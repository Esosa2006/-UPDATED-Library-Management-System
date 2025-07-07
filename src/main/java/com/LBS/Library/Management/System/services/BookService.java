package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    List<UserViewBookDto> getByAuthor(String author);

    List<UserViewBookDto> getByCategory(String category);

    Page<Book> viewAllBooks(int page, int size);

    ResponseEntity<UserViewBookDto> viewSpecificBook(String bookName);
}
