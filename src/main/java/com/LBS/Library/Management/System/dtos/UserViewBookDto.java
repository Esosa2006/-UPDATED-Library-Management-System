package com.LBS.Library.Management.System.dtos;

import com.LBS.Library.Management.System.AvailabilityStatus;
import lombok.*;

@NoArgsConstructor
@Data
public class UserViewBookDto {
    private String bookName;
    private String author;
    private String category;
    private AvailabilityStatus status;
}
