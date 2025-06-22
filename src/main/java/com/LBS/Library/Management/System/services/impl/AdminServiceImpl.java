package com.LBS.Library.Management.System.services.impl;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.enitites.ImageData;
import com.LBS.Library.Management.System.enitites.Librarian;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.repositories.LibrarianPassportRepository;
import com.LBS.Library.Management.System.repositories.LibrarianRepository;
import com.LBS.Library.Management.System.repositories.RentalRepository;
import com.LBS.Library.Management.System.services.AdminService;
import com.LBS.Library.Management.System.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AdminServiceImpl implements AdminService{
    public final LibrarianPassportRepository librarianPassportRepository;
    public final LibrarianRepository librarianRepository;
    public final RentalRepository rentalRepository;

    @Autowired
    public AdminServiceImpl(LibrarianPassportRepository librarianPassportRepository, LibrarianRepository librarianRepository, RentalRepository rentalRepository) {
        this.librarianPassportRepository = librarianPassportRepository;
        this.librarianRepository = librarianRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public ResponseEntity<Librarian> addNewLibrarian(LibrarianRegistrationDto dto) throws IOException {
        Librarian librarianCheck =  librarianRepository.findByEmail(dto.getEmail());
        if (librarianCheck == null){
            throw new GlobalRuntimeException("Librarian already exists");
        }
        ImageData imageData = ImageData.builder()
                .imageName(dto.getPassport().getOriginalFilename())
                .imageType(dto.getPassport().getContentType())
                .imageData(ImageUtil.compressImage(dto.getPassport().getBytes()))
                .build();

        librarianPassportRepository.save(imageData);
        Librarian librarian = Librarian.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone_number(dto.getPhoneNumber())
                .build();
        librarian.setPassportPhoto(imageData);
        return ResponseEntity.ok(librarianRepository.save(librarian));

    }
}