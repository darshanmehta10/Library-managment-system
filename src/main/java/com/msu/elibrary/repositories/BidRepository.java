package com.msu.elibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.msu.elibrary.entities.Bid;

/**
 * @author VPatel
 */
@Repository
public interface BidRepository extends JpaRepository<Bid,Integer> {
    @Query("SELECT bid FROM Bid bid,BookCard card where bid.book = card.book AND card.user = null")
    List<Bid> findBidsInLibrary();
}
