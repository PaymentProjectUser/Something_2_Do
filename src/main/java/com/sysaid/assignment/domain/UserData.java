package com.sysaid.assignment.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserData {
    private String username;
    private List<Task> tasks;
}
