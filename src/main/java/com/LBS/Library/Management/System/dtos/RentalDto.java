package com.LBS.Library.Management.System.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class RentalDto {
    private String rentalName;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
}
