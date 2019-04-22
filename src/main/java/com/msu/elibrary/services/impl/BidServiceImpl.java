package com.msu.elibrary.services.impl;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msu.elibrary.entities.Bid;
import com.msu.elibrary.entities.Book;
import com.msu.elibrary.entities.User;
import com.msu.elibrary.repositories.BidRepository;
import com.msu.elibrary.services.BidService;

/**
 * @author VPatel
 */
@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private BidRepository bidRepository;

    @Override
    public void addBid(User user, Book book) {
        Bid bid = new Bid();
        bid.setUser(user);
        bid.setBook(book);
        bid.setBidDate(new GregorianCalendar());
        bidRepository.saveAndFlush(bid);
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public List<Bid> getBidsInLibrary() {
        return bidRepository.findBidsInLibrary();
    }

    @Override
    public void removeBid(Integer id) {
        bidRepository.delete(id);
    }
}
