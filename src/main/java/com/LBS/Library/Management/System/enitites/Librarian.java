package com.LBS.Library.Management.System.enitites;

import com.LBS.Library.Management.System.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class Librarian {
    @Id
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phone_number;
//    @OneToOne
//    @JoinColumn(name = "imageId")
//    private ImageData passport;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private Role role;

//    public void setPassportPhoto(ImageData passport){
//        passport.setLibrarian(this);
//    }
}
