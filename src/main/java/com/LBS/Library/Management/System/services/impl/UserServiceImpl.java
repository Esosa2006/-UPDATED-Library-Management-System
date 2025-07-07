package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.AvailabilityStatus;
import com.LBS.Library.Management.System.dtos.RentalDto;
import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rental;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookLimitReachedException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookNotFoundException;
import com.LBS.Library.Management.System.exceptions.bookExceptions.BookSoldOutException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserAlreadyHasBookException;
import com.LBS.Library.Management.System.exceptions.userExceptions.UserNotFoundException;
import com.LBS.Library.Management.System.mappers.BookMapper;
import com.LBS.Library.Management.System.mappers.RentalsMapper;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import com.LBS.Library.Management.System.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private  final RentalsMapper rentalsMapper;

    @Autowired
    public UserServiceImpl(BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository, RentalRepository rentalRepository, RentalsMapper rentalsMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.rentalsMapper = rentalsMapper;
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
        if(user.getEmail() == null){
            throw new UserNotFoundException("User not found!");
        }
        if (book.getBookName() == null){
            throw new BookNotFoundException("Book not found!");
        }
        if(book.getAvailabilityStatus() == AvailabilityStatus.NOT_AVAILABLE){
            throw new BookSoldOutException("Sold out!");
        }

        if(user.isWithUser(rental)){
            throw new UserAlreadyHasBookException("You already have this book");
        }
        Integer bookQty = book.getQuantity();
        book.setQuantity(bookQty - 1);
        book.setStatus();
        user.storeBorrowedBook(rental);
        rentalRepository.save(rental);
        userRepository.save(user);
        bookRepository.save(book);
        return ResponseEntity.ok("Enjoy your book");
    }

    @Override
    public List<RentalDto> viewBorrowedHistory(String uniqueId) {
        User user = userRepository.findByuniqueID(uniqueId);
        if(user.getEmail() == null) {
            throw new UserNotFoundException("User not found");
        }
        return user.getBorrowedBooks().stream().map(rentalsMapper::toDto).toList();
    }

    @Override
    public ResponseEntity<String> returnBook(String email, String bookName) {
        User user = userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);

        if (user == null || user.getName() == null) {
            throw new UserNotFoundException("User does not exist!");
        }

        if (book == null || book.getBookName() == null) {
            throw new BookNotFoundException("Book does not exist!");
        }

        if(user.getBorrowedBooks().size() == 5){
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
        return ResponseEntity.ok("Book has been successfully returned!");
    }

    @Override
    public User viewProfile(String uniqueId) {
        User user = userRepository.findByuniqueID(uniqueId);
        if(user.getEmail() == null){
            throw new UserNotFoundException("User not found!");
        }
        return user;
    }
}
