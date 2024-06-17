package com.example.msuser.service.concrete;

import com.example.msuser.annotation.LogAnnotation;
import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.exception.NotFoundException;
import com.example.msuser.exception.WrongCredentialsException;
import com.example.msuser.model.request.AuthRequest;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;
import com.example.msuser.service.abstraction.UserService;
import com.example.msuser.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static com.example.msuser.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
@LogAnnotation
public class UserServiceHandler implements UserService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public void signUp(CreateUserRequest request) {
        request.setPassword(securityUtil.hashPassword(request.getPassword()));
        userRepository.save(USER_MAPPER.buildUserEntity(request));
    }

    @Override
    public void signIn(AuthRequest authRequest)  {
        userRepository.findByMail(authRequest.getMail())
                .ifPresentOrElse(userEntity -> {
                    if (!securityUtil.verifyPassword(authRequest.getPassword(), userEntity.getPassword())) {
                        throw new WrongCredentialsException("User not match with given credentials");
                    }
                }, () -> {
                    throw new NotFoundException("User not found!");
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
        userRepository.save(USER_MAPPER.buildUpdateUserEntity(userRequest, id));
    }

    private UserEntity fetchIfExistUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    private UserEntity fetchUserByMail(String mail) {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new NotFoundException("Mail not found!"));
    }
}