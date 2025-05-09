package com.LBS.Library.Management.System.enitites;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Admin {
    private String name;
    private String email;
}
