package com.example.msuser.service.abstraction;

import com.example.msuser.model.request.AuthRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;


public interface UserService {
    void signUp(CreateUserRequest request);
    void signIn(AuthRequest authRequest);
    UserResponse getUser(Long id);
    void updateUser(Long id, UpdateUserRequest userRequest);
}