package com.example.msuser.model.request;

import com.example.msuser.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String surname;
    private String password;
    private String mail;
    private LocalDate birthDate;
    private UserType type;
    private String photo;
}