package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.UserViewBookDto;
import com.LBS.Library.Management.System.enitites.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Data
public class BookMapper {
    public UserViewBookDto toDto(Book book){
        UserViewBookDto dto = new UserViewBookDto();
        dto.setBookName(book.getBookName());
        dto.setCategory(book.getCategory());
        dto.setAuthor(book.getAuthor());
        dto.setStatus(book.getAvailabilityStatus());
        return dto;
    }
}
