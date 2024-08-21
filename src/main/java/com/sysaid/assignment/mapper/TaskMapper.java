package com.sysaid.assignment.mapper;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.AddResponseTaskToUserWishListDto;
import com.sysaid.assignment.dto.CompleteResponseTaskDto;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.CreateResponseTaskDto;
import com.sysaid.assignment.dto.DeleteResponseTaskDto;
import com.sysaid.assignment.dto.GetRequestUncompletedTaskDto;
import com.sysaid.assignment.dto.GetRequestСompletedTaskDto;
import com.sysaid.assignment.dto.GetResponseTaskDto;
import com.sysaid.assignment.dto.GetResponseTasksByRating;
import com.sysaid.assignment.dto.GetResponseUserWishListTaskDto;
import com.sysaid.assignment.dto.UpdateRequestTaskDto;
import com.sysaid.assignment.dto.UpdateResponseTaskDto;
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

    AddResponseTaskToUserWishListDto taskToAddResponseTaskToUserWishListDto(Task task);

    GetResponseUserWishListTaskDto taskToGetRequestUserWishListTaskDto(Task task);

    CompleteResponseTaskDto taskToCompleteTaskDto(Task task);

    GetResponseTasksByRating taskToGetResponseTasksByRating(Task task);
}
