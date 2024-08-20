package com.sysaid.assignment.service.impl;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.DeleteRequestTaskDto;
import com.sysaid.assignment.dto.GetRequestTaskDto;
import com.sysaid.assignment.dto.UpdateRequestTaskDto;
import com.sysaid.assignment.mapper.TaskMapper;
import com.sysaid.assignment.repository.TaskRepository;
import com.sysaid.assignment.service.ITaskService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<Task[]> getUncompletedTasks(String user, String type) {
        List<Task> uncompletedTasks = taskRepository.getUncompletedTasks(user, type);
        Task[] taskArray = uncompletedTasks.toArray(Task[]::new);
        return ResponseEntity.ok(taskArray);
    }

    public ResponseEntity<Task> getRandomTask() {
        String endpointUrl = "https://www.boredapi.com/activity"; //String.format("%s/activity", baseUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Task> responseEntity = template.getForEntity(endpointUrl, Task.class);

        return responseEntity;
    }

    @Override
    public void completeTask() {

    }

    @Override
    public void addTaskToUserWishList() {

    }

    @Override
    public ResponseEntity<Task> createTask(CreateRequestTaskDto createRequestTaskDto) {
        Task createdTask = taskRepository.createTask(createRequestTaskDto);
        return ResponseEntity.ok(createdTask);
    }

    @Override
    public ResponseEntity<Task> updateTask(UpdateRequestTaskDto updateTaskDto) {
        return null;
    }

    @Override
    public ResponseEntity<Task> getTask(GetRequestTaskDto getRequestTaskDto) {
        Task task = taskRepository.getTask(getRequestTaskDto);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto) {
        Task task = taskRepository.deleteTask(deleteRequestTaskDto);
        return ResponseEntity.ok(task);
    }
}
