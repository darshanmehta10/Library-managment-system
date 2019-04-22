package com.msu.elibrary.services;

import com.msu.elibrary.controllers.dto.BookDTO;
import com.msu.elibrary.entities.Book;
import com.msu.elibrary.exceptions.FileExistException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author VPatel
 */
@Service
public interface BookService {
    List<Book> getAllBooks();
    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> getBooksByBookName(Pageable pageable,String bookName);
    Page<Book> getBooksByAuthorName(Pageable pageable,String authorName);
    Book getBookById(Integer bookId);
    void addBook(BookDTO book,boolean editMode) throws IOException, FileExistException;
    void removeBookById(Integer id);
}
