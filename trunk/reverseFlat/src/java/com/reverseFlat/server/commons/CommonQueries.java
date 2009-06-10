/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.server.commons;

import com.reverseFlat.ejb.session.AuctionFacadeLocal;
import com.reverseFlat.ejb.exception.NoBidsException;
import com.reverseFlat.ejb.exception.NoUniqueBidsException;
import com.reverseFlat.ejb.exception.NoWinnerException;
import com.reverseFlat.ejb.persistence.Auction;
import com.reverseFlat.ejb.persistence.Auctioncategory;
import com.reverseFlat.ejb.persistence.Bid;
import com.reverseFlat.ejb.persistence.Chipsexpenditure;
import com.reverseFlat.ejb.persistence.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Pipe
 */
public class CommonQueries {

    private Locale locale;
    private ResourceBundle  messages;

    /**
     *
     * @param request
     * @param realPath
     * @param type active, closed, all
     * @return
     */
    public List<Auction> getAuctionList(AuctionFacadeLocal auctionsFacade, HttpServletRequest request, String realPath, String type) {

        String errorMessage = "";
        messages = (ResourceBundle) request.getSession().getAttribute("messages");
        List auctionsList = null;
        if ("active".equals(type)){
            errorMessage = messages.getString("NoOpenAuctions");
            auctionsList = auctionsFacade.findActiveAuctions();
        }else if ("closed".equals(type)){
            auctionsList = auctionsFacade.findClosedAuctions();
        }else if ("all".equals(type)){
            auctionsList = auctionsFacade.findAll();
        }else if ("lastActivity".equals(type)){
            auctionsList = auctionsFacade.findAuctionsByLastActivity();
        }else if ("enrolledAuctions".equals(type)){
            User user = (User) request.getSession().getAttribute("user");
            if (user != null){
                auctionsList = auctionsFacade.getEnrolledAuctionsByUser(user.getIdUser());
            }else{
                return null;
            }
        }

        if ( auctionsList.size() == 0  ){
            Logger.getLogger(CommonQueries.class.getName()).log(Level.INFO, errorMessage);
            return null;
        }
        List<Auction> activeAuctions = new ArrayList();
        Iterator iterator = auctionsList.iterator();
        for(int i=0; iterator.hasNext(); i++){
            Auction auction = (Auction) iterator.next();
            //activeAuctions[i] = Commons.convertAuctionToAuctionVO( auction, (String) request.getSession().getAttribute("locale") );

            String circledPath = realPath + auction.getCircledFileName();
            Commons.convertBytesToFile(auction.getCircledPicture(), circledPath);
            

            String productPicPath = realPath + auction.getPictureFileName();
            Commons.convertBytesToFile(auction.getPicture(), productPicPath);

            String mosaicPicPath = realPath + auction.getMosaicFileName();
            Commons.convertBytesToFile(auction.getMosaicPicture(), mosaicPicPath);

            activeAuctions.add(auction);
        }
        return activeAuctions;
    }

    public List<Chipsexpenditure> getActiveAuctionsByUser(AuctionFacadeLocal auctionsFacade, Integer idUser){
        return auctionsFacade.findActiveAuctionsByUser(idUser);
    }

    public Auction getLastAuctionByUser(AuctionFacadeLocal auctionsFacade, Integer idUser){
        return auctionsFacade.getLastAuctionByUser(idUser);
    }

    public Bid getLastBidByUser(AuctionFacadeLocal auctionsFacade, Integer idAuction, Integer idUser) throws NoBidsException{
        return auctionsFacade.getLastBidByUser(idAuction, idUser);
    }

    public String getUniqueBidsByUser(AuctionFacadeLocal auctionsFacade, Integer idAuction, Integer idUser) throws NoUniqueBidsException{
        return auctionsFacade.getUniqueBidsByUser(idAuction, idUser);
    }

    public Bid getWinnerBid(AuctionFacadeLocal auctionsFacade, Integer idAuction) throws NoWinnerException{
        return auctionsFacade.getWinnerBid(idAuction);
    }

    public List<Bid> getBidsByUserAndAuction(AuctionFacadeLocal auctionsFacade, Integer idUser, Integer idAuction) throws NoBidsException{
        return auctionsFacade.getBidsByUserAndAuction(idAuction, idUser);
    }

    public ResourceBundle setLocale(HttpServletRequest request){
        
        if (request.getParameter("locale") != null ){
            locale = new Locale(request.getParameter("locale"));
        }else if( request.getSession().getAttribute("locale") != null){
            locale = new Locale((String)request.getSession().getAttribute("locale"));
        }else{
            locale = request.getLocale();
        }
        this.messages = ResourceBundle.getBundle("com.reverseFlat.server.i18n.CommonMessages", locale);
        System.out.println("Language: "+locale.getLanguage());
        request.getSession().setAttribute("locale", locale.getLanguage());
        request.getSession().setAttribute("messages", messages);
        return messages;
    }

    public List<Auctioncategory> getCategories(AuctionFacadeLocal auctionsFacade){
        return auctionsFacade.getCategories();
    }

    public List<Auction> getAuctionsByCategory(AuctionFacadeLocal auctionsFacade, Integer idCategory){
        return auctionsFacade.findAuctionsByCategory(idCategory);
    }

    public Chipsexpenditure getLastChipsExpenditureByUser(AuctionFacadeLocal auctionsFacade, Integer idAuction, Integer idUser) throws NoBidsException{
        return auctionsFacade.getLastChipsExpenditureByUser(idAuction, idUser);
    }

    public int getBidCost(AuctionFacadeLocal auctionsFacade, Integer idAuction, Integer totalNumbers){
        return auctionsFacade.getBidCost(idAuction, totalNumbers);
    }
}
