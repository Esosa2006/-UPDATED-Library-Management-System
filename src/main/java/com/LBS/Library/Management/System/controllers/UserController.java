package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.BookNameRequest;
import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.dtos.RentalDto;
import com.LBS.Library.Management.System.dtos.UserViewDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/viewHistory")
    public List<RentalDto> viewBorrowedHistory(Authentication authentication){
        String email = authentication.getName();
        return userService.viewBorrowedHistory(email);
    }
    @GetMapping("/profile")
    public UserViewDto viewProfile(Authentication authentication){
        String email = authentication.getName();
        return userService.viewProfile(email);
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<String> borrowBook(
            Authentication authentication,
            @RequestBody BookNameRequest bookNameRequest
            ) {
        String bookName = bookNameRequest.getName();
        String email = authentication.getName();
        return userService.borrowBook(email, bookName);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(
            Authentication authentication,
            @RequestBody BookNameRequest bookNameRequest
            ){
        String bookName = bookNameRequest.getName();
        String email = authentication.getName();
        return userService.returnBook(email,bookName);
    }


}

