package com.LBS.Library.Management.System.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LibrarianRegistrationDto {
    private String name;
    private String email;
    private String phoneNumber;
    private MultipartFile passport;
}
