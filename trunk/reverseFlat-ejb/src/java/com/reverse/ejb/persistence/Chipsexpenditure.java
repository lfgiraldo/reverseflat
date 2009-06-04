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
@Table(name = "chipsexpenditure")
@NamedQueries({@NamedQuery(name = "Chipsexpenditure.findAll", query = "SELECT c FROM Chipsexpenditure c"), @NamedQuery(name = "Chipsexpenditure.findByDate", query = "SELECT c FROM Chipsexpenditure c WHERE c.date = :date"), @NamedQuery(name = "Chipsexpenditure.findByChipsAmount", query = "SELECT c FROM Chipsexpenditure c WHERE c.chipsAmount = :chipsAmount"), @NamedQuery(name = "Chipsexpenditure.findByIdExpenditure", query = "SELECT c FROM Chipsexpenditure c WHERE c.idExpenditure = :idExpenditure"), @NamedQuery(name = "Chipsexpenditure.findByBidTo", query = "SELECT c FROM Chipsexpenditure c WHERE c.bidTo = :bidTo"), @NamedQuery(name = "Chipsexpenditure.findByBidFrom", query = "SELECT c FROM Chipsexpenditure c WHERE c.bidFrom = :bidFrom"), @NamedQuery(name = "Chipsexpenditure.findByType", query = "SELECT c FROM Chipsexpenditure c WHERE c.type = :type")})
public class Chipsexpenditure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "chipsAmount")
    private int chipsAmount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExpenditure")
    private Long idExpenditure;
    @Column(name = "bidTo")
    private Float bidTo;
    @Basic(optional = false)
    @Column(name = "bidFrom")
    private float bidFrom;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "auction_idAuction", referencedColumnName = "idAuction")
    @ManyToOne(optional = false)
    private Auction auctionidAuction;
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User useridUser;

    public Chipsexpenditure() {
    }

    public Chipsexpenditure(Long idExpenditure) {
        this.idExpenditure = idExpenditure;
    }

    public Chipsexpenditure(Long idExpenditure, Date date, int chipsAmount, float bidFrom, String type) {
        this.idExpenditure = idExpenditure;
        this.date = date;
        this.chipsAmount = chipsAmount;
        this.bidFrom = bidFrom;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getChipsAmount() {
        return chipsAmount;
    }

    public void setChipsAmount(int chipsAmount) {
        this.chipsAmount = chipsAmount;
    }

    public Long getIdExpenditure() {
        return idExpenditure;
    }

    public void setIdExpenditure(Long idExpenditure) {
        this.idExpenditure = idExpenditure;
    }

    public Float getBidTo() {
        return bidTo;
    }

    public void setBidTo(Float bidTo) {
        this.bidTo = bidTo;
    }

    public float getBidFrom() {
        return bidFrom;
    }

    public void setBidFrom(float bidFrom) {
        this.bidFrom = bidFrom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (idExpenditure != null ? idExpenditure.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chipsexpenditure)) {
            return false;
        }
        Chipsexpenditure other = (Chipsexpenditure) object;
        if ((this.idExpenditure == null && other.idExpenditure != null) || (this.idExpenditure != null && !this.idExpenditure.equals(other.idExpenditure))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Chipsexpenditure[idExpenditure=" + idExpenditure + "]";
    }

}
