package com.example.msuser.mapper;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.model.enums.UserType;
import com.example.msuser.model.request.CreateUserRequest;
import com.example.msuser.model.request.UpdateUserRequest;
import com.example.msuser.model.response.UserResponse;

public enum UserMapper {
    USER_MAPPER;

    public UserEntity buildUserEntity(CreateUserRequest userRequest) {
        return UserEntity
                .builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .mail(userRequest.getMail())
                .password(userRequest.getPassword())
                .type(userRequest.getType())
                .photo(userRequest.getPhoto())
                .build();
    }

    public UserResponse buildUserResponse(UserEntity userEntity) {
        return UserResponse
                .builder()
                .surname(userEntity.getSurname())
                .name(userEntity.getName())
                .mail(userEntity.getMail())
                .userType(userEntity.getType())
                .birthDate(userEntity.getBirthDate())
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
                .build();
    }
}