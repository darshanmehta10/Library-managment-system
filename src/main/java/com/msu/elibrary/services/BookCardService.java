package com.msu.elibrary.services;

import org.springframework.stereotype.Service;

import com.msu.elibrary.entities.Book;
import com.msu.elibrary.entities.BookCard;

import java.util.List;
import java.util.Set;

/**
 * @author VPatel
 */
@Service
public interface BookCardService {
    BookCard getBookCard(int id);
    Set<BookCard> getBookCardByBookInLibrary(Book book);
    void addBookCardToBook(Integer id,Integer bookId);
    void removeBookCard(Integer cardId);
    void saveBookCard(BookCard bookCard);
    List<BookCard> getDebtorsBookCards();
    void removeDebtor(Integer cardId);
}
