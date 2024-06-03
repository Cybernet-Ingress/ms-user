package com.example.msuser.dao.repository;

import com.example.msuser.dao.entity.UserEntity;
import com.example.msuser.model.request.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByPasswordAndMail(String password, String mail);

    Optional<UserEntity> findByMail(String mail);
}
