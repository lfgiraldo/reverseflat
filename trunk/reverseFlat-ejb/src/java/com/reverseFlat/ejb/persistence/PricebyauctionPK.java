/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Embeddable
public class PricebyauctionPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "auction_idAuction")
    private int auctionidAuction;
    @Basic(optional = false)
    @Column(name = "quarter")
    private short quarter;

    public PricebyauctionPK() {
    }

    public PricebyauctionPK(int auctionidAuction, short quarter) {
        this.auctionidAuction = auctionidAuction;
        this.quarter = quarter;
    }

    public int getAuctionidAuction() {
        return auctionidAuction;
    }

    public void setAuctionidAuction(int auctionidAuction) {
        this.auctionidAuction = auctionidAuction;
    }

    public short getQuarter() {
        return quarter;
    }

    public void setQuarter(short quarter) {
        this.quarter = quarter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) auctionidAuction;
        hash += (int) quarter;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PricebyauctionPK)) {
            return false;
        }
        PricebyauctionPK other = (PricebyauctionPK) object;
        if (this.auctionidAuction != other.auctionidAuction) {
            return false;
        }
        if (this.quarter != other.quarter) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.PricebyauctionPK[auctionidAuction=" + auctionidAuction + ", quarter=" + quarter + "]";
    }

}
