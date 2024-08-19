package com.sysaid.assignment.repository.impl;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;
import com.sysaid.assignment.exception.UserNotFoundException;
import com.sysaid.assignment.repository.TaskRepository;
import com.sysaid.assignment.storage.Storage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component //TODO change to @Repository after connect DB
public class TaskRepositoryImpl implements TaskRepository {
    private final Storage storage = Storage.getInstance();

    @Override
    public List<Task> getUncompletedTasks(String user, String type) {
        if (user == null) {
            throw new UserNotFoundException("An empty login user was received!");
        }
        Map<Task, UserData> taskMap = storage.getTaskMap();
        return taskMap.entrySet().stream()
                .filter(entry -> type == null || entry.getKey().getType().equals(type))
                .filter(entry -> entry.getValue().getUsername().equals(user))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

