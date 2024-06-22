package com.example.msuser.controller;


import com.example.msuser.model.request.SignInRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/internal/users")
@RequiredArgsConstructor
public class InternalController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(CREATED)
    public void signUp(@RequestBody CreateUserRequest request) {
        userService.signUp(request);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(ACCEPTED)
    public void signIn(@RequestBody SignInRequest signInRequest) {
        userService.signIn(signInRequest);
    }
}