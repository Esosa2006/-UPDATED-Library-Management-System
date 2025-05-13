package com.LBS.Library.Management.System.enitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table
@Entity
@Data
public class Rentals {
    @Id
    @SequenceGenerator(name = "rental_seq", sequenceName = "rental_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private LocalDate returned;
    private boolean overdue;

    public void setDateGotten(){
        this.dateBorrowed = LocalDate.now();
        this.dueDate = dateBorrowed.plusWeeks(4);
    }

    public void setDateReturned(){
        this.returned = LocalDate.now();
    }


    public void updateOverdue(){
        if(returned == null && LocalDate.now().isAfter(dueDate)){
            overdue = true;
        }
    }

    @PreUpdate
    @PrePersist
    public void updateOverdueStatus() {
        updateOverdue();
    }

}
