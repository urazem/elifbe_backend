package com.urazem.elifbe.modelDto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String email;
    private String password;
}
