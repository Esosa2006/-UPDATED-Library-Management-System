package com.LBS.Library.Management.System.enitites;

import com.LBS.Library.Management.System.AvailabilityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    private Long bookID;
    private String bookName;
    private String author;
    private String category;
    private Integer quantity;
    @Transient
    private AvailabilityStatus availabilityStatus;


    public void setStatus(){
        if(quantity > 0 ){
            this.availabilityStatus = AvailabilityStatus.AVAILABLE;
        }
        else{
            this.availabilityStatus = AvailabilityStatus.NOT_AVAILABLE;
        }
    }
    public void setDateGotten(){
        this.dateBorrowed = LocalDate.now();
    }
    public void setDueDate(){
        String date = dateBorrowed.toString();

    }
}
