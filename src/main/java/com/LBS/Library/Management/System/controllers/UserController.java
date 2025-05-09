package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/books")
    public List<BookDto> getAllBooks(){
        return userService.getAllBooks();
    }
    @GetMapping("/book")
    public Book getABook(@RequestParam(value = "bookName", required = true) String bookName){
        return userService.getABook(bookName);
    }

    @GetMapping("/book/author")
    public List<Book> getByAuthor(@RequestParam(value = "author", required = true)String author){
        return userService.getByAuthor(author);
    }

    @GetMapping("/book/category")
    public List<Book> getByCategory(@RequestParam(value = "category", required = true) String category){
        return userService.getByCategory(category);
    }

    @GetMapping("/viewHistory")
    public List<Book> viewBorrowedHistory(@RequestParam(value = "email", required = true) String email){
        return userService.viewBorrowedHistory(email);
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

