package com.LBS.Library.Management.System.enitites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Librarian {
    @Id
    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
    private Long id;
    private String name;
    private String email;
    private String phone_number;
}
