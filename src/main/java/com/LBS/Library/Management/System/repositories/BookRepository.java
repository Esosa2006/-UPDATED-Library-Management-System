package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBybookName(String bookName);
    List<Book> findAllByauthor(String author);
    List<Book> findAllBycategory(String category);
}
