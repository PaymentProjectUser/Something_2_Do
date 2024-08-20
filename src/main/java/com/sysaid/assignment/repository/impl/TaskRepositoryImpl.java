package com.sysaid.assignment.repository.impl;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.DeleteRequestTaskDto;
import com.sysaid.assignment.dto.GetRequestTaskDto;
import com.sysaid.assignment.exception.TaskNotFoundException;
import com.sysaid.assignment.exception.UserNotFoundException;
import com.sysaid.assignment.mapper.TaskMapper;
import com.sysaid.assignment.repository.TaskRepository;
import com.sysaid.assignment.storage.TaskStorage;
import com.sysaid.assignment.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component //TODO change to @Repository after connect DB
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskStorage taskStorage = TaskStorage.getInstance();
    private final UserStorage userStorage = UserStorage.getInstance();
    private final TaskMapper taskMapper;

    @Override
    public List<Task> getUncompletedTasks(String user, String type) {
        if (user == null) {
            throw new UserNotFoundException("An empty login user was received!");
        }
        Map<Task, UserData> taskMap = taskStorage.getTaskMap();
        return taskMap.entrySet().stream()
                .filter(entry -> type == null || entry.getKey().getType().equals(type))
                .filter(entry -> entry.getValue().getUsername().equals(user))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Task createTask(CreateRequestTaskDto createRequestTaskDto) {
        Map<Task, UserData> taskMap = taskStorage.getTaskMap();
        Set<UserData> userList = userStorage.getUserList();
        Optional<UserData> user = userList.stream()
                .filter(item -> item.getUsername().equals(createRequestTaskDto.getUsername()))
                .findFirst();
        Task mappedTask = taskMapper.mapCreateRequestTaskDtoToTask(createRequestTaskDto);
        taskMap.put(mappedTask, user.get());
        return mappedTask;
    }

    @Override
    public Task updateTask(Task updateTaskDto) {
        return null;
    }

    @Override
    public Task getTask(GetRequestTaskDto getRequestTaskDto) {
        Map<Task, UserData> taskMap = taskStorage.getTaskMap();
        String keyToFind = getRequestTaskDto.getKey();

        return taskMap.keySet().stream()
                .filter(task -> task.getKey().equals(keyToFind))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found with key: " + keyToFind));
    }

    @Override
    public Task deleteTask(DeleteRequestTaskDto deleteRequestTaskDto) {
        Map<Task, UserData> taskMap = taskStorage.getTaskMap();
        String keyToFind = deleteRequestTaskDto.getKey();

        Task taskToDelete = taskMap.keySet().stream()
                .filter(task -> task.getKey().equals(keyToFind))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found with key: " + keyToFind));

        taskMap.remove(taskToDelete);
        return taskToDelete;
    }
}

