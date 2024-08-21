package com.sysaid.assignment.repository.impl;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;
import com.sysaid.assignment.dto.*;
import com.sysaid.assignment.exception.TaskNotFoundException;
import com.sysaid.assignment.exception.UserNotFoundException;
import com.sysaid.assignment.exception.WrongTaskTypeException;
import com.sysaid.assignment.mapper.TaskMapper;
import com.sysaid.assignment.repository.ITaskRepository;
import com.sysaid.assignment.storage.TaskStorage;
import com.sysaid.assignment.storage.TaskUsersStorage;
import com.sysaid.assignment.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component //TODO change to @Repository after connect DB
public class TaskRepositoryImpl implements ITaskRepository {
    private final Map<Task, UserData> taskUserDataMap = TaskUsersStorage.getInstance().getTaskMap();
    private final Set<UserData> userList = UserStorage.getInstance().getUserList();
    private final Set<Task> taskList = TaskStorage.getInstance().getTaskSet();
    private final TaskMapper taskMapper;

    @Override
    public List<Task> getUncompletedTasks(String user, String type) {
        if (user == null) {
            throw new UserNotFoundException("An empty login user was received!");
        }
        if (type != null && type.equals("complete")) {
            throw new WrongTaskTypeException("The type cannot be complete!");
        }

        return taskUserDataMap.entrySet().stream()
                .filter(entry -> (type == null && !entry.getKey().getType().equals("complete"))
                        || entry.getKey().getType().equals(type))
                .filter(entry -> entry.getValue().getUsername().equals(user))
                .map(Map.Entry::getKey)
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getCompletedTasks(String username) {
        if (username == null) {
            throw new UserNotFoundException("An empty login user was received!");
        }

        return taskUserDataMap.keySet().stream()
                .filter(userData -> userData.getType().equals("complete"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> addTaskToUserWishList(String username, ExistTaskDto existTaskDto) {
        UserData user = findUser(username);
        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }

        Task task = findTask(existTaskDto.getTaskKey());
        task.setRating(task.getRating() + 1);

        taskUserDataMap.put(task, user);
        return getUserWishList(username);
    }

    @Override
    public List<Task> getUserWishList(String username) {
        UserData user = findUser(username);
        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }

        return taskUserDataMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(user))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByRating(Integer rating) {
        if (rating != null) {
            return taskList.stream()
                    .filter(item -> item.getRating() == rating)
                    .collect(Collectors.toList());
        }
        List<Task> sortedTasks = taskList.stream()
                .sorted(Comparator.comparingInt(Task::getRating).reversed())
                .collect(Collectors.toList());

        List<Task> selectedTasks = new ArrayList<>();

        selectedTasks.addAll(selectTopNPercent(sortedTasks, 20, 0)); //TODO move all magic numbers and hardcode to the constants class

        selectedTasks.addAll(selectTopNPercent(sortedTasks, 20, 20));

        selectedTasks.addAll(selectTopNPercent(sortedTasks, 10, 40));

        selectedTasks.addAll(selectTopNPercent(sortedTasks, 5, 50));

        selectedTasks.addAll(selectTopNPercent(sortedTasks, 5, 55));

        selectedTasks.addAll(selectRandomNPercent(taskList, 40));

        return selectedTasks;
    }

    @Override
    public Task createTask(Task task) {
        taskList.add(task);

        return task;
    }

    @Override
    public Task updateTask(Task updatedTask, String taskKey) {
        Task oldTask = findTask(taskKey);

        taskUserDataMap.put(updatedTask, taskUserDataMap.get(oldTask));
        taskList.add(updatedTask);

        taskUserDataMap.remove(oldTask);
        taskList.remove(oldTask);

        return updatedTask;
    }

    @Override
    public Task getTask(GetRequestTaskDto getRequestTaskDto) {
        String keyToFind = getRequestTaskDto.getKey();

        return findTask(keyToFind);
    }

    @Override
    public Task deleteTask(DeleteRequestTaskDto deleteRequestTaskDto) {
        String keyToFind = deleteRequestTaskDto.getKey();

        Task taskToDelete = findTask(keyToFind);

        taskUserDataMap.remove(taskToDelete);
        taskList.remove(taskToDelete);

        return taskToDelete;
    }

    @Override
    public Task completeTask(CompleteRequestTaskDto completeRequestTaskDto) {
        String keyToFind = completeRequestTaskDto.getTaskKey();

        Task taskToComplete = findTask(keyToFind);
        taskToComplete.setType("complete");

        taskToComplete.setRating(taskToComplete.getRating() + 2);

        return taskToComplete;
    }

    private Task findTask(String taskKey) {
        return taskList.stream()
                .filter(task -> task.getKey().equals(taskKey))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found with key: " + taskKey));
    }

    private UserData findUser(String username) {
        return userList.stream()
                .filter(item -> item.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    private List<Task> selectTopNPercent(List<Task> sortedTasks, int percent, int startIndex) {
        int size = sortedTasks.size();
        int numTasks = size * percent / 100;

        return sortedTasks.stream()
                .skip(startIndex)
                .limit(numTasks)
                .collect(Collectors.toList());
    }

    private List<Task> selectRandomNPercent(Set<Task> tasks, int percent) {
        int size = tasks.size();
        int numTasks = size * percent / 100;

        List<Task> randomTasks = new ArrayList<>(tasks);
        Collections.shuffle(randomTasks);

        return randomTasks.stream()
                .limit(numTasks)
                .collect(Collectors.toList());
    }
}

