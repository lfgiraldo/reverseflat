/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "pricebyauction")
@NamedQueries({@NamedQuery(name = "Pricebyauction.findAll", query = "SELECT p FROM Pricebyauction p"), @NamedQuery(name = "Pricebyauction.findByAuctionidAuction", query = "SELECT p FROM Pricebyauction p WHERE p.pricebyauctionPK.auctionidAuction = :auctionidAuction"), @NamedQuery(name = "Pricebyauction.findByQuarter", query = "SELECT p FROM Pricebyauction p WHERE p.pricebyauctionPK.quarter = :quarter")})
public class Pricebyauction implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PricebyauctionPK pricebyauctionPK;
    @JoinColumn(name = "price_idPrice", referencedColumnName = "idPrice")
    @ManyToOne(optional = false)
    private Price priceidPrice;
    @JoinColumn(name = "auction_idAuction", referencedColumnName = "idAuction", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Auction auction;
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser")
    @ManyToOne
    private User useridUser;

    public Pricebyauction() {
    }

    public Pricebyauction(PricebyauctionPK pricebyauctionPK) {
        this.pricebyauctionPK = pricebyauctionPK;
    }

    public Pricebyauction(int auctionidAuction, short quarter) {
        this.pricebyauctionPK = new PricebyauctionPK(auctionidAuction, quarter);
    }

    public PricebyauctionPK getPricebyauctionPK() {
        return pricebyauctionPK;
    }

    public void setPricebyauctionPK(PricebyauctionPK pricebyauctionPK) {
        this.pricebyauctionPK = pricebyauctionPK;
    }

    public Price getPriceidPrice() {
        return priceidPrice;
    }

    public void setPriceidPrice(Price priceidPrice) {
        this.priceidPrice = priceidPrice;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(User useridUser) {
        this.useridUser = useridUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricebyauctionPK != null ? pricebyauctionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pricebyauction)) {
            return false;
        }
        Pricebyauction other = (Pricebyauction) object;
        if ((this.pricebyauctionPK == null && other.pricebyauctionPK != null) || (this.pricebyauctionPK != null && !this.pricebyauctionPK.equals(other.pricebyauctionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Pricebyauction[pricebyauctionPK=" + pricebyauctionPK + "]";
    }

}
