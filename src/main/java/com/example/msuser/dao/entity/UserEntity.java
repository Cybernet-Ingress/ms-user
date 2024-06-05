package com.example.msuser.dao.entity;

import com.example.msuser.model.enums.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import lombok.*;
import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String password;
    private String mail;
    private String userType;
    private String photo;

    @Enumerated(STRING)
    private UserStatus status;

    private LocalDate createDate;
    private LocalDate updateDate;
}