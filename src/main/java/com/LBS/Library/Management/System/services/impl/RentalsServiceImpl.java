package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.enitites.Rental;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalsServiceImpl implements RentalsService{
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalsServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Page<Rental> viewLibraryRentalHistory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rentalRepository.findAll(pageable);
    }

    @Override
    public List<Rental> viewOverdueRentals() {
        List<Rental> overDueRentals = new ArrayList<>();
        List<Rental> allRentals = rentalRepository.findAll().stream().toList();
        for (Rental rental : allRentals) {
            LocalDate dueDate = rental.getDueDate();
            boolean overdue = dueDate.isAfter(LocalDate.now());
            if (overdue) {
                rental.setOverdue(true);
                rentalRepository.save(rental);
                overDueRentals.add(rental);
            }
        }
        return overDueRentals;
    }
}
