package com.msu.elibrary.services.impl;

import com.msu.elibrary.entities.Book;
import com.msu.elibrary.entities.BookCard;
import com.msu.elibrary.repositories.BookCardRepository;
import com.msu.elibrary.repositories.BookRepository;
import com.msu.elibrary.services.BookCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author VPatel
 */
@Service
public class BookCardServiceImpl implements BookCardService {
    @Autowired
    private BookCardRepository bookCardRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public BookCard getBookCard(int id) {
        return bookCardRepository.findOne(id);
    }


    public void addBookCardToBook(Integer id,Integer bookId) {
        Book book = bookRepository.findOne(bookId);
        BookCard bookCard = new BookCard();
        bookCard.setId(id);
        bookCard.setBook(book);
        bookCardRepository.saveAndFlush(bookCard);
       }

    @Override
    public void removeBookCard(Integer cardId) {
        bookCardRepository.delete(cardId);
    }

    @Override
    public Set<BookCard> getBookCardByBookInLibrary(Book book) {
        return bookCardRepository.getBookCardsByBookInLibrary(book);
    }

    @Override
    public void saveBookCard(BookCard bookCard) {
        bookCardRepository.saveAndFlush(bookCard);
    }

    @Override
    public List<BookCard> getDebtorsBookCards() {
        return bookCardRepository.getDebtorsBookCards();
    }

    @Override
    public void removeDebtor(Integer cardId) {
        BookCard bookCard = bookCardRepository.findOne(cardId);
        bookCard.setUser(null);
        bookCardRepository.saveAndFlush(bookCard);
    }
}
