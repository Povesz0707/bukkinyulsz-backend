package com.example.bukkinyulszbackend.model.payload.signIn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInRequest {
    private String signInKey;
    private String username;
    private String email;
    private String password;

}
