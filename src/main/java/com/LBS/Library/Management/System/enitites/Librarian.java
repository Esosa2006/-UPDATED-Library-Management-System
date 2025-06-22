package com.LBS.Library.Management.System.enitites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table
@Builder
public class Librarian {
    @Id
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    private Long id;
    @NotBlank(message = "Name is required!")
    @Column(name = "name", nullable = false)
    private String name;
    @Email(message = "Invalid email format detected!")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Phone number is required!")
    @Size(min = 11, message = "Phone number must be at least 11 numbers!")
    @Column(name = "phone_number", nullable = false)
    private String phone_number;
    @NotBlank(message = "Passport photograph is required")
    @OneToOne
    @JoinColumn(name = "imageId")
    private ImageData passport;


    public void setPassportPhoto(ImageData passport){
        passport.setLibrarian(this);
    }
}
