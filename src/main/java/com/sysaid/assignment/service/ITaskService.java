package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ITaskService {
     ResponseEntity<Task[]> getUncompletedTasks(String user, String type);

    ResponseEntity<Task> getRandomTask();
}
