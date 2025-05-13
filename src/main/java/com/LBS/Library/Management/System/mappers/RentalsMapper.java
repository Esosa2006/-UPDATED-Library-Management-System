package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.RentalsDto;
import com.LBS.Library.Management.System.enitites.Rentals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalsMapper {
    @Mapping(target = "rentalName", source = "rentalName")
    RentalsDto toDto(Rentals rental);
}
