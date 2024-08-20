package com.sysaid.assignment.service.impl;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.*;
import com.sysaid.assignment.mapper.TaskMapper;
import com.sysaid.assignment.repository.ITaskRepository;
import com.sysaid.assignment.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    @Value("${external.boredapi.baseURL}")
    private String baseUrl;

    private final ITaskRepository ITaskRepository;
    private final TaskMapper taskMapper;

    @Override
    public ResponseEntity<Task[]> getUncompletedTasks(String user, String type) {
        List<Task> uncompletedTasks = ITaskRepository.getUncompletedTasks(user, type);
        Task[] taskArray = uncompletedTasks.toArray(Task[]::new);
        return ResponseEntity.ok(taskArray);
    }

    @Override
    public ResponseEntity<Task[]> getCompletedTasks(String user) {
        List<Task> uncompletedTasks = ITaskRepository.getCompletedTasks(user);
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
    public ResponseEntity<Task> completeTask(CompleteRequestTaskDto completeRequestTaskDto) {
        Task task =  ITaskRepository.completeTask(completeRequestTaskDto);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task[]> addTaskToUserWishList(AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto) {
        List<Task> userWishList =  ITaskRepository.addTaskToUserWishList(addRequestTaskToUserWishListDto.getUsername(),
                addRequestTaskToUserWishListDto.getExistTaskDto());

        Task[] taskArray = userWishList.toArray(Task[]::new);
        return ResponseEntity.ok(taskArray);
    }

    @Override
    public ResponseEntity<Task> createTask(CreateRequestTaskDto createRequestTaskDto) {
        Task createdTask = ITaskRepository.createTask(taskMapper.mapCreateRequestTaskDtoToTask(createRequestTaskDto),
                createRequestTaskDto.getUsername());
        return ResponseEntity.ok(createdTask);
    }

    @Override
    public ResponseEntity<Task> updateTask(UpdateRequestTaskDto updateTaskDto) {
        Task updatedTask = ITaskRepository.updateTask(taskMapper.updateUpdateRequestTaskDtoToTask(updateTaskDto),
                updateTaskDto.getKey());
        return ResponseEntity.ok(updatedTask);
    }

    @Override
    public ResponseEntity<Task> getTask(GetRequestTaskDto getRequestTaskDto) {
        Task task = ITaskRepository.getTask(getRequestTaskDto);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto) {
        Task task = ITaskRepository.deleteTask(deleteRequestTaskDto);
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<Task[]> getUserWishList(GetRequestUserWishListDto getRequestUserWishListDto) {
        List<Task> userWishList = ITaskRepository.getUserWishList(getRequestUserWishListDto.getUsername());

        Task[] taskArray = userWishList.toArray(Task[]::new);
        return ResponseEntity.ok(taskArray);
    }
}
