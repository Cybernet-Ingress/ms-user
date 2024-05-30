package com.example.msuser.service.abstraction;

import com.example.msuser.exception.NotFoundException;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void createUser(CreateUserRequest request);
    UserResponse getUser(Long id) throws NotFoundException;
    void deleteUser(Long id) throws NotFoundException;

    void updateUser(Long id) throws NotFoundException;
}