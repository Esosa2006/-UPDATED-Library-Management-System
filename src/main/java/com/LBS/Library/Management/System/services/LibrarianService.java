package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.BookRegistrationDto;
import com.LBS.Library.Management.System.dtos.UserRegistrationDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface LibrarianService{
    List<Book> getByAuthor(String author);

    List<Book> getByCategory(String category);

    Page<Book> viewAllBooks(int page, int size);

    ResponseEntity<Book> viewSpecificBook(String bookName);

    void addNewBook(BookRegistrationDto bookRegistrationDto);

    ResponseEntity<Book> updateBookFields(Long id, Map<String, Object> updates);

    ResponseEntity<String> deleteBook(Long id);

    List<Rentals> viewOverdueRentals();

    ResponseEntity<User> addNewUser(UserRegistrationDto userDto);

    Page<User> viewAllProfiles(int page, int size);

    void addSeveralBooks(Book[] listOfBooks);

    Page<Rentals> viewLibraryRentalHistory(int page, int size);






}
