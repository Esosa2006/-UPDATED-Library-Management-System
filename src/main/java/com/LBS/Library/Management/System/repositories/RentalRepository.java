package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rentals, Long> {
    List<Rentals> findByoverdueTrue();
}
