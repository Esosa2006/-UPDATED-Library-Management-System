package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBybookName(String bookName);
    List<Book> findAllByauthor(String author);
    List<Book> findAllBycategory(String category);
}
