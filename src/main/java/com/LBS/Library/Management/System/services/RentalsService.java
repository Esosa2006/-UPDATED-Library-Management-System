package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.enitites.Rental;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RentalsService {
    Page<Rental> viewLibraryRentalHistory(int page, int size);

    List<Rental> viewOverdueRentals();
}
