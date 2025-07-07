package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.dtos.LoginDto;
import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<Object> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto){
        return authService.register(userRegistrationDto);
    }

    public ResponseEntity<Object> registerLibrarian(@Valid @RequestBody LibrarianRegistrationDto librarianRegistrationDto) throws IOException {
        return authService.registerLibrarian(librarianRegistrationDto);
    }

    public ResponseEntity<Object> login(@Valid LoginDto loginDto){
        return authService.login(loginDto);
    }
}

