package com.bgmsys.bookmanagementsys.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
    private long bookId;
    private String bookName;
    private String author;
    private int totalCopies;
    private int availableCopies;
}
