package com.LBS.Library.Management.System.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phone_no;
    @NotBlank(message = "Password is required")
    private String password;
}
