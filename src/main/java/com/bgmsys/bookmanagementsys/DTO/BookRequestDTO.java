package com.bgmsys.bookmanagementsys.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private String name;
    private String author;
    private int totalCopies;
    private int availableCopies;
}
