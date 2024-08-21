package com.sysaid.assignment.storage;

import com.sysaid.assignment.domain.UserData;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
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
}
