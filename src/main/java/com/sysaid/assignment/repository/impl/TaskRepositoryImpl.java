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
import com.sysaid.assignment.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component //TODO change to @Repository after connect DB
public class TaskRepositoryImpl implements ITaskRepository {
    private final Map<Task, UserData> taskMap = TaskStorage.getInstance().getTaskMap();
    private final Set<UserData> userList = UserStorage.getInstance().getUserList();
    private final TaskMapper taskMapper;

    @Override
    public List<Task> getUncompletedTasks(String user, String type) {
        if (user == null) {
            throw new UserNotFoundException("An empty login user was received!");
        }
        if (type != null && type.equals("complete")) {
            throw new WrongTaskTypeException("The type cannot be complete!");
        }

        return taskMap.entrySet().stream()
                .filter(entry -> (type == null && !entry.getKey().getType().equals("complete"))
                        || entry.getKey().getType().equals(type))
                .filter(entry -> entry.getValue().getUsername().equals(user))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getCompletedTasks(String username) {
        if (username == null) {
            throw new UserNotFoundException("An empty login user was received!");
        }

        return taskMap.keySet().stream()
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

        taskMap.put(task, user);
        return getUserWishList(username);
    }

    @Override
    public List<Task> getUserWishList(String username) {
        UserData user = findUser(username);
        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }

        return taskMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(user))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Task createTask(Task task, String username) {
        UserData userData = null;
        if (username != null) {
            userData = findUser(username);
            if (userData != null) {
                userData.setTaskQuantity(userData.getTaskQuantity() + 1);
            }
        }
        taskMap.put(task, userData);

        return task;
    }

    @Override
    public Task updateTask(Task updatedTask, String taskKey) {
        Task oldTask = findTask(taskKey);
        taskMap.put(updatedTask, taskMap.get(oldTask));

        taskMap.remove(oldTask);

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

        taskMap.remove(taskToDelete);
        return taskToDelete;
    }

    @Override
    public Task completeTask(CompleteRequestTaskDto completeRequestTaskDto) {
        String keyToFind = completeRequestTaskDto.getTaskKey();

        Task taskToComplete = findTask(keyToFind);
        taskToComplete.setType("complete");

        UserData userData = taskMap.get(taskToComplete);
        userData.setTaskQuantity(userData.getTaskQuantity() - 1);

        return taskToComplete;
    }

    private Task findTask(String taskKey) {
        return taskMap.keySet().stream()
                .filter(task -> task.getKey().equals(taskKey))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found with key: " + taskKey));
    }

    private UserData findUser(String username) {
        return userList.stream()
                .filter(item -> item.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}

