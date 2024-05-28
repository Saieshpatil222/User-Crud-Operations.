package com.main.project.springbootrestfulwebservices.services;

import com.main.project.springbootrestfulwebservices.dto.UserDto;
import com.main.project.springbootrestfulwebservices.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService  {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);

    UserDto getByUserId(Long id);

    List<UserDto> getAllUsers();

    void deleteUser(Long id);
}
