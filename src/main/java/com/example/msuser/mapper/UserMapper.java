package com.example.msuser.mapper;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.response.UserResponse;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(CreateUserRequest userRequest) {
        return UserEntity
                .builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .mail(userRequest.getMail())
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
}