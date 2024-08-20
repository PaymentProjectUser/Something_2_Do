package com.sysaid.assignment.mapper;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task mapCreateRequestTaskDtoToTask(CreateRequestTaskDto createRequestTaskDto);

    Task updateUpdateRequestTaskDtoToTask(UpdateRequestTaskDto updateRequestTaskDto);

    GetRequestUncompletedTaskDto taskToGetRequestUncompletedTaskDto(Task task);

    GetRequestСompletedTaskDto taskToGetRequestCompletedTaskDto(Task task);

    CreateResponseTaskDto taskToCreateTaskDto(Task task);

    UpdateResponseTaskDto taskToUpdateTaskDto(Task task);

    GetResponseTaskDto taskToGetResponseTaskDto(Task task);

    DeleteResponseTaskDto taskToDeleteTaskDto(Task task);

    GetRequestUserWishListTaskDto taskToGetRequestUserWishListTaskDto(Task task);
}
