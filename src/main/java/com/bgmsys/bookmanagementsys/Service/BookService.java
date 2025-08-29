package com.bgmsys.bookmanagementsys.Service;

import com.bgmsys.bookmanagementsys.DTO.*;
import com.bgmsys.bookmanagementsys.Mapper.*;
import com.bgmsys.bookmanagementsys.Model.*;
import com.bgmsys.bookmanagementsys.Repository.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;

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
    @Autowired
    private ModelMapper modelMapper;



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
                Objects.requireNonNull(book).getBookId(),
                book.getName(),
                book.getAuthor(),
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

    public BwRnResponseDTO returnBook(long bookId, long memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepository.findById(memberId).orElse(null);
        EntryInfo existingEntryInfo = entryInfoRepository
                .findByBook_BookIdAndMember_MemberIdAndReturndateIsNull(bookId, memberId)
                .orElseThrow(() -> new RuntimeException("No active borrow record found"));
        existingEntryInfo.setReturndate(LocalDate.now());
        if(book.getAvailablecopies()<book.getTotalcopies()){
            book.setAvailablecopies(book.getAvailablecopies()+1);
            bookRepository.save(book);
        }
        EntryInfo savedInfo=entryInfoRepository.save(existingEntryInfo);
        return brRnMapperDTO.toDTO(savedInfo);
    }

    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookResponseDTO.class))
                .toList();
    }

    public BookResponseDTO updateBook(long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.save(book);
        return BookMapperDTO.toDTO(book);
    }

    public List<MemberResponseDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(member ->modelMapper.map(member,MemberResponseDTO.class))
                .toList();
    }

    public MemberResponseDTO updateMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No member with this id found"));
        memberRepository.save(member);
        return MemberMapperDTO.toDTO(member);
    }
}