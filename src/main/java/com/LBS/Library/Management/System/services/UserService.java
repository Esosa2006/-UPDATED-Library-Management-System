package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.AvailabilityStatus;
import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.mappers.BookMapper;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserService(BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    public Book getABook(String bookName) {
        return bookRepository.findBybookName(bookName);
    }

    public List<Book> getByAuthor(String author) {
        return bookRepository.findAllByauthor(author);
    }

    public List<Book> getByCategory(String category) {
        return bookRepository.findAllBycategory(category);
    }

    public ResponseEntity<String> borrowBook(String email, String bookName) {
        User user = userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);
        if(user.getEmail() == null){
            throw new IllegalStateException("User not found, please register!");
        }
        if (book.getBookName() == null){
            throw new IllegalStateException("Book not found!");
        }
        if(book.getAvailabilityStatus() == AvailabilityStatus.NOT_AVAILABLE){
            throw new IllegalStateException("Sold out!");
        }
        Integer bookQty = book.getQuantity();
        book.setQuantity(bookQty - 1);
        book.setStatus();
        book.setDateGotten();
        user.storeBorrowedBook(book);
        userRepository.save(user);
        bookRepository.save(book);
        return ResponseEntity.ok("Enjoy your book");
    }

    public List<Book> viewBorrowedHistory(String email) {
        User user = userRepository.findByemail(email);
        if(user.getEmail() == null) {
            throw new IllegalStateException("User not found!");
        }
        return user.getBorrowedBooks().stream().toList();
    }

    public ResponseEntity<String> returnBook(String email, String bookName) {
        User user =  userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);
        if (user.getName() == null){
            throw new IllegalStateException("User does not exist");
        }
        if(book.getBookName() == null){
            throw new IllegalStateException("Book does not exist");
        }
        Integer bookQty = book.getQuantity();
        if(user.isWithUser(book)){
            user.returnBook(book);
            book.setQuantity(bookQty + 1);
        }
        bookRepository.save(book);
        userRepository.save(user);
        return ResponseEntity.ok("Book has been successfully returned!");
    }
}
