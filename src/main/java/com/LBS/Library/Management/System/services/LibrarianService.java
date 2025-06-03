package com.LBS.Library.Management.System.services;


import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.repositories.BookRepository;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LibrarianService {
    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    @Autowired
    public LibrarianService(BookRepository bookRepository, RentalRepository rentalRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public List<Book> getByAuthor(String author) {
        return bookRepository.findAllByauthor(author);
    }

    public List<Book> getByCategory(String category) {
        return bookRepository.findAllBycategory(category);
    }

    public Page<Book> viewAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }

    public Book viewSpecificBook(String bookName) {
        return bookRepository.findBybookName(bookName);
    }

    public void addNewBook(@Valid Book book) {
        if (bookRepository.existsById(book.getBookID())){
            throw new GlobalRuntimeException("Book already exists in inventory");
        }
        book.setStatus();
        bookRepository.save(book);
    }

    public ResponseEntity<Book> updateBookFields(Long id, Map<String, Object> updates) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new GlobalRuntimeException("Could not find book"));
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

    public ResponseEntity<String> deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new GlobalRuntimeException("Book not found!"));
        bookRepository.delete(book);
        return ResponseEntity.ok("Book successfully deleted");
    }

    public List<Rentals> viewOverdueRentals(){
        return rentalRepository.findByoverdueTrue();
    }

    public ResponseEntity<User> addNewUser(@Valid UserRegistrationDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone_no(userDto.getPhone_no());
        user.setUniqueID();
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    public Page<User> viewAllProfiles(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public void addSeveralBooks(Book[] listOfBooks) {
        for (Book book : listOfBooks) {
            book.setStatus();
            bookRepository.save(book);
        }
    }

    public List<Rentals> viewLibraryRentalHistory() {
        return rentalRepository.findAll();
    }
}
