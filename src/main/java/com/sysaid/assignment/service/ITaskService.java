package com.sysaid.assignment.service;

import com.sysaid.assignment.dto.*;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    ResponseEntity<GetRequestUncompletedTaskDto[]> getUncompletedTasks(String user, String type);

    ResponseEntity<GetRequestСompletedTaskDto[]> getCompletedTasks(String user);

    ResponseEntity<CreateResponseTaskDto> getRandomTask();

    ResponseEntity<CreateResponseTaskDto> createTask(CreateRequestTaskDto createRequestTaskDto);

    ResponseEntity<UpdateResponseTaskDto> updateTask(UpdateRequestTaskDto updateTaskDto);

    ResponseEntity<GetResponseTaskDto> getTask(GetRequestTaskDto getRequestTaskDto);

    ResponseEntity<DeleteResponseTaskDto> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto);

    ResponseEntity<GetResponseUserWishListTaskDto[]> getUserWishList(String username);

    ResponseEntity<CompleteResponseTaskDto> completeTask(CompleteRequestTaskDto completeRequestTaskDto);

    ResponseEntity<AddResponseTaskToUserWishListDto[]> addTaskToUserWishList(AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto);

    ResponseEntity<GetResponseTasksByRating[]> getTasksByRating(Integer rating);
}
