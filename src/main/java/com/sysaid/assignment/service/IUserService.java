package com.sysaid.assignment.service;

import com.sysaid.assignment.dto.CreateRequestUserDto;
import com.sysaid.assignment.dto.CreateResponseUserDto;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<CreateResponseUserDto> createUser(CreateRequestUserDto createRequestUserDto);
}
