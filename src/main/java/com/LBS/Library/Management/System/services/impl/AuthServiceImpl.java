package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.dtos.LoginDto;
import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.enitites.ImageData;
import com.LBS.Library.Management.System.enitites.Librarian;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.enums.Role;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserAlreadyExistsException;
import com.LBS.Library.Management.System.repositories.LibrarianPassportRepository;
import com.LBS.Library.Management.System.repositories.LibrarianRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import com.LBS.Library.Management.System.security.JWTService;
import com.LBS.Library.Management.System.services.AuthService;
import com.LBS.Library.Management.System.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final LibrarianRepository librarianRepository;
    private final LibrarianPassportRepository librarianPassportRepository;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, LibrarianRepository librarianRepository, LibrarianPassportRepository librarianPassportRepository, AuthenticationManager authManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.librarianRepository = librarianRepository;
        this.librarianPassportRepository = librarianPassportRepository;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Object> register(UserRegistrationDto userRegistrationDto) {
        User user = userRepository.findByemail(userRegistrationDto.getEmail());
        if (user != null){
            log.error("User email does not exists in repo");
            throw new UserAlreadyExistsException("This Email is already in use!");
        }
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        User newUser = new User();
        newUser.setName(userRegistrationDto.getName());
        newUser.setEmail(userRegistrationDto.getEmail());
        newUser.setPhone_no(userRegistrationDto.getPhone_no());
        newUser.setRole(Role.CUSTOMER);
        newUser.setUniqueID();
        newUser.setPassword(encoder.encode(userRegistrationDto.getPassword()));
        userRepository.save(newUser);
        log.info("New User created!");
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!");
    }

    @Override
    public ResponseEntity<Object> registerLibrarian(LibrarianRegistrationDto librarianRegistrationDto){
        Librarian librarian = librarianRepository.findByEmail(librarianRegistrationDto.getEmail());
        if (librarian != null){
            log.error("This email already exists in repo");
            throw new UserAlreadyExistsException("This Email is already in use!");
        }
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        Librarian newLibrarian = new Librarian();
//        ImageData imageData = ImageData.builder()
//                .imageName(librarianRegistrationDto.getPassport().getOriginalFilename())
//                .imageType(librarianRegistrationDto.getPassport().getContentType())
//                .imageData(ImageUtil.compressImage(librarianRegistrationDto.getPassport().getBytes()))
//                .build();
        newLibrarian.setName(librarianRegistrationDto.getName());
        newLibrarian.setEmail(librarianRegistrationDto.getEmail());
        newLibrarian.setPhone_number(librarianRegistrationDto.getPhoneNumber());
        newLibrarian.setPassword(encoder.encode(librarianRegistrationDto.getPassword()));
        newLibrarian.setRole(Role.LIBRARIAN);
        librarianRepository.save(newLibrarian);
//        newLibrarian.setPassportPhoto(imageData);
//        librarianPassportRepository.save(imageData);
        log.info("Librarian created");
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }


    @Override
    public ResponseEntity<Object> login(LoginDto loginDto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if (!authentication.isAuthenticated()){
            log.error("User authentication failed!");
            throw new GlobalRuntimeException("Authentication Failed!");
        }
        log.info("Authentication successful!");
        return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(loginDto.getEmail()));

    }
}
