package com.sysaid.assignment.repository;

import com.sysaid.assignment.dto.CreateRequestUserDto;
import com.sysaid.assignment.dto.CreateResponseUserDto;

public interface IUserRepository {
    CreateResponseUserDto createUser(CreateRequestUserDto createRequestUserDto);
}
