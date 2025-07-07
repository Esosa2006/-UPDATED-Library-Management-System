package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.RentalDto;
import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<String> borrowBook(String email, String bookName);

    List<RentalDto> viewBorrowedHistory(String uniqueId);

    ResponseEntity<String> returnBook(String email, String bookName);

    User viewProfile(String uniqueId);
}
