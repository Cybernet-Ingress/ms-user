package com.example.msuser.service.concrete;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.exception.NotFoundException;
import com.example.msuser.exception.WrongCredentialsException;
import com.example.msuser.model.request.AuthRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;
import com.example.msuser.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.example.msuser.mapper.UserMapper.USER_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {

    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Override
    public void createUser(CreateUserRequest request) {
        log.info("ActionLog.createUser.start request: {}", request);
        request.setPassword(securityService.hashPassword(request.getPassword()));
        userRepository.save(USER_MAPPER.buildUserEntity(request));
        log.info("ActionLog.createUser.success request: {}", request);
    }

    @Override
    public void authUser(AuthRequest authRequest)  {
        log.info("ActionLog.authUser.start authRequest: {}", authRequest);
        userRepository.findByMail(authRequest.getMail())
                .ifPresentOrElse(userEntity -> {
                    if (!securityService.verifyPassword(authRequest.getPassword(), userEntity.getPassword())) {
                        throw new WrongCredentialsException("User not match with given credentials", HttpStatus.UNAUTHORIZED.toString());
                    }
                }, () -> {
                    throw new NotFoundException("User not found!", HttpStatus.NOT_FOUND.toString());
                });
        log.info("ActionLog.authUser.success authRequest: {}", authRequest);
    }

    @Override
    public UserResponse getUser(Long id) {
        log.info("ActionLog.getUser.start request: {}", id);
        var user = fetchIfExistUser(id);
        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public void updateUser(Long id, UpdateUserRequest userRequest) {
        var user = fetchIfExistUser(id);
        user.setPassword(securityService.hashPassword(userRequest.getPassword()));
        Base64.getDecoder().decode(user.getPhoto());
        userRepository.save(USER_MAPPER.buildUpdateUserEntity(userRequest, id));
    }

    private UserEntity fetchIfExistUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!", HttpStatus.NOT_FOUND.toString()));
    }
}