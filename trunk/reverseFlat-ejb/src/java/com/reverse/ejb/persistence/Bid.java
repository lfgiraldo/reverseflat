/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "bid")
@NamedQueries({@NamedQuery(name = "Bid.findAll", query = "SELECT b FROM Bid b"), @NamedQuery(name = "Bid.findByIdBid", query = "SELECT b FROM Bid b WHERE b.idBid = :idBid"), @NamedQuery(name = "Bid.findByBidDate", query = "SELECT b FROM Bid b WHERE b.bidDate = :bidDate"), @NamedQuery(name = "Bid.findByBidAmount", query = "SELECT b FROM Bid b WHERE b.bidAmount = :bidAmount"), @NamedQuery(name = "Bid.findByChipsAmount", query = "SELECT b FROM Bid b WHERE b.chipsAmount = :chipsAmount")})
public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBid")
    private Integer idBid;
    @Basic(optional = false)
    @Column(name = "bidDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidDate;
    @Basic(optional = false)
    @Column(name = "bidAmount")
    private float bidAmount;
    @Basic(optional = false)
    @Column(name = "chipsAmount")
    private int chipsAmount;
    @JoinColumn(name = "auction_idAuction", referencedColumnName = "idAuction")
    @ManyToOne(optional = false)
    private Auction auctionidAuction;
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User useridUser;

    public Bid() {
    }

    public Bid(Integer idBid) {
        this.idBid = idBid;
    }

    public Bid(Integer idBid, Date bidDate, float bidAmount, int chipsAmount) {
        this.idBid = idBid;
        this.bidDate = bidDate;
        this.bidAmount = bidAmount;
        this.chipsAmount = chipsAmount;
    }

    public Integer getIdBid() {
        return idBid;
    }

    public void setIdBid(Integer idBid) {
        this.idBid = idBid;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public float getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(float bidAmount) {
        this.bidAmount = bidAmount;
    }

    public int getChipsAmount() {
        return chipsAmount;
    }

    public void setChipsAmount(int chipsAmount) {
        this.chipsAmount = chipsAmount;
    }

    public Auction getAuctionidAuction() {
        return auctionidAuction;
    }

    public void setAuctionidAuction(Auction auctionidAuction) {
        this.auctionidAuction = auctionidAuction;
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
        hash += (idBid != null ? idBid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.idBid == null && other.idBid != null) || (this.idBid != null && !this.idBid.equals(other.idBid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Bid[idBid=" + idBid + "]";
    }

}
