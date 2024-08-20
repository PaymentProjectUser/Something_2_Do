package com.sysaid.assignment.service;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.DeleteRequestTaskDto;
import com.sysaid.assignment.dto.GetRequestTaskDto;
import com.sysaid.assignment.dto.UpdateRequestTaskDto;
import org.springframework.http.ResponseEntity;

public interface ITaskService {
    ResponseEntity<Task[]> getUncompletedTasks(String user, String type);

    ResponseEntity<Task> getRandomTask();

    void completeTask();

    void addTaskToUserWishList();

    ResponseEntity<Task> createTask(CreateRequestTaskDto createRequestTaskDto);

    ResponseEntity<Task> updateTask(UpdateRequestTaskDto updateTaskDto);

    ResponseEntity<Task> getTask(GetRequestTaskDto getRequestTaskDto);

    ResponseEntity<Task> deleteTask(DeleteRequestTaskDto deleteRequestTaskDto);
}
