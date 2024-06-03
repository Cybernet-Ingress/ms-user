package com.example.msuser.mapper;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;

import static com.example.msuser.model.enums.UserStatus.UPDATED;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(CreateUserRequest userRequest) {
        return UserEntity
                .builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .mail(userRequest.getMail())
                .password(userRequest.getPassword())
                .photo(userRequest.getPhoto())
                .build();
    }

    public UserResponse buildUserResponse(UserEntity userEntity) {
        return UserResponse
                .builder()
                .id(userEntity.getId())
                .surname(userEntity.getSurname())
                .name(userEntity.getName())
                .build();
    }

    public UserEntity buildUpdateUserEntity(UpdateUserRequest updateUserRequest, Long id) {
        return UserEntity
                .builder()
                .id(id)
                .name(updateUserRequest.getName())
                .surname(updateUserRequest.getSurname())
                .mail(updateUserRequest.getMail())
                .photo(updateUserRequest.getPhoto())
                .password(updateUserRequest.getPassword())
                .status(UPDATED)
                .build();
    }
}