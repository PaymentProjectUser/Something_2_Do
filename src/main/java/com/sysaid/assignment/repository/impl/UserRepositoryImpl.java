package com.sysaid.assignment.repository.impl;

import com.sysaid.assignment.domain.UserData;
import com.sysaid.assignment.dto.CreateRequestUserDto;
import com.sysaid.assignment.dto.CreateResponseUserDto;
import com.sysaid.assignment.mapper.UserMapper;
import com.sysaid.assignment.repository.IUserRepository;
import com.sysaid.assignment.storage.UserStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component //TODO change to @Repository after connect DB
@AllArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    private final Set<UserData> userList = UserStorage.getInstance().getUserList();
    private final UserMapper userMapper;

    @Override
    public CreateResponseUserDto createUser(CreateRequestUserDto createRequestUserDto) {
        UserData userData = userMapper.createRequestUserDtoToUserData(createRequestUserDto);
        userList.add(userData);
        return userMapper.userDataToCreateResponseUserDto(userData);
    }
}
