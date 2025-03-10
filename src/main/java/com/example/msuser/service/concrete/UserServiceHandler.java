package com.example.msuser.service.concrete;

import com.example.msuser.annotation.Log;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.exception.NotFoundException;
import com.example.msuser.exception.UnauthorizedException;
import com.example.msuser.exception.WrongCredentialsException;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.SignInRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;
import com.example.msuser.service.abstraction.UserService;
import com.example.msuser.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.example.msuser.exception.ExceptionConstants.ID_NOT_FOUND_CODE;
import static com.example.msuser.exception.ExceptionConstants.ID_NOT_FOUND_EXCEPTION;
import static com.example.msuser.exception.ExceptionConstants.MAIL_NOT_FOUND_EXCEPTION;
import static com.example.msuser.exception.ExceptionConstants.UNAUTHORIZED_CODE;
import static com.example.msuser.exception.ExceptionConstants.UNAUTHORIZED_EXCEPTION;
import static com.example.msuser.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
@Log
public class UserServiceHandler implements UserService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public void signUp(CreateUserRequest request) {
        request.setPassword(securityUtil.hashPassword(request.getPassword()));
        userRepository.save(USER_MAPPER.buildUserEntity(request));
    }

    @Override
    public void signIn(SignInRequest signInRequest) {
        userRepository.findByMail(signInRequest.getMail())
                .ifPresentOrElse(userEntity -> {
                    if (!securityUtil.verifyPassword(signInRequest.getPassword(), userEntity.getPassword())) {
                        throw new UnauthorizedException(UNAUTHORIZED_CODE, String.format(UNAUTHORIZED_EXCEPTION, signInRequest.getMail()));
                    }
                }, () -> {
                    throw new WrongCredentialsException(MAIL_NOT_FOUND_EXCEPTION);
                });
    }

    @Override
    public UserResponse getUser(Long id) {
        var user = fetchIfExistUser(id);
        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public void updateUser(Long id, UpdateUserRequest userRequest) {
        var user = fetchIfExistUser(id);
        user.setPassword(securityUtil.hashPassword(userRequest.getPassword()));
        Base64.getDecoder().decode(user.getPhoto().getBytes());
        userRepository.save(USER_MAPPER.buildUserRequest(userRequest, id));
    }

    private UserEntity fetchIfExistUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ID_NOT_FOUND_CODE, String.format(ID_NOT_FOUND_EXCEPTION, id)));
    }
}