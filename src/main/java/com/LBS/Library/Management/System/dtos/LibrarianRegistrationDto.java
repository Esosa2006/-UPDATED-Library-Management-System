package com.LBS.Library.Management.System.dtos;

import com.LBS.Library.Management.System.enitites.ImageData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LibrarianRegistrationDto {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Incorrect email format!")
    private String email;
    @Size(min = 11, max = 11, message = "Phone number should be 11 numbers")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    private MultipartFile passport;
    @NotBlank(message = "Password is required!")
    private String password;
}
