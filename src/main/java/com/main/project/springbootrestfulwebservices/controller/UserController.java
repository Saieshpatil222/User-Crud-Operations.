package com.main.project.springbootrestfulwebservices.controller;

import com.main.project.springbootrestfulwebservices.dto.UserDto;

import com.main.project.springbootrestfulwebservices.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = " - CRUD REST APIs Create User, Update User, Delete User, Get All Users"
)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create USER REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {

        UserDto u = userService.createUser(user);
        return new ResponseEntity<>(u, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Update User REST API",
            description = "Update USER REST API is used to update particular user in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 201 SUCCESS"
    )

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDto user) {

        UserDto user1 = userService.updateUser(user, id);
        return new ResponseEntity<>(user1, HttpStatus.OK);

    }

    @Operation(
            summary = "Get User By Id REST API",
            description = "Get User By Id REST API is used to get single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {

        UserDto user2 = userService.getByUserId(id);
        return user2;

    }

    @Operation(
            summary = "Get All Users By REST API",
            description = "Get All Users REST API is used to All users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> user = userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted!!", HttpStatus.OK);
    }


}
