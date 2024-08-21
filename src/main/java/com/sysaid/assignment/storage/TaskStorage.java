package com.sysaid.assignment.storage;

import com.sysaid.assignment.domain.Task;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class TaskStorage {
    private static TaskStorage instance;

    private final Set<Task> taskSet;

    private TaskStorage() {
        taskSet = new HashSet<>();
    }

    public static synchronized TaskStorage getInstance() {
        if (instance == null) {
            instance = new TaskStorage();
        }
        return instance;
    }
}
