package com.msu.elibrary.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msu.elibrary.entities.Bid;
import com.msu.elibrary.entities.Book;
import com.msu.elibrary.entities.User;

/**
 * @author VPatel
 */
@Service
public interface BidService {
    void addBid(User user,Book book);
    List<Bid> getAllBids();
    List<Bid> getBidsInLibrary();
    void removeBid(Integer id);
}
