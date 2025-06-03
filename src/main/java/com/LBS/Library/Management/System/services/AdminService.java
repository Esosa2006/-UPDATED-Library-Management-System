package com.LBS.Library.Management.System.services;

import com.LBS.Library.Management.System.dtos.LibrarianRegistrationDto;
import com.LBS.Library.Management.System.enitites.ImageData;
import com.LBS.Library.Management.System.enitites.Librarian;
import com.LBS.Library.Management.System.exceptions.GlobalRuntimeException;
import com.LBS.Library.Management.System.repositories.LibrarianRepository;
import com.LBS.Library.Management.System.repositories.LibrarianPassportRepository;
import com.LBS.Library.Management.System.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AdminService {
    public final LibrarianPassportRepository librarianPassportRepository;
    public final LibrarianRepository librarianRepository;

    @Autowired
    public AdminService(LibrarianPassportRepository librarianPassportRepository, LibrarianRepository librarianRepository) {
        this.librarianPassportRepository = librarianPassportRepository;
        this.librarianRepository = librarianRepository;
    }

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
