package com.sysaid.assignment.storage;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;

import java.util.HashMap;
import java.util.Map;

public class TaskStorage {
    private static TaskStorage instance;

    private final Map<Task, UserData> taskMap;

    private TaskStorage() {
        taskMap = new HashMap<>();
    }

    public static synchronized TaskStorage getInstance() {
        if (instance == null) {
            instance = new TaskStorage();
        }
        return instance;
    }

    public Map<Task, UserData> getTaskMap() {
        return taskMap;
    }
}
