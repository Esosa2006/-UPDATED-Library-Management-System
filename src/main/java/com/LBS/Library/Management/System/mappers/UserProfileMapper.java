package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.UserViewDto;
import com.LBS.Library.Management.System.enitites.User;

public class UserProfileMapper {
    public UserViewDto toDto(User user){
        UserViewDto userViewDto = new UserViewDto();
        userViewDto.setName(user.getName());
        userViewDto.setEmail(user.getEmail());
        userViewDto.setPhone_no(user.getPhone_no());
        userViewDto.setUnique_ID(user.getUniqueID());
        userViewDto.getBorrowedBooks().addAll(user.getBorrowedBooks());
        return userViewDto;
    }
}
