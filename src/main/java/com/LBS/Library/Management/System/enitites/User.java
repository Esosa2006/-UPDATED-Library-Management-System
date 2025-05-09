package com.LBS.Library.Management.System.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Random;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    private Long userId;
    private String name;
    private String uniqueID;
    private String email;
    private String phone_no;
    private ArrayList<Book> borrowedBooks;

    @PrePersist
    public void setUniqueID(String name){
        Random rand = new Random();
        rand.nextInt(0, 500);
        String part_of_name = name.substring(0, 5);
        this.uniqueID = part_of_name + rand.toString();
    }

    public void storeBorrowedBook(Book book){
        String bookName = book.getBookName();
        for(Book book_in_list: borrowedBooks){
            if (!book.getBookName().equalsIgnoreCase(bookName)){
                borrowedBooks.add(book);
            }
        }
    }
    public boolean isWithUser(Book book){
        String bookName = book.getBookName();
        for(Book book_in_list: borrowedBooks){
            return book.getBookName().equalsIgnoreCase(bookName);
        }
        return false;
    }

    public void returnBook(Book book){
        borrowedBooks.remove(book);
    }
}
