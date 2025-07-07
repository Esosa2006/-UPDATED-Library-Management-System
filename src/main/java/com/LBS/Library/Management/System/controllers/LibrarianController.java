package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.BookRegistrationDto;
import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rental;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.services.LibrarianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/librarian")
public class LibrarianController {
    private final LibrarianService librarianService;
    @Autowired
    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @GetMapping("/profiles")
    public Page<User> viewAllProfiles(@RequestParam (defaultValue = "0") int page,
                                      @RequestParam (defaultValue = "10") int size){
        return librarianService.viewAllProfiles(page, size);
    }

    @PostMapping("/books/add")
    public void addNewBook(@Valid @RequestBody BookRegistrationDto bookRegistrationDto){
        librarianService.addNewBook(bookRegistrationDto);
    }
    @PostMapping("/books/addSeveral")
    public void addSeveralBooks(@Valid @RequestBody Book[] list_of_books){
        librarianService.addSeveralBooks(list_of_books);
    }

    @PostMapping("/userRegistration")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody UserRegistrationDto userDto){
        return librarianService.addNewUser(userDto);
    }

    @PatchMapping("/books/update")
    public ResponseEntity<Book> updateBookFields(
            @RequestParam(required = true, value = "id") Long id,
            @RequestBody Map<String, Object> updates
            ){
        return librarianService.updateBookFields(id, updates);
    }

    @DeleteMapping("/books/delete")
    public ResponseEntity<String> deleteBook(
            @RequestParam(required = true, value = "id") Long id
    ){
        return librarianService.deleteBook(id);
    }
}

