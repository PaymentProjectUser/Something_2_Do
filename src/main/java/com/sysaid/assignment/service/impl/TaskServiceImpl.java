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

    public ResponseEntity<CreateResponseTaskDto> getRandomTask() {
        String endpointUrl = String.format("%s/activity", baseUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<Task> responseEntity = template.getForEntity(endpointUrl, Task.class);

        return ResponseEntity.ok(taskMapper.taskToCreateTaskDto(
                ITaskRepository.createTask(responseEntity.getBody(), null)));
    }

    @Override
    public ResponseEntity<CompleteResponseTaskDto> completeTask(CompleteRequestTaskDto completeRequestTaskDto) {
        Task task =  ITaskRepository.completeTask(completeRequestTaskDto);
        return ResponseEntity.ok(taskMapper.taskToCompleteTaskDto(task));
    }

    @Override
    public ResponseEntity<GetRequestUserWishListTaskDto[]> addTaskToUserWishList(AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto) {
        return ResponseEntity.ok(
                ITaskRepository.addTaskToUserWishList(addRequestTaskToUserWishListDto.getUsername(),
                addRequestTaskToUserWishListDto.getExistTaskDto()).stream()
                        .map(taskMapper::taskToGetRequestUserWishListTaskDto)
                        .toArray(GetRequestUserWishListTaskDto[]::new)
                );
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
