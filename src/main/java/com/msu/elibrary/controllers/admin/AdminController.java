package com.msu.elibrary.controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msu.elibrary.controllers.dto.BookDTO;
import com.msu.elibrary.entities.Author;
import com.msu.elibrary.entities.Bid;
import com.msu.elibrary.entities.BookCard;
import com.msu.elibrary.entities.User;
import com.msu.elibrary.exceptions.FileExistException;
import com.msu.elibrary.services.BidService;
import com.msu.elibrary.services.BookCardService;
import com.msu.elibrary.services.BookService;
import com.msu.elibrary.services.UDKService;
import com.msu.elibrary.services.UserService;

/**
 * @author VPatel
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UDKService udkService;
    @Autowired
    private BookCardService bookCardService;
    @Autowired
    private BidService bidService;


    private Logger log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "removeDebtor")
    public String removeDebtor(@RequestParam(value = "cardId") Integer cardId) {
        bookCardService.removeDebtor(cardId);
        return "redirect:/admin/debtors";
    }

    @RequestMapping(value = "debtors")
    public String getDebtors(Model model) {
        model.addAttribute("bookCards", bookCardService.getDebtorsBookCards());
        return "debtors";
    }

    @RequestMapping(value = "getBookCards/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Set<BookCard> getBookCards(@PathVariable Integer id) {
        Set<BookCard> bookCards = bookService.getBookById(id).getBookCards();
        return bookCards;
    }

    @RequestMapping("removeBid")
    public String removeBid(@RequestParam(name = "bidId") Integer bidId) {
        bidService.removeBid(bidId);
        return "redirect:/admin/viewBids";
    }

    @RequestMapping("giveBook")
    public String giveBookForUser(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "cardId") Integer cardId,@RequestParam(name = "bidId") Integer bidId) {
        User user = userService.getUserById(userId);
        BookCard bookCard = bookCardService.getBookCard(cardId);
        bookCard.setUser(user);
        bookCard.setIssueDate(Calendar.getInstance());
        bookCardService.saveBookCard(bookCard);
        bidService.removeBid(bidId);
        return "redirect:/admin/viewBids";
    }

    @RequestMapping("viewBids")
    public String viewBids(Model model) {
        List<Bid> bids = bidService.getAllBids();
        for(Bid bid : bids) {
            bid.getBook().setBookCards(bookCardService.getBookCardByBookInLibrary(bid.getBook()));
        }
        model.addAttribute("bids",bids);
        return "bids";
    }

    @RequestMapping("removeBookCard")
    public String removeBookCard(@RequestParam(name = "cardId") Integer cardId) {
        bookCardService.removeBookCard(cardId);
        return "redirect:/";
    }

    @RequestMapping("/addBookCard")
    public String addBookCard(@RequestParam(name = "bookId") Integer bookId, @RequestParam(name = "cardId") Integer cardId) {
        bookCardService.addBookCardToBook(cardId,bookId);
        return "redirect:/";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(Model model,@PathVariable(value = "id") Integer id) {
        BookDTO bookDTO = new BookDTO(bookService.getBookById(id));
        model.addAttribute("udk",udkService.getAllUDK());
        model.addAttribute("book",bookDTO);
        model.addAttribute("edit",true);
        return "addBook";
    }

    @RequestMapping("/removeBook/{id}")
    public String removeBook(Model model,@PathVariable(value = "id") Integer id) {
        bookService.removeBookById(id);
        return "redirect:/";
    }

    @RequestMapping("/addBook")
    public String addBook(Model model) {
        BookDTO book = new BookDTO();
        book.setAuthors(new ArrayList<>());
        book.getAuthors().add(new Author("","",""));
        model.addAttribute("book",book);
        model.addAttribute("udk",udkService.getAllUDK());
        model.addAttribute("edit",false);
        return "addBook";
    }

    @RequestMapping("/saveBook")
    public String saveBook(@ModelAttribute("book")@Valid BookDTO book, BindingResult bindingResult,@ModelAttribute("edit")boolean edit, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("udk",udkService.getAllUDK());
            return "addBook";
        }
        try {
            bookService.addBook(book,edit);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "File uploaded failed:";
        } catch (IOException e) {
            e.printStackTrace();
            return "File uploaded failed:";
        } catch (FileExistException e) {
            log.warn("File exist");
            saveBook(book,bindingResult,edit,model);
        }
        System.out.println(edit);
        if (edit) {
            return "redirect:/";
        } else {
            return "redirect:/admin/addBook";
        }
//        return "addBook";
    }

}
