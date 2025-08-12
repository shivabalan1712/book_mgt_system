package com.bgmsys.bookmanagementsys.Controller;

import com.bgmsys.bookmanagementsys.DTO.*;
import com.bgmsys.bookmanagementsys.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookControler {
    @Autowired
    BookService bookService;
    @Autowired
    private BwRnResponseDTO bwRnResponceDTO;
    @PostMapping("/book")
    public BookResponseDTO book(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.addBook(bookRequestDTO);
    }

    @PostMapping("/member")
    public MemberResponseDTO member(@RequestBody MemberRequestDTO memberRequestDTO) {
        return bookService.addMember(memberRequestDTO);
    }

//    @GetMapping("/book")
//    public BookResponseDTO getAllBook() { return bookService.getAllBook();}

    @GetMapping("/book/{id}")
    public BookResponseDTO book(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/book/{id}")
    public BookResponseDTO updateBook(@PathVariable Integer id, @RequestBody BookRequestDTO bookRequestDTO) {return bookService.updateBook(id);}

    @GetMapping("/member/{id}")
    public MemberResponseDTO member(@PathVariable Integer id) {
        return bookService.getMemberById(id);
    }

    @PostMapping("/borrow/{bookId}/{memberId}")
    public BwRnResponseDTO borrowBook(@PathVariable long bookId, @PathVariable long memberId) {
        return bookService.borrowBook(bookId, memberId);
    }

    @PostMapping("/return/{bookId}/{memberId}")
    public BwRnResponseDTO returnBook(@PathVariable long bookId, @PathVariable long memberId) {
        return bookService.returnBook(bookId, memberId);
    }
}

