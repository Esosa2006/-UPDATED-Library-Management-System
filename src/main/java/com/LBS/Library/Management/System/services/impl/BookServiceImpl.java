package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return bookRepository.findAllByauthor(author);
    }

    @Override
    public List<Book> getByCategory(String category) {
        return bookRepository.findAllBycategory(category);
    }

    @Override
    public Page<Book> viewAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<Book> viewSpecificBook(String bookName) {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findBybookName(bookName));
    }
}
