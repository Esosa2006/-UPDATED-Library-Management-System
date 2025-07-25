package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.BookRegistrationDto;
import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface LibrarianService{
    void addNewBook(BookRegistrationDto bookRegistrationDto);

    ResponseEntity<Book> updateBookFields(Long id, Map<String, Object> updates);

    ResponseEntity<String> deleteBook(Long id);

    Page<User> viewAllProfiles(int page, int size);

    void addSeveralBooks(Book[] listOfBooks);





}
