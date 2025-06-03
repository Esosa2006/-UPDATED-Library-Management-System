package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
    Librarian findByEmail(String email);
}
