package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.AvailabilityStatus;
import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.mappers.BookMapper;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public UserService(BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository, RentalRepository rentalRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).toList();
    }

    public BookDto getABook(String bookName) {
        Book book = bookRepository.findBybookName(bookName);
        BookDto dto = new BookDto();
        dto.setCategory(book.getCategory());
        dto.setBookName(book.getBookName());
        dto.setAuthor(book.getAuthor());
        dto.setAvailabilityStatus(book.getAvailabilityStatus());
        return dto;
    }

    public List<BookDto> getByAuthor(String author) {
        return bookRepository.findAllByauthor(author).stream().map(bookMapper::toDto).toList();
    }

    public List<BookDto> getByCategory(String category) {
        return bookRepository.findAllBycategory(category).stream().map(bookMapper::toDto).toList();
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
        Rentals rental = new Rentals();
        rental.setDateGotten();
        rental.setBook(book);
        rental.setUser(user);
        Integer bookQty = book.getQuantity();
        book.setQuantity(bookQty - 1);
        book.setStatus();
        user.storeBorrowedBook(rental);
        rentalRepository.save(rental);
        userRepository.save(user);
        bookRepository.save(book);
        return ResponseEntity.ok("Enjoy your book");
    }

    public List<Rentals> viewBorrowedHistory(String uniqueId) {
        User user = userRepository.findByuniqueID(uniqueId);
        if(user.getEmail() == null) {
            throw new IllegalStateException("User not found!");
        }
        return user.getBorrowedBooks();
    }

    public ResponseEntity<String> returnBook(String email, String bookName) {
        User user =  userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);
        Rentals rental = new Rentals();
        rental.setBook(book);
        if (user.getName() == null){
            throw new IllegalStateException("User does not exist");
        }
        if(book.getBookName() == null){
            throw new IllegalStateException("Book does not exist");
        }
        Integer bookQty = book.getQuantity();
        if(user.isWithUser(rental)){
            user.returnBook(rental);
            book.setQuantity(bookQty + 1);
        }
        rental.setDateReturned();
        rentalRepository.save(rental);
        bookRepository.save(book);
        userRepository.save(user);
        return ResponseEntity.ok("Book has been successfully returned!");
    }

    public User viewProfile(String uniqueId) {
        User user = userRepository.findByuniqueID(uniqueId);
        if(user.getEmail() == null){
            throw new IllegalStateException("User not found!");
        }
        return user;
    }
}
