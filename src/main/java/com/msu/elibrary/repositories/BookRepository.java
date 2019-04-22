package com.msu.elibrary.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msu.elibrary.entities.Book;



/**
 * @author VPatel
 */
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findByBookNameContaining(String bookName, Pageable pageable);
    Page<Book> findByAuthorsNameContaining(String authorsName, Pageable pageable);
}
