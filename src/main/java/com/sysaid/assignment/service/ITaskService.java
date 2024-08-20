package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.*;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    ResponseEntity<GetRequestUncompletedTaskDto[]> getUncompletedTasks(String user, String type);

    ResponseEntity<GetRequestСompletedTaskDto[]> getCompletedTasks(String user);

    ResponseEntity<Task> getRandomTask();

    ResponseEntity<CreateResponseTaskDto> createTask(CreateRequestTaskDto createRequestTaskDto);

    ResponseEntity<UpdateResponseTaskDto> updateTask(UpdateRequestTaskDto updateTaskDto);

    ResponseEntity<GetResponseTaskDto> getTask(GetRequestTaskDto getRequestTaskDto);

    ResponseEntity<DeleteResponseTaskDto> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto);

    ResponseEntity<GetRequestUserWishListTaskDto[]> getUserWishList(String username);

    ResponseEntity<Task> completeTask(CompleteRequestTaskDto completeRequestTaskDto);

    ResponseEntity<Task[]> addTaskToUserWishList(AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto);
}
