package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    ResponseEntity<Task> getRandomTask();
}
