package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.Book;
import com.LBS.Library.Management.System.enitites.Rentals;
import com.LBS.Library.Management.System.enitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rentals, Long> {
    List<Rentals> findByoverdueTrue();
    Optional<Rentals> findByUserAndBookAndReturnedIsNull(User user, Book book);

}
