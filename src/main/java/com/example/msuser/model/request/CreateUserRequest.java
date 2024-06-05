package com.example.msuser.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String mail;
    private String type;
    private String photo = Base64.getEncoder().encodeToString(getPhoto().getBytes());
}