package com.example.msuser.service.abstraction;

import com.example.msuser.model.request.AuthRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createUser(CreateUserRequest request);
    void authUser(AuthRequest authRequest);
    UserResponse getUser(Long id);
    void updateUser(Long id, UpdateUserRequest userRequest);
}