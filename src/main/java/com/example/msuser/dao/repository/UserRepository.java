package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<UserEntity> findById(Long id);

    void save(UserEntity userEntity);
}
