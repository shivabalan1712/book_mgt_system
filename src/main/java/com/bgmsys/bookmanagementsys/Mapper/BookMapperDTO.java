package com.bgmsys.bookmanagementsys.Mapper;

import com.bgmsys.bookmanagementsys.DTO.BookRequestDTO;
import com.bgmsys.bookmanagementsys.DTO.BookResponseDTO;
import com.bgmsys.bookmanagementsys.Model.Book;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookMapperDTO {

    public static Book toEntity(BookRequestDTO bookRequestDTO) {
        Book book = new Book();
        book.setName(bookRequestDTO.getName());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setTotalcopies(bookRequestDTO.getTotalCopies());
        book.setAvailablecopies(bookRequestDTO.getAvailableCopies());
        return book;

    }

    public static BookResponseDTO toDTO(Book book) {
        return new BookResponseDTO(
                book.getBookId(),
                book.getName(),
                book.getAuthor(),
                book.getTotalcopies(),
                book.getAvailablecopies()
        );

    }
}
