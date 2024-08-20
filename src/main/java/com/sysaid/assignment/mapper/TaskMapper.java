package com.sysaid.assignment.mapper;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.UpdateRequestTaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task mapCreateRequestTaskDtoToTask(CreateRequestTaskDto createRequestTaskDto);

    Task updateUpdateRequestTaskDtoToTask(UpdateRequestTaskDto updateRequestTaskDto);
}
