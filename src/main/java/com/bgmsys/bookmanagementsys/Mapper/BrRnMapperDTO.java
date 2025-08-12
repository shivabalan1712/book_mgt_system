package com.bgmsys.bookmanagementsys.Mapper;

import com.bgmsys.bookmanagementsys.DTO.BwRnResponseDTO;
import com.bgmsys.bookmanagementsys.Model.Book;
import com.bgmsys.bookmanagementsys.Model.EntryInfo;
import com.bgmsys.bookmanagementsys.Model.Member;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
@Data
public class BrRnMapperDTO {

    public EntryInfo toEntity(Member member, Book book){
        EntryInfo entryInfo = new EntryInfo();
        entryInfo.setBook(book);
        entryInfo.setMember(member);
        entryInfo.setBorrowdate(LocalDate.now());
        return entryInfo;
    }

    public BwRnResponseDTO toDTO(EntryInfo entryInfo) {
        BwRnResponseDTO bwRnDTO = new BwRnResponseDTO();

        return new BwRnResponseDTO(
                entryInfo.getMember().getMemberId(),
                entryInfo.getMember().getUsername(),
                entryInfo.getBook().getName(),
                entryInfo.getBook().getAuthor(),
                entryInfo.getBorrowdate(),
                entryInfo.getReturndate()
        );
    }
}
