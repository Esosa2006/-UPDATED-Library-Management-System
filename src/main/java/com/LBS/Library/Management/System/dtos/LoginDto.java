package com.LBS.Library.Management.System.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Password is required!")
    private String password;
}
