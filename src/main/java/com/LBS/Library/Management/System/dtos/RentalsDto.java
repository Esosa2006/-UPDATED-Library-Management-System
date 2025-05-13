package com.LBS.Library.Management.System.dtos;

import com.LBS.Library.Management.System.enitites.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class RentalsDto {
    private String rentalName;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;


}
