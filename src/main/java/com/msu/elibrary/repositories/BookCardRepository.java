package com.msu.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msu.elibrary.entities.Book;
import com.msu.elibrary.entities.BookCard;

import java.util.List;
import java.util.Set;

/**
 * @author VPatel
 */
@Repository
public interface BookCardRepository extends JpaRepository<BookCard,Integer> {
    @Query("SELECT bookCard FROM BookCard bookCard where bookCard.book = ?1 AND bookCard.user = null")
    Set<BookCard> getBookCardsByBookInLibrary(Book book);

    @Query("SELECT bookCard From BookCard bookCard where bookCard.user is not null")
    List<BookCard> getDebtorsBookCards();
}
