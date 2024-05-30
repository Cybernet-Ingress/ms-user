package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.model.response.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<UserEntity> findById(Long id);

    void save(UserEntity userEntity);
    void auth(UserResponse userResponse);
}
