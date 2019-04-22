package com.msu.elibrary.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.msu.elibrary.entities.Book;
import com.msu.elibrary.entities.User;
import com.msu.elibrary.services.BidService;
import com.msu.elibrary.services.BookCardService;
import com.msu.elibrary.services.BookService;
import com.msu.elibrary.services.UserService;

/**
 * @author VPatel
 */
@Controller
public class ContentController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BidService bidService;

    @Autowired
    private BookCardService bookCardService;


    @RequestMapping("/")
    public String index(Model model, @PageableDefault(page = 1,value = 50,sort = {"bookName"})Pageable pageable,@RequestParam(value = "search",required = false)String search,@RequestParam(value = "authorSearch",required = false)String authorSearch,HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByUsername(email);
        model.addAttribute("username",user.getFirstName());
        model.addAttribute("email",user.getUsername());
        Page<Book> page;
        if (search != null) {
            page = bookService.getBooksByBookName(pageable, search);
        } else if (authorSearch != null) {
            page = bookService.getBooksByAuthorName(pageable,authorSearch);
        } else {
            page = bookService.getAllBooks(pageable);
        }
        model.addAttribute("books", page);
        return "index";
    }


    @RequestMapping("/addBid")
    public String addBid(Model model,@RequestParam(name = "bookId") int bookId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book book = bookService.getBookById(bookId);
        bidService.addBid(user,book);
        return "redirect:/";
    }

}
