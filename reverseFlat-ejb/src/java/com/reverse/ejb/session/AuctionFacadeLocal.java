/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.session;

import com.reverse.ejb.exception.ClosedAuctionException;
import com.reverse.ejb.exception.NoBidsException;
import com.reverse.ejb.exception.NoChipsException;
import com.reverse.ejb.exception.NoUniqueBidsException;
import com.reverse.ejb.exception.NoWinnerException;
import com.reverse.ejb.exception.RangeException;
import com.reverse.ejb.persistence.Auction;
import com.reverse.ejb.persistence.Auctioncategory;
import com.reverse.ejb.persistence.Bid;
import com.reverse.ejb.persistence.Chipsexpenditure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pipe
 */
@Local
public interface AuctionFacadeLocal {

    void create(Auction auction);

    void edit(Auction auction);

    void remove(Auction auction);

    Auction find(Object id);

    List<Auction> findAll();
    
    List<Auction> findActiveAuctions();

    List<Auction> findTopAuctions();

    List<Auction> findClosedAuctions();

    List<Chipsexpenditure> findActiveAuctionsByUser(Integer idUser);

    List<Auction> findAuctionsByLastActivity();

    List<Auction> findClosedAuctionsByUser(Integer idUser);

    List<Auction> findAuctionsByUser(Integer idUser, String type);

    void activateAuction(Integer idAuction);

    void singleBid(Bid bid) throws NoChipsException, ClosedAuctionException;

    void rangeBid(Bid startingBid, Bid finishingBid) throws NoChipsException, RangeException, ClosedAuctionException;

    /**
     * Returns the winner bid in some auction
     * @param idAuction
     * @return idBid, bidDate, bidAmount, user_idUser, auction_idAuction, count(bidAmount) as n
     */
    Bid getWinnerBid (Integer idAuction) throws NoWinnerException;

    int getBidCost(Integer idAuction, Integer totalNumbers);

    float[] getProgressBar(Integer idAuction);
    
    String getUniqueBidsByUser(Integer idAuction, Integer idUser) throws NoUniqueBidsException;
    
    Bid getLastBidByUser(Integer idAuction, Integer idUser) throws NoBidsException;

    Chipsexpenditure getLastChipsExpenditureByUser(Integer idAuction, Integer idUser) throws NoBidsException;

    Auction getLastAuctionByUser(Integer idUser);

    List<Auction> getEnrolledAuctionsByUser(Integer idUser);

    public List<Bid> getBidsByUserAndAuction(Integer idAuction, Integer idUser) throws NoBidsException;

    List<Auctioncategory> getCategories ();

    List<Auction> findAuctionsByCategory(Integer idCategory);
}
