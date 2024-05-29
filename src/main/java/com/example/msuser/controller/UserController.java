package com.example.msuser.controller;

import com.example.msuser.exception.NotFoundException;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.response.UserResponse;
import com.example.msuser.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable Long id) throws NotFoundException {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.deleteUser(id);
    }
}