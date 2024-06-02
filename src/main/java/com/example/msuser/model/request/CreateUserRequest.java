package com.example.msuser.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String name;
    private String surname;
    private String mail;
    private String photo = Arrays.toString(Base64.getEncoder().encode(getPhoto().getBytes(UTF_8)));
}