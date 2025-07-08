package com.LBS.Library.Management.System.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRegistrationDto {
    @NotBlank(message = "Name of the book is required!")
    private String bookName;
    @NotBlank(message = "Author is required!")
    private String author;
    @NotBlank(message = "Book Genre is required!")
    private String category;
    @NotNull(message = "Quantity is required!")
    @Min(value = 0, message = "Cannot have negative values!")
    private Integer quantity;
}
