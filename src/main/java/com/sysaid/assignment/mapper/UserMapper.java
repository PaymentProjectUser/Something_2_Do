package com.sysaid.assignment.mapper;

import com.sysaid.assignment.domain.UserData;
import com.sysaid.assignment.dto.CreateRequestUserDto;
import com.sysaid.assignment.dto.CreateResponseUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserData createRequestUserDtoToUserData(CreateRequestUserDto createRequestUserDto);

    CreateResponseUserDto userDataToCreateResponseUserDto(UserData userData);
}
