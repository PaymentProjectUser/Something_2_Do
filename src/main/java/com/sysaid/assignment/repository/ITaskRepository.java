package com.sysaid.assignment.repository;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.domain.UserData;
import com.sysaid.assignment.dto.*;

import java.util.List;

public interface ITaskRepository {
    List<Task> getUncompletedTasks(String user, String type);

    Task completeTask(CompleteRequestTaskDto completeRequestTaskDto);

    Task createTask(Task task);

    Task updateTask(Task updatedTask, String taskKey);

    Task getTask(GetRequestTaskDto getRequestTaskDto);

    Task deleteTask(DeleteRequestTaskDto deleteRequestTaskDto);

    List<Task> getCompletedTasks(String username);

    List<Task> addTaskToUserWishList(String username, ExistTaskDto existTaskDto);

    List<Task> getUserWishList(String username);

    List<Task> getTasksByRating(Integer rating);
}
