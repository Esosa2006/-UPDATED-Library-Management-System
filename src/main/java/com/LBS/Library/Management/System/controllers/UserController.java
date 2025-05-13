package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/books")
    public List<BookDto> getAllBooks(){
        return userService.getAllBooks();
    }

    @GetMapping("/book")
    public BookDto getABook(@RequestParam(value = "bookName", required = true) String bookName){
        return userService.getABook(bookName);
    }

    @GetMapping("/book/author")
    public List<BookDto> getByAuthor(@RequestParam(value = "author", required = true)String author){
        return userService.getByAuthor(author);
    }

    @GetMapping("/book/category")
    public List<BookDto> getByCategory(@RequestParam(value = "category", required = true) String category){
        return userService.getByCategory(category);
    }

    @GetMapping("/viewHistory")
    public List<Rentals> viewBorrowedHistory(@RequestParam(value = "uniqueId", required = true) String uniqueId){
        return userService.viewBorrowedHistory(uniqueId);
    }
    @GetMapping("/profile")
    public User viewProfile(
            @RequestParam(value = "uniqueId", required = true) String uniqueId
    ){
        return userService.viewProfile(uniqueId);
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<String> borrowBook(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "bookName", required = true) String bookName
            ) {
        return userService.borrowBook(email, bookName);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "bookName", required = true) String bookName
    ){
        return userService.returnBook(email,bookName);
    }


}

