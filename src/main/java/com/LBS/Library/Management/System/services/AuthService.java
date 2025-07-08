package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.dtos.LoginDto;
import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<Object> register(UserRegistrationDto userRegistrationDto);

    ResponseEntity<Object> registerLibrarian(LibrarianRegistrationDto librarianRegistrationDto);

    ResponseEntity<Object> login(LoginDto loginDto);
}
