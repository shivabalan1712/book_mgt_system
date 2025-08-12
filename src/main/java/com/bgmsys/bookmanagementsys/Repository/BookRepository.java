package com.bgmsys.bookmanagementsys.Repository;

import com.bgmsys.bookmanagementsys.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
