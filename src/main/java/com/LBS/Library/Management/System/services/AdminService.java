package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.enitites.Librarian;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AdminService {
    ResponseEntity<Librarian> addNewLibrarian(LibrarianRegistrationDto dto) throws IOException;

}


