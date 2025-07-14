package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.AvailabilityStatus;
import com.LBS.Library.Management.System.dtos.RentalDto;
import com.LBS.Library.Management.System.dtos.UserViewDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rental;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookLimitReachedException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookNotFoundException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookSoldOutException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserAlreadyHasBookException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserNotFoundException;
import com.LBS.Library.Management.System.mappers.RentalsMapper;
import com.LBS.Library.Management.System.mappers.UserProfileMapper;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import com.LBS.Library.Management.System.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private final RentalsMapper rentalsMapper;
    private final UserProfileMapper userProfileMapper;

    @Autowired
    public UserServiceImpl(BookRepository bookRepository, UserRepository userRepository, RentalRepository rentalRepository, RentalsMapper rentalsMapper, UserProfileMapper userProfileMapper) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.rentalsMapper = rentalsMapper;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public ResponseEntity<String> borrowBook(String email, String bookName) {
        User user = userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);
        Rental rental = new Rental();
        rental.setDateBorrowed(LocalDate.now().minusDays(1));
        rental.setDueDate(LocalDate.now());
        rental.setBook(book);
        rental.setUser(user);
        rental.setRentalName(book.getBookName());
        if(user == null){
            log.error("Email was not found in repository");
            throw new UserNotFoundException("User not found!");
        }
        if (book.getBookName() == null || book.getBookName().isEmpty()){
            log.error("No book by the bookName was found in repository");
            throw new BookNotFoundException("Book not found!");
        }
        if(book.getAvailabilityStatus() == AvailabilityStatus.NOT_AVAILABLE){
            log.error("Book quantity is 0 and is not available");
            throw new BookSoldOutException("Sold out!");
        }

        if(user.isWithUser(rental)){
            log.error("User already has this book");
            throw new UserAlreadyHasBookException("You already have this book");
        }
        Integer bookQty = book.getQuantity();
        book.setQuantity(bookQty - 1);
        book.setStatus();
        user.storeBorrowedBook(rental);
        rentalRepository.save(rental);
        userRepository.save(user);
        bookRepository.save(book);
        log.info("Book successfully borrowed");
        return ResponseEntity.ok("Enjoy your book");
    }

    @Override
    public List<RentalDto> viewBorrowedHistory(String email) {
        User user = userRepository.findByemail(email);
        if(user.getEmail() == null || user.getEmail().isEmpty()) {
            log.error("No user by this email was found");
            throw new UserNotFoundException("User not found");
        }
        return user.getBorrowedBooks().stream().map(rentalsMapper::toDto).toList();
    }

    @Override
    public ResponseEntity<String> returnBook(String email, String bookName) {
        User user = userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);

        if (user.getName() == null || user.getName().isEmpty()) {
            log.error("No user by this email was found in repository");
            throw new UserNotFoundException("User does not exist!");
        }

        if (book.getBookName() == null || book.getBookName().isEmpty()) {
            log.error("No book by this name was found in repository");
            throw new BookNotFoundException("Book does not exist!");
        }

        if(user.getBorrowedBooks().size() == 5){
            log.error("User currently has up to 5 borrowed books!");
            throw new BookLimitReachedException("You have reached the limit of borrowed books");
        }

        Rental rentalToReturn = rentalRepository
                .findByUserAndBookAndReturnedIsNull(user, book)
                .orElseThrow(() -> new GlobalRuntimeException("You do not have this book"));

        rentalToReturn.setReturned(LocalDate.now());
        book.setQuantity(book.getQuantity() + 1);
        user.returnBook(rentalToReturn);
        rentalRepository.save(rentalToReturn);
        bookRepository.save(book);
        userRepository.save(user);
        log.info("Book has been returned!");
        return ResponseEntity.ok("Book has been successfully returned!");
    }

    @Override
    public ResponseEntity<UserViewDto> viewProfile(String email) {
        User user = userRepository.findByemail(email);
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            log.error("No user with this email was found!");
            throw new UserNotFoundException("User not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userProfileMapper.toDto(user));
    }
}
