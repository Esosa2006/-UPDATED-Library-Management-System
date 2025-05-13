package com.LBS.Library.Management.System.mappers;

import com.LBS.Library.Management.System.dtos.BookDto;
import com.LBS.Library.Management.System.enitites.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "bookName", source = "bookName")
    BookDto toDto(Book book);
}
