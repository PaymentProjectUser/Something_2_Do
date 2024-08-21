package com.sysaid.assignment.storage;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TaskUsersStorage {
    private static TaskUsersStorage instance;

    private final Map<Task, UserData> taskMap;

    private TaskUsersStorage() {
        taskMap = new HashMap<>();
    }

    public static synchronized TaskUsersStorage getInstance() {
        if (instance == null) {
            instance = new TaskUsersStorage();
        }
        return instance;
    }

}
