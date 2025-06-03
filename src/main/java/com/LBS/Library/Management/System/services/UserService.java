package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.AvailabilityStatus;
import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.dtos.RentalsDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.mappers.BookMapper;
import com.LBS.Library.Management.System.mappers.RentalsMapper;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;
    private  final RentalsMapper rentalsMapper;

    @Autowired
    public UserService(BookRepository bookRepository, BookMapper bookMapper, UserRepository userRepository, RentalRepository rentalRepository, RentalsMapper rentalsMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
        this.rentalsMapper = rentalsMapper;
    }

    public Page<BookDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable).map(bookMapper::toDto);
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
        Rentals rental = new Rentals();
        rental.setDateGotten();
        rental.setBook(book);
        rental.setUser(user);
        rental.setRentalName(book);
        if(user.getEmail() == null){
            throw new GlobalRuntimeException("User not found!");
        }
        if (book.getBookName() == null){
            throw new GlobalRuntimeException("Book not found!");
        }
        if(book.getAvailabilityStatus() == AvailabilityStatus.NOT_AVAILABLE){
            throw new GlobalRuntimeException("Sold out!");
        }

        if(user.isWithUser(rental)){
            throw new GlobalRuntimeException("You already have this book");
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

    public List<RentalsDto> viewBorrowedHistory(String uniqueId) {
        User user = userRepository.findByuniqueID(uniqueId);
        if(user.getEmail() == null) {
            throw new GlobalRuntimeException("User not found");
        }
        return user.getBorrowedBooks().stream().map(rentalsMapper::toDto).toList();
    }

    public ResponseEntity<String> returnBook(String email, String bookName) {
        User user = userRepository.findByemail(email);
        Book book = bookRepository.findBybookName(bookName);

        if (user == null || user.getName() == null) {
            throw new GlobalRuntimeException("User does not exist!");
        }

        if (book == null || book.getBookName() == null) {
            throw new GlobalRuntimeException("Book does not exist!");
        }

        if(user.getBorrowedBooks().size() == 5){
            throw new GlobalRuntimeException("You have reached the limit of borrowed books");
        }

        Rentals rentalToReturn = rentalRepository
                .findByUserAndBookAndReturnedIsNull(user, book)
                .orElseThrow(() -> new GlobalRuntimeException("You do not have this book"));

        rentalToReturn.setDateReturned();
        book.setQuantity(book.getQuantity() + 1);
        user.returnBook(rentalToReturn);
        rentalRepository.save(rentalToReturn);
        bookRepository.save(book);
        userRepository.save(user);
        return ResponseEntity.ok("Book has been successfully returned!");
    }


    public User viewProfile(String uniqueId) {
        User user = userRepository.findByuniqueID(uniqueId);
        if(user.getEmail() == null){
            throw new GlobalRuntimeException("User not found!");
        }
        return user;
    }
}
