package com.LBS.Library.Management.System.enitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Table
@Entity
@Data
public class Rentals {
    private User user;
    private Book book;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;

    public void setDateGotten(){
        this.dateBorrowed = LocalDate.now();
        this.dueDate = dateBorrowed.plusWeeks(4);
    }

}
