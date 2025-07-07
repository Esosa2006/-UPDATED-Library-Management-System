package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.dtos.LoginDto;
import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.enitites.ImageData;
import com.LBS.Library.Management.System.enitites.Librarian;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserAlreadyExistsException;
import com.LBS.Library.Management.System.repositories.LibrarianPassportRepository;
import com.LBS.Library.Management.System.repositories.LibrarianRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import com.LBS.Library.Management.System.services.AuthService;
import com.LBS.Library.Management.System.services.LibrarianService;
import com.LBS.Library.Management.System.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final LibrarianRepository librarianRepository;
    private final LibrarianPassportRepository librarianPassportRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, LibrarianRepository librarianRepository, LibrarianPassportRepository librarianPassportRepository) {
        this.userRepository = userRepository;
        this.librarianRepository = librarianRepository;
        this.librarianPassportRepository = librarianPassportRepository;
    }

    @Override
    public ResponseEntity<Object> register(UserRegistrationDto userRegistrationDto) {
        User user = userRepository.findByemail(userRegistrationDto.getEmail());
        if (user != null){
            throw new UserAlreadyExistsException("This Email is already in use!");
        }
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        User newUser = new User();
        newUser.setName(userRegistrationDto.getName());
        newUser.setEmail(userRegistrationDto.getEmail());
        newUser.setPhone_no(userRegistrationDto.getEmail());
        newUser.setPassword(encoder.encode(userRegistrationDto.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
    }

    @Override
    public ResponseEntity<Object> registerLibrarian(LibrarianRegistrationDto librarianRegistrationDto) throws IOException {
        Librarian librarian = librarianRepository.findByEmail(librarianRegistrationDto.getEmail());
        if (librarian != null){
            throw new UserAlreadyExistsException("This Email is already in use!");
        }
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        Librarian newLibrarian = new Librarian();
        ImageData imageData = ImageData.builder()
                .imageName(librarianRegistrationDto.getPassport().getOriginalFilename())
                .imageType(librarianRegistrationDto.getPassport().getContentType())
                .imageData(ImageUtil.compressImage(librarianRegistrationDto.getPassport().getBytes()))
                .build();
        newLibrarian.setName(librarianRegistrationDto.getName());
        newLibrarian.setEmail(librarianRegistrationDto.getEmail());
        newLibrarian.setPhone_number(librarianRegistrationDto.getPhoneNumber());
        newLibrarian.setPassword(encoder.encode(librarianRegistrationDto.getPassword()));
        newLibrarian.setPassportPhoto(imageData);
        return ResponseEntity.status(HttpStatus.CREATED).body(librarianRepository.save(newLibrarian));
    }


    @Override
    public ResponseEntity<Object> login(LoginDto loginDto) {
        return null;
    }
}
