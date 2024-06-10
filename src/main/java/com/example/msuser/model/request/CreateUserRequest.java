package com.example.msuser.model.request;

import com.example.msuser.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
    private UserType type;

    @NotNull
    private String photo;
}