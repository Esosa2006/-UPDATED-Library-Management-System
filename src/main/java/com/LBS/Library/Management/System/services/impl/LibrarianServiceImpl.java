package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.dtos.BookRegistrationDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookAlreadyExistsException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookNotFoundException;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import com.LBS.Library.Management.System.services.LibrarianService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Slf4j
@Service
public class LibrarianServiceImpl implements LibrarianService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LibrarianServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addNewBook(BookRegistrationDto bookRegistrationDto) {
        Book book = new Book();
        book.setBookName(bookRegistrationDto.getBookName());
        book.setAuthor(bookRegistrationDto.getAuthor());
        book.setCategory(bookRegistrationDto.getCategory());
        book.setQuantity(bookRegistrationDto.getQuantity());
        if (bookRepository.findBybookName(bookRegistrationDto.getBookName()) != null){
            log.error("Book name already exists in repo");
            throw new BookAlreadyExistsException("Book already exists in inventory");
        }
        book.setStatus();
        bookRepository.save(book);
    }

    @Override
    public ResponseEntity<Book> updateBookFields(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Could not find book"));
        if(updates.containsKey("bookName")){
            book.setBookName(String.valueOf(updates.get("bookName")));
        }
        if(updates.containsKey("author")){
            book.setAuthor(String.valueOf(updates.get("author")));
        }
        if (updates.containsKey("category")){
            book.setCategory(String.valueOf(updates.get("category")));
        }
        if (updates.containsKey("quantity")){
            book.setQuantity((Integer) updates.get("quantity"));
        }
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @Override
    public ResponseEntity<String> deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found!"));
        bookRepository.delete(book);
        return ResponseEntity.ok("Book successfully deleted");
    }

    @Override
    public Page<User> viewAllProfiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public void addSeveralBooks(Book[] listOfBooks) {
        for (Book book : listOfBooks) {
            book.setStatus();
            bookRepository.save(book);
        }
        log.info("Books successfully saved!");
    }
}
