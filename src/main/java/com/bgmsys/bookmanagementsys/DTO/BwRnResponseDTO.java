package com.bgmsys.bookmanagementsys.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BwRnResponseDTO {

    private long memberId;
    private String memberName;
    private String bookName;
    private String author;
    private LocalDate borrowDate;
    private LocalDate returnDate;

}
