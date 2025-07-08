package com.LBS.Library.Management.System.dtos;

import com.LBS.Library.Management.System.enitites.Rental;
import com.LBS.Library.Management.System.enitites.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserViewDto {
    private String name;
    private String email;
    private String unique_ID;
    private String phone_no;
    private List<Rental> borrowedBooks = new ArrayList<>();
}
