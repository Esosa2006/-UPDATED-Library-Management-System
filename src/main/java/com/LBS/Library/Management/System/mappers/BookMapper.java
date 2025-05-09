package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.enitites.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookDto toDto(Book book);
}
