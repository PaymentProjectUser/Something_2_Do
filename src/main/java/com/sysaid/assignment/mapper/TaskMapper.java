package com.sysaid.assignment.mapper;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task mapCreateRequestTaskDtoToTask(CreateRequestTaskDto createRequestTaskDto);
}
