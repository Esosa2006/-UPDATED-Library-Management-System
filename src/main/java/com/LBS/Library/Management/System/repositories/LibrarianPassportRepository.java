package com.LBS.Library.Management.System.repositories;

import com.LBS.Library.Management.System.enitites.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianPassportRepository extends JpaRepository<ImageData, Long> {
}
