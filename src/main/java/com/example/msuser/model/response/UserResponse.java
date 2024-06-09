package com.example.msuser.model.response;

import com.example.msuser.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String photo;
    private UserType userType;
}
