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
    public ResponseEntity<GetRequestUncompletedTaskDto[]> getUncompletedTasks(String user, String type) {
        return ResponseEntity.ok(
                ITaskRepository.getUncompletedTasks(user, type).stream()
                        .map(taskMapper::taskToGetRequestUncompletedTaskDto)
                        .toArray(GetRequestUncompletedTaskDto[]::new)
        );
    }

    @Override
    public ResponseEntity<GetRequestСompletedTaskDto[]> getCompletedTasks(String user) {
        return ResponseEntity.ok(
                ITaskRepository.getCompletedTasks(user).stream()
                        .map(taskMapper::taskToGetRequestCompletedTaskDto)
                        .toArray(GetRequestСompletedTaskDto[]::new)
        );
    }

    public ResponseEntity<Task> getRandomTask() {
        String endpointUrl = String.format("%s/activity", baseUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Task> responseEntity = template.getForEntity(endpointUrl, Task.class);

        ITaskRepository.createTask(responseEntity.getBody(), null);
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
    public ResponseEntity<CreateResponseTaskDto> createTask(CreateRequestTaskDto createRequestTaskDto) {
        Task createdTask = ITaskRepository.createTask(taskMapper.mapCreateRequestTaskDtoToTask(createRequestTaskDto),
                createRequestTaskDto.getUsername());

        return ResponseEntity.ok(taskMapper.taskToCreateTaskDto(createdTask));
    }

    @Override
    public ResponseEntity<UpdateResponseTaskDto> updateTask(UpdateRequestTaskDto updateTaskDto) {
        Task updatedTask = ITaskRepository.updateTask(taskMapper.updateUpdateRequestTaskDtoToTask(updateTaskDto),
                updateTaskDto.getKey());
        return ResponseEntity.ok(taskMapper.taskToUpdateTaskDto(updatedTask));
    }

    @Override
    public ResponseEntity<GetResponseTaskDto> getTask(GetRequestTaskDto getRequestTaskDto) {
        Task task = ITaskRepository.getTask(getRequestTaskDto);
        return ResponseEntity.ok(taskMapper.taskToGetResponseTaskDto(task));
    }

    @Override
    public ResponseEntity<DeleteResponseTaskDto> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto) {
        Task task = ITaskRepository.deleteTask(deleteRequestTaskDto);
        return ResponseEntity.ok(taskMapper.taskToDeleteTaskDto(task));
    }

    @Override
    public ResponseEntity<GetRequestUserWishListTaskDto[]> getUserWishList(String username) {
        return ResponseEntity.ok(
                ITaskRepository.getUserWishList(username).stream()
                        .map(taskMapper::taskToGetRequestUserWishListTaskDto)
                        .toArray(GetRequestUserWishListTaskDto[]::new)
        );
    }
}
