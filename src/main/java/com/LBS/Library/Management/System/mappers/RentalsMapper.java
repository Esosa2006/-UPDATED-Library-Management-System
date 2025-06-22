package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.RentalsDto;
import com.LBS.Library.Management.System.enitites.Rentals;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class RentalsMapper {
    public RentalsDto toDto(Rentals rental){
        RentalsDto dto = new RentalsDto();
        dto.setRentalName(rental.getRentalName());
        dto.setDateBorrowed(rental.getDateBorrowed());
        dto.setDueDate(rental.getDueDate());
        return dto;
    };
}
