package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.RentalsDto;
import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    Page<UserViewBookDto> getAllBooks(int page, int size);

    UserViewBookDto getABook(String bookName);

    List<UserViewBookDto> getByAuthor(String author);

    List<UserViewBookDto> getByCategory(String category);

    ResponseEntity<String> borrowBook(String email, String bookName);

    List<RentalsDto> viewBorrowedHistory(String uniqueId);

    ResponseEntity<String> returnBook(String email, String bookName);

    User viewProfile(String uniqueId);
}
