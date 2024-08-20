package com.sysaid.assignment.storage;

import com.sysaid.assignment.domain.UserData;
import java.util.HashSet;
import java.util.Set;

public class UserStorage {
    private static UserStorage instance;

    private final Set<UserData> userList;

    private UserStorage() {
        userList = new HashSet<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Set<UserData> getUserList() {
        return userList;
    }
}
