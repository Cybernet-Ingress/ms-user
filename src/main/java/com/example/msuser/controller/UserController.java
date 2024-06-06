package com.example.msuser.controller;

import com.example.msuser.model.request.AuthRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;
import com.example.msuser.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(ACCEPTED)
    public void authUser(@RequestBody AuthRequest authRequest) {
        userService.authUser(authRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }
}