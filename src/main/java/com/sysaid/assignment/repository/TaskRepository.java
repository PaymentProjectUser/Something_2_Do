package com.sysaid.assignment.repository;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.DeleteRequestTaskDto;
import com.sysaid.assignment.dto.GetRequestTaskDto;

import java.util.List;

public interface TaskRepository {
    List<Task> getUncompletedTasks(String user, String type);

    Task createTask(CreateRequestTaskDto task);

    Task updateTask(Task task);

    Task getTask(GetRequestTaskDto getRequestTaskDto);

    Task deleteTask(DeleteRequestTaskDto deleteRequestTaskDto);
}
