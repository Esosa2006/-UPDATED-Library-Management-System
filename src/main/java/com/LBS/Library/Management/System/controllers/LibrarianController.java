package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("/books")
    public List<Book> viewAllBooks(){return librarianService.viewAllBooks();}

    @GetMapping("/book")
    public Book viewSpecificBook(@RequestParam(required = true, value = "bookName") String bookName){
        return librarianService.viewSpecificBook(bookName);
    }

    @GetMapping("/profiles")
    public List<User> viewAllProfiles(){
        return librarianService.viewAllProfiles();
    }

    @PostMapping("/books/add")
    public void addNewBook(@RequestBody Book book){
        librarianService.addNewBook(book);
    }
    @PostMapping("/books/addSeveral")
    public void addSeveralBooks(@RequestBody Book[] list_of_books){
        librarianService.addSeveralBooks(list_of_books);
    }

    @PostMapping("/userRegistration")
    public ResponseEntity<User> addNewUser(@RequestBody UserRegistrationDto userDto){
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
    @GetMapping("/book/author")
    public List<Book> getByAuthor(@RequestParam(value = "author", required = true)String author){
        return librarianService.getByAuthor(author);
    }

    @GetMapping("/book/category")
    public List<Book> getByCategory(@RequestParam(value = "category", required = true) String category){
        return librarianService.getByCategory(category);
    }

    @GetMapping("/book/overdue")
    public List<Rentals> viewOverdueRentals(){
        return librarianService.viewOverdueRentals();
    }

    @GetMapping("/inventory/rentals")
    public List<Rentals> viewLibraryRentalsHistory(){
        return librarianService.viewLibraryRentalHistory();
    }
}

