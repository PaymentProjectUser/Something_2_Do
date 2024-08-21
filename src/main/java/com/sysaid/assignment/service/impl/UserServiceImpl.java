package com.sysaid.assignment.service.impl;

import com.sysaid.assignment.dto.CreateRequestUserDto;
import com.sysaid.assignment.dto.CreateResponseUserDto;
import com.sysaid.assignment.repository.IUserRepository;
import com.sysaid.assignment.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public ResponseEntity<CreateResponseUserDto> createUser(CreateRequestUserDto createRequestUserDto) {
        return ResponseEntity.ok(userRepository.createUser(createRequestUserDto));
    }
}
