package com.example.msuser.model.response;

import com.example.msuser.exception.ExceptionConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    private String code;
    private String message;
}
