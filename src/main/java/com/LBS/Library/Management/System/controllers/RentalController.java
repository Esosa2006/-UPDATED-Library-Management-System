package com.LBS.Library.Management.System.controllers;

import com.LBS.Library.Management.System.enitites.Rental;
import com.LBS.Library.Management.System.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class RentalController {
    private final RentalsService rentalsService;

    @Autowired
    public RentalController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @GetMapping("/book/overdue")
    public List<Rental> viewOverdueRentals(){
        return rentalsService.viewOverdueRentals();
    }

    @GetMapping("/inventory/rentals")
    public Page<Rental> viewLibraryRentalsHistory(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam (defaultValue = "10") int size){
        return rentalsService.viewLibraryRentalHistory(page, size);
    }
}
