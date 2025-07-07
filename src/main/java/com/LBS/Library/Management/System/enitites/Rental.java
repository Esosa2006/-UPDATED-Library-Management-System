package com.LBS.Library.Management.System.enitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Table
@Entity
@Data
public class Rental {
    @Id
    @SequenceGenerator(name = "rental_seq", sequenceName = "rental_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @ToString.Exclude
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private Book book;
    private String rentalName;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private LocalDate returned;
    private boolean overdue;
}
