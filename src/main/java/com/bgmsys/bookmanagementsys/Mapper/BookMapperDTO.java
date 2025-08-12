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
        book.setBookName(bookRequestDTO.getBookName());
        book.setBookAuthor(bookRequestDTO.getAuthor());
        book.setTotalcopies(bookRequestDTO.getTotalCopies());
        book.setAvailablecopies(bookRequestDTO.getAvailableCopies());
        return book;

    }

    public static BookResponseDTO toDTO(Book book) {
        return new BookResponseDTO(
                book.getBookid(),
                book.getBookName(),
                book.getBookAuthor(),
                book.getTotalcopies(),
                book.getAvailablecopies()
        );

    }
}
