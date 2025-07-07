package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.RentalDto;
import com.LBS.Library.Management.System.enitites.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class RentalsMapper {
    public RentalDto toDto(Rental rental){
        RentalDto dto = new RentalDto();
        dto.setRentalName(rental.getRentalName());
        dto.setDateBorrowed(rental.getDateBorrowed());
        dto.setDueDate(rental.getDueDate());
        return dto;
    };
}
