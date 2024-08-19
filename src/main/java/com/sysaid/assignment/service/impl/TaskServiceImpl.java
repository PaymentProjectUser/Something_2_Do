package com.sysaid.assignment.service.impl;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.repository.TaskRepository;
import com.sysaid.assignment.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;

    private final TaskRepository taskRepository;

    @Override
    public ResponseEntity<Task[]> getUncompletedTasks(String user, String type) {
        List<Task> uncompletedTasks = taskRepository.getUncompletedTasks(user, type);
        Task[] taskArray = uncompletedTasks.toArray(Task[]::new);
        return ResponseEntity.ok(taskArray);
    }

    public ResponseEntity<Task> getRandomTask() {
        String endpointUrl = String.format("%s/activity", baseUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Task> responseEntity = template.getForEntity(endpointUrl, Task.class);

        return responseEntity;
    }
}
