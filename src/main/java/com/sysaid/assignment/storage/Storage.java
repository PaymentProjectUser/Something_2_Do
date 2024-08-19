package com.sysaid.assignment.storage;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;

import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static Storage instance;

    private final Map<Task, UserData> taskMap;

    private Storage() {
        taskMap = new HashMap<>();
    }

    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Map<Task, UserData> getTaskMap() {
        return taskMap;
    }
}
