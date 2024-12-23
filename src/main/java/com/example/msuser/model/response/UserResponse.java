package com.example.msuser.model.response;

import com.example.msuser.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String name;
    private String surname;
    private String mail;
    private String photo;
    private UserType userType;
    private LocalDate birthDate;
}