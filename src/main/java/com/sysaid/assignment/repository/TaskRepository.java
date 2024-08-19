package com.sysaid.assignment.repository;

import com.sysaid.assignment.domain.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> getUncompletedTasks(String user, String type);
}
