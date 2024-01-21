package com.ks.practice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "Username can not be emoty")
    private String userName;
    @NotEmpty(message = "Password can not be emoty")
    private String password;
}
