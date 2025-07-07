package com.LBS.Library.Management.System.enitites;

import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    private Long userId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "uniqueID")
    private String uniqueID;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "phone_no", nullable = false)
    private String phone_no;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rental> borrowedBooks = new ArrayList<>();



    public void setUniqueID(){
        Random rand = new Random();
        int randomNum = rand.nextInt(500);
        String part_of_name = name.substring(0, 5);
        this.uniqueID = part_of_name + randomNum;
    }


    public void storeBorrowedBook(Rental rental){
        String rentalName = rental.getBook().getBookName();
        if(borrowedBooks.isEmpty() || (!borrowedBooks.contains(rental))){
            borrowedBooks.add(rental);
        }
        else{
            throw new GlobalRuntimeException("You already have this book");
        }
    }
    public boolean isWithUser(Rental rental) {
        Book incomingBook = rental.getBook();

        for (Rental r : borrowedBooks) {
            Book borrowedBook = r.getBook();
            if (borrowedBook.getBookID().equals(incomingBook.getBookID()) && r.getReturned() == null) {
                return true;
            }
        }

        return false;
    }


    public void returnBook(Rental rental){
        borrowedBooks.remove(rental);
    }


}
