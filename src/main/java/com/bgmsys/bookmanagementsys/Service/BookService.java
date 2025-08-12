package com.bgmsys.bookmanagementsys.Service;

import com.bgmsys.bookmanagementsys.DTO.*;
import com.bgmsys.bookmanagementsys.DTO.BwRnResponseDTO;
import com.bgmsys.bookmanagementsys.Mapper.BookMapperDTO;
import com.bgmsys.bookmanagementsys.Mapper.BrRnMapperDTO;
import com.bgmsys.bookmanagementsys.Mapper.MemberMapperDTO;
import com.bgmsys.bookmanagementsys.Model.Book;
import com.bgmsys.bookmanagementsys.Model.EntryInfo;
import com.bgmsys.bookmanagementsys.Model.Member;
import com.bgmsys.bookmanagementsys.Repository.BookRepository;
import com.bgmsys.bookmanagementsys.Repository.EntryInfoRepository;
import com.bgmsys.bookmanagementsys.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntryInfoRepository entryInfoRepository;
    @Autowired
    private MemberMapperDTO memberMapperDTO;
    @Autowired
    private BookMapperDTO bookMapperDTO;
    @Autowired
    private BrRnMapperDTO brRnMapperDTO;

    public BookResponseDTO addBook(BookRequestDTO bookRequestDTO) {
        Book book= BookMapperDTO.toEntity(bookRequestDTO);
        bookRepository.save(book);
        return BookMapperDTO.toDTO(book);
    }

    public MemberResponseDTO addMember(MemberRequestDTO memberRequestDTO) {
        Member member = MemberMapperDTO.toEntity(memberRequestDTO);
        memberRepository.save(member);
        return MemberMapperDTO.toDTO(member);
    }

    public BookResponseDTO getBookById(long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        return new BookResponseDTO(
                Objects.requireNonNull(book).getBookid(),
                book.getBookName(),
                book.getBookAuthor(),
                book.getTotalcopies(),
                book.getAvailablecopies()
        );
    }

    public MemberResponseDTO getMemberById(long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return new MemberResponseDTO(
                Objects.requireNonNull(member).getMemberId(),
                member.getUsername(),
                member.getPassword(),
                member.getEmail()
        );
    }

    public BwRnResponseDTO borrowBook(long bookId, long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepository.findById(memberId).orElse(null);
        if(book.getAvailablecopies()<=0)
            throw new RuntimeException("Not enough copies");

        book.setAvailablecopies(book.getAvailablecopies()-1);
        bookRepository.save(book);

        EntryInfo entryInfo = brRnMapperDTO.toEntity(member,book);
        EntryInfo entryInfo1=entryInfoRepository.save(entryInfo);
        return brRnMapperDTO.toDTO(entryInfo1);


    }

//    public BwRnResponseDTO returnBook(long bookId, long memberId) {
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new RuntimeException("Book not found"));
//
//        Member member = memberRepository.findById(memberId).orElse(null);
//        EntryInfo existingEntryInfo = entryInfoRepository
//                .finfindByBook_BookIdAndMember_MemberIdAndReturnDateIsNull(bookId, memberId)
//                .orElseThrow(() -> new RuntimeException("No active borrow record found"));
//        existingEntryInfo.setReturndate(LocalDate.now());
//        if(book.getAvailablecopies()<book.getTotalcopies()){
//            book.setAvailablecopies(book.getAvailablecopies()+1);
//            bookRepository.save(book);
//        }
//        EntryInfo savedInfo=entryInfoRepository.save(existingEntryInfo);
//        return brRnMapperDTO.toDTO(savedInfo);
//    }

}
