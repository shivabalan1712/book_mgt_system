package com.bgmsys.bookmanagementsys.Model;

import com.bgmsys.bookmanagementsys.DTO.BookResponseDTO;
import com.bgmsys.bookmanagementsys.Repository.BookRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String name;
    private String author;
    private int totalcopies;
    private int availablecopies;

}
