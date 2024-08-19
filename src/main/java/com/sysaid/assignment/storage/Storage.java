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

    public void addTask(Task task, UserData userData) {
        taskMap.put(task, userData);
    }

    public UserData getUserDataByTask(Task task) {
        return taskMap.get(task);
    }

    public void removeTask(Task task) {
        taskMap.remove(task);
    }

    public boolean containsTask(Task task) {
        return taskMap.containsKey(task);
    }
}
