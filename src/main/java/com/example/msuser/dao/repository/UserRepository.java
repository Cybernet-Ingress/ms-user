package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByPasswordAndMail(String password, String mail);

    Optional<UserEntity> findByMail(String mail);
}
