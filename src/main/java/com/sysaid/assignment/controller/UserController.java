package com.sysaid.assignment.controller;

import com.sysaid.assignment.dto.CreateRequestUserDto;
import com.sysaid.assignment.dto.CreateResponseUserDto;
import com.sysaid.assignment.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    @PostMapping("/create") //TODO add registration and authorization with authentication
    public ResponseEntity<CreateResponseUserDto> createUser(@RequestBody CreateRequestUserDto createRequestUserDto) {
        return userService.createUser(createRequestUserDto);
    }
}
