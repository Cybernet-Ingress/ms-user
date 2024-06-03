package com.example.msuser.controller;

import com.example.msuser.model.request.AuthRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
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

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
    }

    @GetMapping("/sign-in")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void authUser(@RequestBody AuthRequest authRequest) {
        userService.authUser(authRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest userRequest) {
        userService.updateUser(id, userRequest);
    }
}