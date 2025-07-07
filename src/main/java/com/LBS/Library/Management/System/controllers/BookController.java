package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public ResponseEntity<UserViewBookDto> viewSpecificBook(@RequestParam(required = true, value = "bookName") String bookName){
        return bookService.viewSpecificBook(bookName);
    }

    @GetMapping("/all")
    public Page<Book> viewAllBooks(@RequestParam (defaultValue = "0") int page,
                                   @RequestParam (defaultValue = "5") int size){
        return bookService.viewAllBooks(page, size);
    }

    @GetMapping("/book/author")
    public List<UserViewBookDto> getByAuthor(@RequestParam(value = "author", required = true)String author){
        return bookService.getByAuthor(author);
    }

    @GetMapping("/book/category")
    public List<UserViewBookDto> getByCategory(@RequestParam(value = "category", required = true) String category){
        return bookService.getByCategory(category);
    }
}
