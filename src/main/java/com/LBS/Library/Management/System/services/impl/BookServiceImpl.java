package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.exceptions.bookExceptions.AuthorNotFoundException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookNotFoundException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.CategoryNotFoundException;
import com.LBS.Library.Management.System.mappers.BookMapper;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<UserViewBookDto> getByAuthor(String author) {
        if (bookRepository.findAllByauthor(author).isEmpty()){
            log.error("Author does not exists in repo");
            throw new AuthorNotFoundException("No author found");
        }
        return bookRepository.findAllByauthor(author).stream().map(bookMapper::toDto).toList();
    }

    @Override
    public List<UserViewBookDto> getByCategory(String category) {
        if (bookRepository.findAllBycategory(category).isEmpty()){
            log.error("Category does not exist in repo!");
            throw new CategoryNotFoundException("No category found");
        }
        return bookRepository.findAllBycategory(category).stream().map(bookMapper::toDto).toList();
    }

    @Override
    public Page<Book> viewAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<UserViewBookDto> viewSpecificBook(String bookName) {
        Book book = bookRepository.findBybookName(bookName);
        if (book == null){
            log.info("Book name not found in repository");
            throw new BookNotFoundException("Book not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookMapper.toDto(book));
    }
}
