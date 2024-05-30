package com.example.msuser.service.concrete;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.dao.repository.UserRepository;
import com.example.msuser.exception.NotFoundException;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.response.UserResponse;
import com.example.msuser.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.msuser.mapper.UserMapper.USER_MAPPER;
import static com.example.msuser.model.enums.UserStatus.UPDATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceHandler implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest request) {
        log.info("ActionLog.createUser.start request: {}", request);
        userRepository.save(USER_MAPPER.buildUserEntity(request));
        log.info("ActionLog.createUser.success request: {}", request);
    }

    @Override
    public void authUser(UserResponse response) {
        log.info("ActionLog.authUser.start response: {}", response);
        userRepository.auth(response);
        log.info("ActionLog.authUser.success response: {}", response);
    }

    @Override
    public UserResponse getUser(Long id) throws NotFoundException {
        log.info("ActionLog.getUser.start request: {}", id);
        var user = fetchIfExist(id);
        return USER_MAPPER.buildUserResponse(user);
    }

    @Override
    public void updateUser(Long id) throws NotFoundException {
        var user = fetchIfExist(id);
        user.setName(user.getName());
        user.setSurname(user.getSurname());
        user.setMail(user.getMail());
        user.setPhoto(user.getPhoto());
        user.setStatus(UPDATED);
        userRepository.save(user);
    }

    private UserEntity fetchIfExist(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }
}