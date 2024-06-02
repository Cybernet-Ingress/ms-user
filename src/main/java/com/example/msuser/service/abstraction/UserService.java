package com.example.msuser.service.abstraction;

import com.example.msuser.exception.NotFoundException;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.response.UserResponse;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createUser(CreateUserRequest request);
    void authUser(UserResponse response);
    UserResponse getUser(Long id);
    void updateUser(Long id);
}