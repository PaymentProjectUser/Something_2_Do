package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.*;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    ResponseEntity<Task[]> getUncompletedTasks(String user, String type);

    ResponseEntity<Task[]> getCompletedTasks(String user);

    ResponseEntity<Task> getRandomTask();

    ResponseEntity<Task> completeTask(CompleteRequestTaskDto completeRequestTaskDto);

    ResponseEntity<Task[]> addTaskToUserWishList(AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto);

    ResponseEntity<Task> createTask(CreateRequestTaskDto createRequestTaskDto);

    ResponseEntity<Task> updateTask(UpdateRequestTaskDto updateTaskDto);

    ResponseEntity<Task> getTask(GetRequestTaskDto getRequestTaskDto);

    ResponseEntity<Task> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto);

    ResponseEntity<Task[]> getUserWishList(GetRequestUserWishListDto getRequestUserWishListDto);
}
