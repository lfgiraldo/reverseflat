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
import com.reverse.ejb.persistence.Auctiontexttranslation;
import com.reverse.ejb.persistence.Bid;
import com.reverse.ejb.persistence.Chipsexpenditure;
import com.reverse.ejb.persistence.Pricebyauction;
import com.reverse.ejb.persistence.PricebyauctionPK;
import com.reverse.ejb.persistence.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pipe
 */
@Stateless (mappedName="AuctionFacade")
public class AuctionFacade implements AuctionFacadeLocal, AuctionFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Auction auction) {
        Query q = em.createNativeQuery("SELECT MAX(idAuction) FROM auction").setHint("toplink.refresh", "true");
        Vector idUser = (Vector) q.getSingleResult();
        Long id = (Long) idUser.get(0);
        id++;
        auction.setIdAuction(id.intValue());
        List<Auctiontexttranslation> languagesList = auction.getAuctiontexttranslationCollection();
        List<Auctiontexttranslation> newList = new ArrayList();
        for(Auctiontexttranslation language : languagesList ){
            language.setAuctionidAuction(auction);
            newList.add(language);
        }
        auction.setAuctiontexttranslationCollection(newList);
        em.persist(auction);
        
        

    }

    public void edit(Auction auction) {
        em.merge(auction);
    }

    public void remove(Auction auction) {
        em.remove(em.merge(auction));
    }

    public Auction find(Object id) {
        //Cierra todas las subastas que hayan finalizado.
        Query q = em.createNativeQuery("UPDATE auction SET active = 0 WHERE endDate IS NOT NULL AND endDate < NOW()");
        q.executeUpdate();
        return em.find(com.reverse.ejb.persistence.Auction.class, id);
    }
    
    public List<Auction> findActiveAuctions() {
        //Cierra todas las subastas que hayan finalizado.
        Query q = em.createNativeQuery("UPDATE auction SET active = 0 WHERE endDate IS NOT NULL AND endDate < NOW()");
        q.executeUpdate();
        return em.createQuery("select object(o) from Auction as o Where o.active = 1").setMaxResults(10).setHint("toplink.refresh", "true").getResultList();
    }

    public List<Auction> findAll() {
        return em.createQuery("select object(o) from Auction as o").setHint("toplink.refresh", "true").getResultList();
    }

    /*TODO Hacer la consulta de TOP Auctions */
    public List<Auction> findTopAuctions() {
        return null;
    }

    public List<Auction> findClosedAuctions() {
        return em.createQuery("select object(o) from Auction as o WHERE o.active = 0  AND o.endDate < CURRENT_TIMESTAMP").getResultList();
    }

    public List<Chipsexpenditure> findActiveAuctionsByUser(Integer idUser) {
        Query q = em.createQuery("SELECT DISTINCT auc FROM Auction as auc JOIN auc.chipsexpenditureCollection  as user where user.useridUser.idUser = :idUser AND auc.active = 1").setMaxResults(10).setHint("toplink.refresh", "true");
        q.setParameter("idUser", idUser);
        return q.getResultList();
    }

    public List<Auction> findClosedAuctionsByUser(Integer idUser) {
        return null;
    }

    /**
     *
     * @param idUser
     * @param type normal, free, club
     * @return
     */
    public List<Auction> findAuctionsByUser(Integer idUser, String type) {
        Query q = em.createQuery("SELECT DISTINCT auct FROM Auction as auct JOIN auct.bidCollection as bids WHERE auct.active = 1 and bids.useridUser.idUser = :idUser and auct.type = :type").setHint("toplink.refresh", "true");
        q.setParameter("idUser", idUser);
        q.setParameter("type", type);
        return q.getResultList();
    }

    public void activateAuction(Integer idAuction) {
        Query q = em.createQuery("update Auction set active = 1 WHERE idAuction = :idAuction");
        q.setParameter("idAuction", idAuction);
        q.executeUpdate();
    }

    public void singleBid(Bid bid) throws NoChipsException, ClosedAuctionException {
        //Cierro las subastas en las cuales se acabó el tiempo
        Query q = em.createNativeQuery("UPDATE auction SET active = 0 WHERE endDate IS NOT NULL AND endDate < CURDATE()");
        q.executeUpdate();

        //Consulto el balance del usuario.
        Query foundsQuery = em.createNamedQuery("User.findByIdUser");
        foundsQuery.setParameter("idUser", bid.getUseridUser().getIdUser());
        User user = (User) foundsQuery.getSingleResult();

        //Creo un gasto
        Chipsexpenditure chipExpenditure = new Chipsexpenditure();
        chipExpenditure.setAuctionidAuction(bid.getAuctionidAuction());
        chipExpenditure.setUseridUser(bid.getUseridUser());

        //Consulto la información de la subasta, para saber los valores de los cuartos.
        q = em.createNamedQuery("Auction.findByIdAuction");
        q.setParameter("idAuction", bid.getAuctionidAuction().getIdAuction());
        Auction auction = (Auction) q.getSingleResult();

        if (!auction.getActive()){
            throw new ClosedAuctionException("This auction has been closed.");
        }

        int currentBidCost = getBidCost(bid.getAuctionidAuction().getIdAuction(), 1);
        int chipsBalance = 0;
        if ("free".equalsIgnoreCase(auction.getType())){
            chipsBalance = user.getFreeChipsBalance();
        }else{
            chipsBalance = user.getChipsBalance();
        }

        //Si tiene fichas suficientes para cubrir el valor de la puja, le permito pujar.
        if (chipsBalance >= currentBidCost){
            //Almaceno los datos de la puja.
            //Determino cuántas fichas le voy a quitar al usuario
            chipExpenditure.setChipsAmount(currentBidCost);
            bid.setChipsAmount(currentBidCost);
            chipExpenditure.setBidFrom(bid.getBidAmount());
            chipExpenditure.setBidTo(bid.getBidAmount());
            //Si es una subasta gratuita creo el gasto como FREE
            if ("free".equalsIgnoreCase(auction.getType())){
                chipExpenditure.setType("free");
                q = em.createQuery("update User set freeChipsBalance = (freeChipsBalance - :bidCost) WHERE idUser = :idUser");
            }else{
                chipExpenditure.setType("normal");
                q = em.createQuery("update User set chipsBalance = (chipsBalance - :bidCost) WHERE idUser = :idUser");
            }
            
            q.setParameter("idUser", bid.getUseridUser().getIdUser());
            q.setParameter("bidCost", chipExpenditure.getChipsAmount());
            try{
                q.executeUpdate();
            }catch (Exception ex){
                throw new NoChipsException("You Don't have enough chips");
            }
            
            em.persist(chipExpenditure);
          
            //Mínimo de fichas en esta subasta
            long minChips = auction.getMinBids();
            //Cantidad de pujas por cuarto
            int bidsPerQ = Math.round(minChips/6);
            System.out.println("Bids per Q: "+bidsPerQ);

            //Consultar cuántas PUJAS tiene la subasta.
            q = em.createNativeQuery("select count(b.idBid) from bid b where b.auction_idAuction = ?").setHint("toplink.refresh", "true");
            q.setParameter(1, bid.getAuctionidAuction().getIdAuction());
            Vector totalBidsVector = (Vector) q.getSingleResult();

            Long totalBids;
            if ( totalBidsVector.get(0) == null ){
                totalBids = new Long(0);
            }else{
                totalBids = (Long)totalBidsVector.get(0);
            }
            System.out.println("Total Bids: "+totalBids);
            //SI ES EL GANADOR DEL PRIMER CUARTO, SE LE ENTREGA EL PREMIO 1
            if (totalBids == bidsPerQ){
                List<Pricebyauction> prices = auction.getPricebyauctionCollection();
                PricebyauctionPK pricePK = new PricebyauctionPK(bid.getAuctionidAuction().getIdAuction(), new Short("1") );
                Pricebyauction price = new Pricebyauction();
                Bid winnerBid = new Bid();
                for(Pricebyauction p: prices){
                    if ( Short.parseShort("1") ==  p.getPricebyauctionPK().getQuarter() ){
                        pricePK = p.getPricebyauctionPK();
                        price.setPriceidPrice(p.getPriceidPrice());
                        try{
                            winnerBid = getWinnerBid(bid.getAuctionidAuction().getIdAuction());
                        }catch(NoWinnerException ex){
                            Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                        }
                    }
                }
                price.setPricebyauctionPK(pricePK);
                price.setUseridUser(winnerBid.getUseridUser());
                em.merge(price);
            }else
                //SI ES EL GANADOR DEL SEGUNDO CUARTO, SE LE ENTREGA EL PREMIO 2
                if (totalBids == (bidsPerQ*2)){
                    List<Pricebyauction> prices = auction.getPricebyauctionCollection();
                    PricebyauctionPK pricePK = new PricebyauctionPK(bid.getAuctionidAuction().getIdAuction(), new Short("2") );
                    Pricebyauction price = new Pricebyauction(pricePK);
                    Bid winnerBid = new Bid();
                    for(Pricebyauction p: prices){
                        if ( Short.parseShort("2") ==  p.getPricebyauctionPK().getQuarter() ){
                            pricePK = p.getPricebyauctionPK();
                            price.setPriceidPrice(p.getPriceidPrice());
                            try{
                                winnerBid = getWinnerBid(bid.getAuctionidAuction().getIdAuction());
                            }catch(NoWinnerException ex){
                                Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                            }

                        }
                    }
                    price.setPricebyauctionPK(pricePK);
                    price.setUseridUser(winnerBid.getUseridUser());
                    em.merge(price);
                }else {
                    //SI ES EL GANADOR DEL TERCER CUARTO, SE LE ENTREGA EL PREMIO 3
                    if (totalBids == (bidsPerQ*3)){
                        List<Pricebyauction> prices = auction.getPricebyauctionCollection();
                        PricebyauctionPK pricePK = new PricebyauctionPK(bid.getAuctionidAuction().getIdAuction(), new Short("3") );
                        Pricebyauction price = new Pricebyauction(pricePK);
                        Bid winnerBid = new Bid();
                        for(Pricebyauction p: prices){
                            if ( Short.parseShort("3") ==  p.getPricebyauctionPK().getQuarter() ){
                                pricePK = p.getPricebyauctionPK();
                                price.setPriceidPrice(p.getPriceidPrice());
                                try{
                                    winnerBid = getWinnerBid(bid.getAuctionidAuction().getIdAuction());
                                }catch(NoWinnerException ex){
                                    Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                                }
                            }
                        }
                        price.setPricebyauctionPK(pricePK);
                        price.setUseridUser(winnerBid.getUseridUser());
                        em.merge(price);

                        //Como la barra se terminó, se debe activar la cuenta atrás. Una vez termine, se debe cerrar la subasta.
                        if ( auction.getEndDate() == null ){
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.MINUTE, auction.getMinutesAfterClose());
                            auction.setEndDate(cal.getTime());
                            em.persist(auction);
                        }
                    }
                }

                Date currentDate = new Date();
                if ( auction.getEndDate() != null && auction.getEndDate().before(currentDate) && auction.getActive() == true){
                    auction.setActive(false);
                    em.persist(auction);
                }
                em.persist(bid);

        }else{
            throw new NoChipsException("You Don't have enough chips");
        }
    }


    /**
     * Make bids between starting and ending number, both of them inclusive.
     * @param startingBid Starting number for a range bid
     * @param finishingBid Finishing number for a range bid
     * @throws com.snoofing.exception.NoChipsException  If user has not enough chips
     * @throws com.snoofing.exception.RangeException    If starting bid is bigger than finishing bid
     */
    public void rangeBid(Bid startingBid, Bid finishingBid) throws NoChipsException, RangeException, ClosedAuctionException {
        if (startingBid.getBidAmount() >= finishingBid.getBidAmount()){
            throw new RangeException("Finishing bid must be greater than starting bid");
        }

        //Cierro las subastas en las cuales se acabó el tiempo
        Query q = em.createNativeQuery("UPDATE auction SET active = 0 WHERE endDate IS NOT NULL AND endDate < CURDATE()");
        q.executeUpdate();


        int range = (int) (finishingBid.getBidAmount() * 10 - startingBid.getBidAmount() * 10);
        float newValue = startingBid.getBidAmount();

        //Creo un gasto
        Chipsexpenditure chipExpenditure = new Chipsexpenditure();
        chipExpenditure.setAuctionidAuction(startingBid.getAuctionidAuction());
        chipExpenditure.setUseridUser(startingBid.getUseridUser());
        chipExpenditure.setBidFrom(startingBid.getBidAmount());
        chipExpenditure.setBidTo(finishingBid.getBidAmount());
        int totalNumbers = (int) ((finishingBid.getBidAmount() - startingBid.getBidAmount()) * 10 + 1);
        int totalBidCost = getBidCost(startingBid.getAuctionidAuction().getIdAuction(), totalNumbers);


        //Consulto el balance del usuario.
        Query foundsQuery = em.createNamedQuery("User.findByIdUser").setHint("toplink.refresh", "true");
        foundsQuery.setParameter("idUser", startingBid.getUseridUser().getIdUser());
        User user = (User) foundsQuery.getSingleResult();


        //Consulto la información de la subasta, para saber los valores de los cuartos.
        q = em.createNamedQuery("Auction.findByIdAuction");
        q.setParameter("idAuction", startingBid.getAuctionidAuction().getIdAuction());
        Auction auction = (Auction) q.getSingleResult();

        if (!auction.getActive()){
            throw new ClosedAuctionException("This auction has been closed.");
        }

        int chipsBalance = 0;
        if ("free".equalsIgnoreCase(auction.getType())){
            chipsBalance = user.getFreeChipsBalance();
        }else{
            chipsBalance = user.getChipsBalance();
        }
        //Si tiene fichas suficientes para cubrir el valor de la puja, le permito pujar.
        if (chipsBalance >= totalBidCost){
            //Almaceno los datos de la puja.
            //Determino cuántas fichas le voy a quitar al usuario
            chipExpenditure.setChipsAmount(totalBidCost);
            if ("free".equalsIgnoreCase(auction.getType())){
                chipExpenditure.setType("free");
                q = em.createQuery("update User set freeChipsBalance = (freeChipsBalance - :bidCost) WHERE idUser = :idUser");
            }else{
                chipExpenditure.setType("normal");
                q = em.createQuery("update User set chipsBalance = (chipsBalance - :bidCost) WHERE idUser = :idUser");
            }
            q.setParameter("idUser", startingBid.getUseridUser().getIdUser());
            q.setParameter("bidCost", chipExpenditure.getChipsAmount());
            em.persist(chipExpenditure);
            try{
                q.executeUpdate();
            }catch (Exception ex){
                Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                throw new NoChipsException("You Don't have enough chips");
            }
        }else{
            Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, "You Don't have enough chips");
            throw new NoChipsException("You Don't have enough chips");
        }

        
        //Mínimo de fichas en esta subasta
        long minChips = auction.getMinBids();
        //Cantidad de pujas por cuarto
        int bidsPerQ = Math.round(minChips/6);
        System.out.println("Bids per Q: "+bidsPerQ);
        //Consultar cuántas FICHAS tiene la subasta.
        q = em.createNativeQuery("select count(b.idBid) from bid b where b.auction_idAuction = ?").setHint("toplink.refresh", "true");
        q.setParameter(1, startingBid.getAuctionidAuction().getIdAuction());
        Vector totalBidsVector = (Vector) q.getSingleResult();

        Long totalBids;
        if ( totalBidsVector.get(0) == null ){
            totalBids = new Long(0);
        }else{
            totalBids = (Long)totalBidsVector.get(0);
        }
        System.out.print("Total: ");
        for (int i = 0; i <= range; i++){
            System.out.print(totalBids+" - ");
            Bid newBid = new Bid();
            newBid.setAuctionidAuction(startingBid.getAuctionidAuction());
            newBid.setBidAmount(new Float(newValue));
            newBid.setUseridUser(user);

            if (totalBids <= bidsPerQ){
                newBid.setChipsAmount(1);

                //SI ES EL GANADOR DEL PRIMER CUARTO, SE LE ENTREGA EL PREMIO 1
                if (totalBids == bidsPerQ){
                    List<Pricebyauction> prices = auction.getPricebyauctionCollection();
                    PricebyauctionPK pricePK = new PricebyauctionPK(startingBid.getAuctionidAuction().getIdAuction(), new Short("1") );
                    Pricebyauction price = new Pricebyauction();
                    Bid winnerBid = new Bid();
                    for(Pricebyauction p: prices){
                        if ( Short.parseShort("1") ==  p.getPricebyauctionPK().getQuarter() ){
                            pricePK = p.getPricebyauctionPK();
                            price.setPriceidPrice(p.getPriceidPrice());
                            try{
                                winnerBid = getWinnerBid(startingBid.getAuctionidAuction().getIdAuction());
                            }catch(NoWinnerException ex){
                                Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                            }
                        }
                    }
                    price.setPricebyauctionPK(pricePK);
                    price.setUseridUser(winnerBid.getUseridUser());
                    em.merge(price);
                }
            }else if (totalBids > bidsPerQ && totalBids <= bidsPerQ*2){
                newBid.setChipsAmount(2);
                //SI ES EL GANADOR DEL SEGUNDO CUARTO, SE LE ENTREGA EL PREMIO 2
                if (totalBids == (bidsPerQ*2)){
                    List<Pricebyauction> prices = auction.getPricebyauctionCollection();
                    PricebyauctionPK pricePK = new PricebyauctionPK(startingBid.getAuctionidAuction().getIdAuction(), new Short("2") );
                    Pricebyauction price = new Pricebyauction(pricePK);
                    Bid winnerBid = new Bid();
                    for(Pricebyauction p: prices){
                        if ( Short.parseShort("2") ==  p.getPricebyauctionPK().getQuarter() ){
                            pricePK = p.getPricebyauctionPK();
                            price.setPriceidPrice(p.getPriceidPrice());
                            try{
                                winnerBid = getWinnerBid(startingBid.getAuctionidAuction().getIdAuction());    
                            }catch(NoWinnerException ex){
                                Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                            }

                        }
                    }
                    price.setPricebyauctionPK(pricePK);
                    price.setUseridUser(winnerBid.getUseridUser());
                    em.merge(price);
                }
            }else if (totalBids > bidsPerQ*2 && totalBids <= bidsPerQ*3){
                newBid.setChipsAmount(3);
                //SI ES EL GANADOR DEL TERCER CUARTO, SE LE ENTREGA EL PREMIO 3
                if (totalBids == (bidsPerQ*3)){
                    List<Pricebyauction> prices = auction.getPricebyauctionCollection();
                    PricebyauctionPK pricePK = new PricebyauctionPK(startingBid.getAuctionidAuction().getIdAuction(), new Short("3") );
                    Pricebyauction price = new Pricebyauction(pricePK);
                    Bid winnerBid = new Bid();
                    for(Pricebyauction p: prices){
                        if ( Short.parseShort("3") ==  p.getPricebyauctionPK().getQuarter() ){
                            pricePK = p.getPricebyauctionPK();
                            price.setPriceidPrice(p.getPriceidPrice());
                            try{
                                winnerBid = getWinnerBid(startingBid.getAuctionidAuction().getIdAuction());
                            }catch(NoWinnerException ex){
                                Logger.getLogger(AuctionFacade.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                            }
                        }
                    }
                    price.setPricebyauctionPK(pricePK);
                    price.setUseridUser(winnerBid.getUseridUser());
                    em.merge(price);

                    //Como la barra se terminó, se debe activar la cuenta atrás.
                    if ( auction.getEndDate() == null ){
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.MINUTE, auction.getMinutesAfterClose());
                        auction.setEndDate(cal.getTime());
                        em.persist(auction);
                    }
                }
            }else{
                newBid.setChipsAmount(4);
            }
            totalBids++;

            em.persist(newBid);
            newValue = newValue + 0.1f;

            //Si el tiempo se acabó se debe cerrar la subasta.
            Date currentDate = new Date();
            if ( auction.getEndDate() != null && auction.getEndDate().before(currentDate) && auction.getActive() == true){
                auction.setActive(false);
                em.persist(auction);
            }
        }
    }

    /**
     * Returns the winner bid in some auction
     * @param idAuction
     * @return idBid, bidDate, bidAmount, user_idUser, auction_idAuction, count(bidAmount) as n
     */
    public Bid getWinnerBid (Integer idAuction)throws NoWinnerException{
        //SELECT min(bid.bidAmount) FROM Bid as bid WHERE bid.bidAmount IN(SELECT b.bidAmount FROM Bid as b WHERE b.auctionidAuction.idAuction = 1 GROUP BY b.bidAmount HAVING (count(b.bidAmount) = 1 ))
        Query q = em.createNativeQuery("select * from (select idBid, bidDate, bidAmount, user_idUser, auction_idAuction, count(bidAmount) as n from bid where auction_idauction = ? group by bidAmount having n=1) as minimun having min(n)").setHint("toplink.refresh", "true");
        q.setParameter(1, idAuction);
        Vector winnerBid;
        try{
            winnerBid = (Vector) q.getSingleResult();
            q = em.createQuery("SELECT bid FROM Bid as bid WHERE bid.idBid = :idBid").setHint("toplink.refresh", "true");
            q.setParameter("idBid", winnerBid.get(0));
            return (Bid) q.getSingleResult();
        }catch (NoResultException ex){
            throw new NoWinnerException("There is no winner at the moment");
        }
        
        
    }

    public int getBidCost(Integer idAuction, Integer totalNumbers){
        //Consulto la información de la subasta, para saber los valores de los cuartos.
        Query q = em.createNamedQuery("Auction.findByIdAuction");
        q.setParameter("idAuction", idAuction);
        Auction auction = (Auction) q.getSingleResult();
        //Mínimo de fichas en esta subasta
        long minChips = auction.getMinBids();
        //Cantidad de pujas por cuarto
        int bidsPerQ = Math.round(minChips/6);

        //Consultar cuántas FICHAS tiene la subasta.
        q = em.createNativeQuery("select count(b.idBid) from bid b where b.auction_idAuction = ?").setHint("toplink.refresh", "true");
        q.setParameter(1, idAuction);
        Vector totalBidsVector = (Vector) q.getSingleResult();

        Long totalChips;
        if ( totalBidsVector.get(0) == null ){
            totalChips = new Long(0);
        }else{
            totalChips = (Long)totalBidsVector.get(0);
        }


        int totalCostInChips = 0;
        int necessaryBids = 0;

        necessaryBids = (bidsPerQ * 1) - Math.round(totalChips);
        if (necessaryBids > 0){
            if ( totalNumbers > necessaryBids ){
                totalCostInChips += necessaryBids * 1;
                totalNumbers -= necessaryBids;
                totalChips += totalCostInChips;
            }else{
                totalCostInChips += totalNumbers * 1;
                return totalCostInChips;
            }
        }
        

        necessaryBids = (bidsPerQ * 2) - Math.round(totalChips);
        if (necessaryBids > 0){
            if ( totalNumbers > necessaryBids ){
                totalCostInChips += necessaryBids * 2;
                totalNumbers -= necessaryBids;
                totalChips += totalCostInChips;
            }else{
                totalCostInChips += totalNumbers * 2;
                return totalCostInChips;
            }
        }

        necessaryBids = (bidsPerQ * 3) - Math.round(totalChips);
        if (necessaryBids > 0){
            if ( totalNumbers > necessaryBids ){
                totalCostInChips += necessaryBids * 3;
                totalNumbers -= necessaryBids;
                totalChips += totalCostInChips;
            }else{
                totalCostInChips += totalNumbers * 3;
                return totalCostInChips;
            }
        }

        if ( totalNumbers > 0 ){
            totalCostInChips += totalNumbers * 4;
        }

        return totalCostInChips;
    }

    /**
     * 
     * @param idAuction
     * @param idUser
     * @return array with the cost and the progress bar
     */
    public float[] getProgressBar(Integer idAuction){

        float[] progressBar = new float[7];
        //Consultar cuántas PUJAS tiene la subasta. Por eso se cuentan y no se suman
        Query q = em.createNativeQuery("select count(b.chipsAmount) from bid b where b.auction_idAuction = ?").setHint("toplink.refresh", "true");
        q.setParameter(1, idAuction);
        Vector totalBidsVector = (Vector) q.getSingleResult();

        Long totalChips;
        if ( totalBidsVector.get(0) == null ){
            totalChips = new Long(0);
        }else{
            totalChips = (Long)totalBidsVector.get(0);
        }

        //Consulto la información de la subasta, para saber los valores de los cuartos.
        q = em.createNamedQuery("Auction.findByIdAuction");
        q.setParameter("idAuction", idAuction);
        Auction auction = (Auction) q.getSingleResult();
        long quartersSize = auction.getMinBids() / 6; //Mínimo común múltiplo = 6

        if ( totalChips <= quartersSize ){
            progressBar[0] = auction.getChipsPerBid1stQ();
            progressBar[1] = totalChips.floatValue()/quartersSize;
            progressBar[2] = 0;
            progressBar[3] = 0;
        }else if ( totalChips > quartersSize && totalChips <= quartersSize * 2 ){
            progressBar[0] = auction.getChipsPerBid2ndQ();
            progressBar[1] = 1;
            progressBar[2] = totalChips.floatValue()/(quartersSize*2);
            progressBar[3] = 0;
        }else if ( totalChips > quartersSize * 2 && totalChips <= quartersSize * 3 ){
            progressBar[0] = auction.getChipsPerBid3rdQ();
            progressBar[1] = 1;
            progressBar[2] = 1;
            progressBar[3] = totalChips.floatValue()/(quartersSize*3);
        }else{
            if ( auction.getEndDate() == null ){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, auction.getMinutesAfterClose());
                auction.setEndDate(cal.getTime());
                em.persist(auction);
            }
            Date currentDate = new Date();
            if ( auction.getEndDate() != null && auction.getEndDate().after(currentDate) && auction.getActive() == true){
                auction.setActive(false);
                em.persist(auction);
            }
            progressBar[0] = auction.getChipsPerBid4thQ();
            progressBar[1] = 1;
            progressBar[2] = 1;
            progressBar[3] = 1; 
        }
        
        progressBar[4] = auction.getChipsPerBid1stQ();
        progressBar[5] = auction.getChipsPerBid2ndQ();
        progressBar[6] = auction.getChipsPerBid3rdQ();
        return progressBar;
    }
    
    public String getUniqueBidsByUser(Integer idAuction, Integer idUser) throws NoUniqueBidsException{
        
        String uniqueBids = "";
        
        //Consultar pujas únicas por usuario
        Query q = em.createNativeQuery("select * from (select idBid, bidDate, bidAmount, user_idUser, auction_idAuction, count(bidAmount) as n from bid where auction_idauction = ?  group by bidAmount having n=1 ) as minimun having user_idUser = ?").setHint("toplink.refresh", "true");
        q.setParameter(1, idAuction);
        q.setParameter(2, idUser);
        List totalBidsVector = null;

        try{
            totalBidsVector = (List) q.getResultList();
            Iterator i = totalBidsVector.iterator();
            while (i.hasNext()){
                Vector vector = (Vector) i.next();
                uniqueBids += vector.get(2);
                uniqueBids += " ";
            }
        }catch(NoResultException ex){
            throw new NoUniqueBidsException("You do not have unique bids");
        }
        
        return uniqueBids;
        
    }

    public Bid getLastBidByUser(Integer idAuction, Integer idUser) throws NoBidsException{
        
        List lastBidsList;
        Vector vector = null;
        //Consultar pujas únicas por usuario
        Query q = em.createNativeQuery("SELECT idBid, bidDate, bidAmount, user_idUser, auction_idAuction FROM bid b where user_iduser = ?  and auction_idauction = ? AND idBid IN (SELECT max(idBid) FROM bid b where user_iduser = ?  and auction_idauction = ?)").setHint("toplink.refresh", "true");
        q.setParameter(1, idUser);
        q.setParameter(2, idAuction);
        q.setParameter(3, idUser);
        q.setParameter(4, idAuction);
        
        try{
            lastBidsList = (List) q.getResultList();
            Iterator i = lastBidsList.iterator();
            if (lastBidsList.size() == 0){
                throw new NoBidsException("You do not have bids on this auction.");
            }
            if(i.hasNext()){
                vector = (Vector) i.next();
            }
            q = em.createQuery("SELECT bid FROM Bid as bid WHERE bid.idBid = :idBid");
            q.setParameter("idBid", vector.get(0));
            return (Bid) q.getSingleResult();
        }catch(NoResultException ex){
            throw new NoBidsException("You do not have bids.");
        }
    }

    public Chipsexpenditure getLastChipsExpenditureByUser(Integer idAuction, Integer idUser) throws NoBidsException{
        Query q = em.createQuery("SELECT MAX(ex.idExpenditure) FROM Chipsexpenditure ex WHERE ex.auctionidAuction.idAuction = :idAuction AND ex.useridUser.idUser = :idUser");
        q.setParameter("idAuction", idAuction);
        q.setParameter("idUser", idUser);
        Long idExpenditure;
        try{
            idExpenditure = (Long) q.getSingleResult();
        }catch(Exception ex){
            throw new NoBidsException("You do not have bids.");
        }
        q = em.createQuery("SELECT ex FROM Chipsexpenditure ex WHERE ex.idExpenditure = :idExpenditure");
        q.setParameter("idExpenditure", idExpenditure);
        return (Chipsexpenditure) q.getSingleResult();

    }
    
    public Auction getLastAuctionByUser(Integer idUser) {
        

        Vector lastAuctionByUser;

        //Consultar pujas únicas por usuario
        Query q = em.createNativeQuery("select idAuction from auction as o where o.active = 1 AND o.idAuction = (SELECT auction_idAuction FROM bid where user_idUser = ? and idBid = (select max(idBid) FROM bid where user_idUser = ? ) )").setHint("toplink.refresh", "true");
        q.setParameter(1, idUser);
        q.setParameter(2, idUser);
        try{
            lastAuctionByUser = (Vector) q.getSingleResult();
            q = em.createQuery("SELECT auc FROM Auction as auc WHERE auc.idAuction = :idAuction").setHint("toplink.refresh", "true");
            q.setParameter("idAuction", lastAuctionByUser.get(0));
            return (Auction) q.getSingleResult();
        }catch(NoResultException ex){
            q = em.createNativeQuery("select idAuction from auction as o where o.idAuction IN (SELECT max(idAuction) FROM auction where active = 1)").setHint("toplink.refresh", "true");
            lastAuctionByUser = (Vector) q.getSingleResult();
            q = em.createQuery("SELECT auc FROM Auction as auc WHERE auc.idAuction = :idAuction").setHint("toplink.refresh", "true");
            q.setParameter("idAuction", lastAuctionByUser.get(0));
            return (Auction) q.getSingleResult();
        }
        
    }

    public List<Auction> getEnrolledAuctionsByUser(Integer idUser) {
        Query q = em.createQuery("SELECT DISTINCT auc FROM Auction as auc JOIN auc.chipsexpenditureCollection as exp WHERE exp.useridUser.idUser = :idUser AND auc.active = 1").setHint("toplink.refresh", "true");
        q.setParameter("idUser", idUser);
        return q.getResultList();
    }

    public List<Bid> getBidsByUserAndAuction(Integer idAuction, Integer idUser) throws NoBidsException{

        List lastBidsList;
        //Consultar pujas únicas por usuario
        Query q = em.createQuery(
                "SELECT object(o) FROM Bid as o where o.useridUser = :idUser  " +
                "and o.auctionidAuction = :idAuction order by o.bidAmount").setHint("toplink.refresh", "true");
        q.setParameter("idUser", new User(idUser));
        q.setParameter("idAuction", new Auction(idAuction));

        try{
            lastBidsList = (List) q.getResultList();
        }catch(NoResultException ex){
            throw new NoBidsException("You do not have bids on this auction");
        }
        return lastBidsList;

    }
    
    public List<Auctioncategory> getCategories (){
        Query q = em.createQuery("select distinct cat from Auctioncategory as cat where cat.idCategory IN (select distinct auc.categoryidCategory.idCategory from Auction as auc)").setHint("toplink.refresh", "true");
        return q.getResultList();
    }

    public List<Auction> findAuctionsByLastActivity(){
        Query q = em.createQuery("SELECT DISTINCT auc FROM Auction as auc JOIN auc.chipsexpenditureCollection as exp WHERE auc.active=1 ORDER BY exp.idExpenditure DESC").setHint("toplink.refresh", "true");
        return q.getResultList();
    }

    public List<Auction> findAuctionsByCategory(Integer idCategory){
        Query q = em.createQuery("select distinct auc from Auction as auc JOIN auc.categoryidCategory cat where cat.idCategory = :idCategory and auc.active = 1").setHint("toplink.refresh", "true");
        q.setParameter("idCategory", idCategory);
        return q.getResultList();
    }

}
