package com.LBS.Library.Management.System.enitites;

import com.LBS.Library.Management.System.AvailabilityStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    @NotBlank(message = "Name of the book is required!")
    @Column(name = "bookName", nullable = false)
    private String bookName;
    @NotBlank(message = "Author is required!")
    @Column(name = "author", nullable = false)
    private String author;
    @NotBlank(message = "Book Genre is required!")
    @Column(name = "category", nullable = false)
    private String category;
    @NotBlank(message = "Quantity is required!")
    @Min(value = 0, message = "Quantity cannot be a negative value!")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "availabilityStatus", nullable = false)
    private AvailabilityStatus availabilityStatus;

    public void setStatus(){
        if(quantity > 0 ){
            this.availabilityStatus = AvailabilityStatus.AVAILABLE;
        }
        else{
            this.availabilityStatus = AvailabilityStatus.NOT_AVAILABLE;
        }
    }
}
