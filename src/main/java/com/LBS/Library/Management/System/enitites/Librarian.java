package com.LBS.Library.Management.System.enitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Librarian {
    private String name;
    private String email;
    private String phone_number;
}
