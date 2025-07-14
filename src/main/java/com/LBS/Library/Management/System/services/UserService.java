package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.RentalDto;
import com.LBS.Library.Management.System.dtos.UserViewDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> borrowBook(String email, String bookName);

    List<RentalDto> viewBorrowedHistory(String uniqueId);

    ResponseEntity<String> returnBook(String email, String bookName);

    ResponseEntity<UserViewDto> viewProfile(String email);
}
