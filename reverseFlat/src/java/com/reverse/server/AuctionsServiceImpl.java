/*
 * AuctionsServiceImpl.java
 *
 * Created on January 12, 2009, 8:10 PM
 *
 */

package com.reverse.server;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/*
import com.snoofing.ejb.session.AuctionFacadeLocal;
import com.snoofing.ejb.session.UserFacadeLocal;
import com.snoofing.exception.NoBidsException;
import com.snoofing.exception.NoChipsException;
import com.snoofing.exception.NoUniqueBidsException;
import com.snoofing.exception.NoWinnerException;
import com.snoofing.exception.RangeException;
import com.snoofing.gwt.client.common.exceptions.BidException;
import com.snoofing.gwt.client.common.valueobjects.AuctionVO;
import com.snoofing.gwt.client.common.valueobjects.BidVO;
import com.snoofing.gwt.client.common.valueobjects.ControlPanelVO;
import com.snoofing.gwt.client.common.valueobjects.UserVO;
import com.snoofing.gwt.client.services.AuctionsService;
import com.snoofing.gwt.server.commons.Commons;
import com.snoofing.persistence.Auction;
import com.snoofing.persistence.Bid;
import com.snoofing.persistence.User;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

*/

/**
 * Remote Service en GWT encargado de hacer pujas y refrescar el panel de control.
 * @author lfgiraldo
 */
public class AuctionsServiceImpl { //extends RemoteServiceServlet implements
    /*
    AuctionsService {

    @EJB
    private AuctionFacadeLocal auctionsFacade;
    @EJB
    private UserFacadeLocal userFacade;

    private Locale locale;
    private ResourceBundle  messages;

    public AuctionVO[] getActiveAuctions() {

        setLocale();

        List auctionsList = auctionsFacade.findActiveAuctions();
        if ( auctionsList.size() == 0  ){
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, messages.getString("NoOpenAuctions"));
            return null;
        }
        AuctionVO[] activeAuctions = new AuctionVO[auctionsList.size()];
        Iterator iterator = auctionsList.iterator();
        for(int i=0; iterator.hasNext(); i++){
            Auction auction = (Auction) iterator.next();
            activeAuctions[i] = Commons.convertAuctionToAuctionVO( auction, locale.getCountry() );
            String fullPath = getServletContext().getRealPath("/") + "uploadedItems/"+activeAuctions[i].getFileName();
            try {
                //Create the file in hard disk.
                String imagePath = getServletContext().getRealPath("/") + "img/productCircle.png";
                byte[] mixedImage = Commons.mergeImages(Commons.getBytesFromFile(imagePath), auction.getPicture(), 75);
                Commons.convertBytesToFile(mixedImage, fullPath);
                String relativePath =  getThreadLocalRequest().getContextPath() + "/uploadedItems/" + activeAuctions[i].getFileName();
                activeAuctions[i].setCircledPicture(relativePath);

                fullPath = getServletContext().getRealPath("/") + "uploadedItems/big"+auction.getPictureFileName();
                auction.setPicture(Commons.resizeImageAsJPG(auction.getPicture(), 150));
                Commons.convertBytesToFile(auction.getPicture(), fullPath);

                relativePath =  getThreadLocalRequest().getContextPath() + "/uploadedItems/big" + auction.getPictureFileName();
                activeAuctions[i].setGamePicture(relativePath);

            } catch (IOException ex) {
                Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            
        }
        return activeAuctions;
    }

    public AuctionVO[] getClosedAuctions() {
        setLocale();

        List auctionsList = auctionsFacade.findClosedAuctions();
        if ( auctionsList.size() == 0  ){
            System.out.println(messages.getString("NoClosedAuctions"));
            return null;
        }
        AuctionVO[] closedAuctions = new AuctionVO[auctionsList.size()];
        Iterator iterator = auctionsList.iterator();
        for(int i=0; iterator.hasNext(); i++){
            Auction auction = (Auction) iterator.next();
            closedAuctions[i] = Commons.convertAuctionToAuctionVO( auction, locale.getCountry() );
            String fullPath = getServletContext().getRealPath("/") + "uploadedItems/"+closedAuctions[i].getFileName();
            try {
                //Create the file in hard disk.
                String imagePath = getServletContext().getRealPath("/") + "img/productCircle.png";
                byte[] mixedImage = Commons.mergeImages(Commons.getBytesFromFile(imagePath), auction.getPicture(), 75);
                Commons.convertBytesToFile(mixedImage, fullPath);
            } catch (IOException ex) {
                Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }


            String relativePath =  getThreadLocalRequest().getContextPath() + "/uploadedItems/" + closedAuctions[i].getFileName();
            closedAuctions[i].setCircledPicture(relativePath);
        }
        return closedAuctions;
    }
    

    public ControlPanelVO doSingleBid(BidVO bid) throws BidException {
        setLocale();

        ControlPanelVO controlPanel =  new ControlPanelVO();
        try {
            bid.setUseridUser((UserVO) getThreadLocalRequest().getSession().getAttribute("user"));
            Bid singleBid = new Bid();
            singleBid = Commons.convertBidVOToBid(bid);
            auctionsFacade.singleBid(singleBid);

            controlPanel = getControlPanel(bid.getAuctionidAuction());

        } catch (NoChipsException ex) {
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
            throw new BidException(ex.getMessage());
        }
        return controlPanel;
    }


    public ControlPanelVO doMultipleBid(BidVO start, BidVO finish) throws BidException {
        setLocale();

        ControlPanelVO controlPanel =  new ControlPanelVO();
        try {
            start.setUseridUser((UserVO) getThreadLocalRequest().getSession().getAttribute("user"));
            finish.setUseridUser((UserVO) getThreadLocalRequest().getSession().getAttribute("user"));
            Bid startingBid = new Bid();
            Bid finishingBid = new Bid();
            startingBid = Commons.convertBidVOToBid(start);
            finishingBid = Commons.convertBidVOToBid(finish);

            auctionsFacade.rangeBid(startingBid, finishingBid);

            controlPanel = getControlPanel(start.getAuctionidAuction());

        } catch (NoChipsException ex) {
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
            throw new BidException(messages.getString("NoChipsException"));
        } catch (RangeException ex) {
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
            ex.printStackTrace();
            throw new BidException(messages.getString("RangeException"));
        }
        return controlPanel;
    }

    public ControlPanelVO getControlPanel (Integer idAuction){

        setLocale();
        
        UserVO currentUser = (UserVO) getThreadLocalRequest().getSession().getAttribute("user");
        ControlPanelVO controlPanel = new ControlPanelVO();
        Vector winnerBid;
        String idWinnerBid = "";
        String winnerBidText = "";
        String uniqueBids = "";
        User winnerUser;
        Vector lastBidVector = null;
        Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "ID Auction: "+idAuction);
        try{
            winnerBid = auctionsFacade.getWinnerBid(idAuction);
            idWinnerBid = winnerBid.get(3).toString();
            winnerBidText = winnerBid.get(2).toString();
            winnerUser = userFacade.find(new Integer(idWinnerBid));
        }catch (NoWinnerException ex){
            winnerUser = new User();
            winnerUser.setNickname(messages.getString("NoWinnerException"));
            winnerBidText = "0.0";
        }
        
        List<Bid> bidsCollection;
        String userBids = "";
        try{
            bidsCollection = auctionsFacade.getBidsByUserAndAuction(idAuction, currentUser.getIdUser());
            for (Bid bid : bidsCollection){
                userBids += bid.getBidAmount() + ", ";
            }
        }catch (NoBidsException ex){
            userBids = messages.getString("No_Bids");
        }catch (NullPointerException ex){
            //@TODO ocurre cuando es un usuario no registrado
        }
        controlPanel.setUserBids(userBids);

        float[] actualBidCost;
        try{
            actualBidCost = auctionsFacade.getActualBidCost(idAuction);
        }catch (NullPointerException ex){
            //@TODO ocurre cuando es un usuario no registrado
            actualBidCost = new float[7];
        }


        User userBalance;
        try{
            userBalance = userFacade.getUserBalanceDetailed(Commons.UserVOtoUser( currentUser ));
        }catch (NullPointerException ex){
            //@TODO ocurre cuando es un usuario no registrado
            userBalance = new User();
        }

        try{
            uniqueBids = auctionsFacade.getUniqueBidsByUser(idAuction, currentUser.getIdUser());
        }catch(NoUniqueBidsException ex){
            uniqueBids = messages.getString("NoUniqueBidsException");
        }catch (NullPointerException ex){
            //@TODO ocurre cuando es un usuario no registrado
        }

        try{
            lastBidVector = auctionsFacade.getLastBidByUser(idAuction, currentUser.getIdUser());
        }catch(NoBidsException ex){
            lastBidVector = null;
        }catch (NullPointerException ex){
            //@TODO ocurre cuando es un usuario no registrado
        }
        
        Auction endDate = auctionsFacade.find(new Integer(idAuction));

        BidVO lastBid = new BidVO();
        if (lastBidVector != null) {
            lastBid.setAuctionidAuction(idAuction);
            lastBid.setBidAmount(Float.parseFloat(lastBidVector.get(2).toString()));
            controlPanel.setLastBidValue(lastBidVector.get(2).toString());
            SimpleDateFormat formatter;
            if (locale != null){
                formatter = new SimpleDateFormat("yyyy-M-d h:m:s", locale);
            }else{
                formatter = new SimpleDateFormat("yyyy-M-d h:m:s");
            }
             
            
            try {
                Date bidDate = new Date();
                bidDate = formatter.parse(lastBidVector.get(1).toString());
                lastBid.setBidDate(bidDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        float winnerBidValue = Float.parseFloat(winnerBidText);
        if ( lastBid.getBidAmount() < winnerBidValue ){
            controlPanel.setHint(messages.getString("Your_last_bid_is_below_the_winner_bid"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("Your_last_bid_is_below_the_winner_bid"));
        }else if ( lastBid.getBidAmount() > winnerBidValue ){
            controlPanel.setHint(messages.getString("Your_last_bid_is_above_the_winner_bid"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("Your_last_bid_is_above_the_winner_bid"));
        }else if ( lastBid.getBidAmount() == winnerBidValue && winnerBidValue != 0.0 ){
            controlPanel.setHint(messages.getString("ActualWinner"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("ActualWinner"));
        }else{
            controlPanel.setHint(messages.getString("NoWinner"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("NoWinner"));
        }

        controlPanel.setActualBidCost(new Float(actualBidCost[0]).intValue());
        controlPanel.setActualWinner(winnerUser.getNickname());
        controlPanel.setChipsBalance(userBalance.getChipsBalance());
        controlPanel.setUniqueBids(uniqueBids);
        controlPanel.setLastBid(lastBid);

        controlPanel.setFirstQ(actualBidCost[1]);
        controlPanel.setSecondQ(actualBidCost[2]);
        controlPanel.setThirdQ(actualBidCost[3]);
        controlPanel.setFirstQCost(""+new Float(actualBidCost[4]).intValue());
        controlPanel.setSecondQCost(""+new Float(actualBidCost[5]).intValue());
        controlPanel.setThirdQCost(""+new Float (actualBidCost[6]).intValue());
        controlPanel.setEndDate(endDate.getEndDate());
        return controlPanel;

    }

    private void setLocale(){
        String userLocale = (String) getThreadLocalRequest().getSession().getAttribute("locale");
        Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Locale: "+userLocale);
        if (userLocale != null){
            String country = "";
            if ("es".equals(userLocale)){
                country = "ES";
            }else if ("en".equals(userLocale)){
                country = "US";
            }
            locale = new Locale(userLocale, country);
            this.messages = ResourceBundle.getBundle("com.snoofing.gwt.server.i18n.AuctionsServiceImplMessages", locale);
        }else{
            this.messages = ResourceBundle.getBundle("com.snoofing.gwt.server.i18n.AuctionsServiceImplMessages");
        }
    }

    /**
     *
     * @return Array of int with 3 positions.
     * Position 0 points to user chips balance.
     * Position 1 points to user free chips balance.
     * Position 2 points to user auctions enrolment.
     
    public int[] getChipsBalance(){
        UserVO currentUser = (UserVO) getThreadLocalRequest().getSession().getAttribute("user");
        User user;
        int[] userBalance = new int[3];
        try{
            user = userFacade.getUserBalanceDetailed(Commons.UserVOtoUser( currentUser ));
            userBalance[0] = user.getChipsBalance();
            userBalance[1] = user.getFreeChipsBalance();
        }catch (NullPointerException ex){
            //@TODO ocurre cuando es un usuario no registrado
            userBalance = new int[2];
            userBalance[0] = 0;
            userBalance[0] = 1;
        }

        List auctionsList = auctionsFacade.findActiveAuctionsByUser(currentUser.getIdUser());

        userBalance[2] = auctionsList.size();

        return userBalance;
    }

    public ControlPanelVO getLastAuctionByUser(){

        ControlPanelVO controlPanel =  new ControlPanelVO();
        AuctionVO auctionVO = new AuctionVO();
        UserVO currentUser = (UserVO) getThreadLocalRequest().getSession().getAttribute("user");
        Auction lastAuction = auctionsFacade.getLastAuctionByUser(currentUser.getIdUser());
        auctionVO = Commons.convertAuctionToAuctionVO(lastAuction, locale.getCountry());

        String fullPath = getServletContext().getRealPath("/") + "uploadedItems/big"+lastAuction.getPictureFileName();
        try {
            lastAuction.setPicture(Commons.resizeImageAsJPG(lastAuction.getPicture(), 150));
        } catch (IOException ex) {
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Commons.convertBytesToFile(lastAuction.getPicture(), fullPath);

        String relativePath =  getThreadLocalRequest().getContextPath() + "/uploadedItems/big" + lastAuction.getPictureFileName();
        auctionVO.setGamePicture(relativePath);

        controlPanel = getControlPanel(lastAuction.getIdAuction());
        controlPanel.setCurrentAuction(auctionVO);
        return controlPanel;

    }

    public ControlPanelVO findControlPanelByIdAuction(Integer idAuction){

        Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "idAuction: "+idAuction );
        ControlPanelVO controlPanel =  new ControlPanelVO();
        AuctionVO auctionVO = new AuctionVO();

        Auction currentAuction = auctionsFacade.find(new Integer(idAuction));
        auctionVO = Commons.convertAuctionToAuctionVO(currentAuction, locale.getCountry());

        String fullPath = getServletContext().getRealPath("/") + "uploadedItems/big"+currentAuction.getPictureFileName();
        try {
            currentAuction.setPicture(Commons.resizeImageAsJPG(currentAuction.getPicture(), 150));
        } catch (IOException ex) {
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Commons.convertBytesToFile(currentAuction.getPicture(), fullPath);

        String relativePath =  getThreadLocalRequest().getContextPath() + "/uploadedItems/big" + currentAuction.getPictureFileName();
        auctionVO.setGamePicture(relativePath);

        controlPanel = getControlPanel(currentAuction.getIdAuction());
        controlPanel.setCurrentAuction(auctionVO);
        return controlPanel;
    }

    public ControlPanelVO getPublicControlPanel (Integer idAuction){

        setLocale();

        ControlPanelVO controlPanel = new ControlPanelVO();
        Vector winnerBid;
        String idWinnerBid = "";
        String winnerBidText = "";
        User winnerUser;
        Vector lastBidVector = null;
        Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "ID Auction: "+idAuction);
        try{
            winnerBid = auctionsFacade.getWinnerBid(idAuction);
            idWinnerBid = winnerBid.get(3).toString();
            winnerBidText = winnerBid.get(2).toString();
            winnerUser = userFacade.find(new Integer(idWinnerBid));
        }catch (NoWinnerException ex){
            winnerUser = new User();
            winnerUser.setNickname(messages.getString("NoWinnerException"));
            winnerBidText = "0.0";
        }

        Auction endDate = auctionsFacade.find(new Integer(idAuction));

        BidVO lastBid = new BidVO();
        if (lastBidVector != null) {
            lastBid.setAuctionidAuction(idAuction);
            lastBid.setBidAmount(Float.parseFloat(lastBidVector.get(2).toString()));
            controlPanel.setLastBidValue(lastBidVector.get(2).toString());
            SimpleDateFormat formatter;
            if (locale != null){
                formatter = new SimpleDateFormat("yyyy-M-d h:m:s", locale);
            }else{
                formatter = new SimpleDateFormat("yyyy-M-d h:m:s");
            }


            try {
                Date bidDate = new Date();
                bidDate = formatter.parse(lastBidVector.get(1).toString());
                lastBid.setBidDate(bidDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        float winnerBidValue = Float.parseFloat(winnerBidText);
        if ( lastBid.getBidAmount() < winnerBidValue ){
            controlPanel.setHint(messages.getString("Your_last_bid_is_below_the_winner_bid"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("Your_last_bid_is_below_the_winner_bid"));
        }else if ( lastBid.getBidAmount() > winnerBidValue ){
            controlPanel.setHint(messages.getString("Your_last_bid_is_above_the_winner_bid"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("Your_last_bid_is_above_the_winner_bid"));
        }else if ( lastBid.getBidAmount() == winnerBidValue && winnerBidValue != 0.0 ){
            controlPanel.setHint(messages.getString("ActualWinner"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("ActualWinner"));
        }else{
            controlPanel.setHint(messages.getString("NoWinner"));
            Logger.getLogger(AuctionsServiceImpl.class.getName()).log(Level.INFO, "Hint: "+messages.getString("NoWinner"));
        }

        controlPanel.setActualWinner(winnerUser.getNickname());
        controlPanel.setEndDate(endDate.getEndDate());
        return controlPanel;

    }
     */
}
