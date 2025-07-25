package com.LBS.Library.Management.System.enitites;

import com.LBS.Library.Management.System.enums.Role;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Admin {
    private String name = "admin";
    private String email = "adminL@gmail.com";
    private Role role = Role.ADMIN;
}
