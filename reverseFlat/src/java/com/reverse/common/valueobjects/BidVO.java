/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.common.valueobjects;

//import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lfgiraldo
 */
public class BidVO implements Serializable {
    private Integer idBid;
    private Date bidDate;
    private float bidAmount;
    private Integer auctionidAuction;
    private UserVO useridUser;

    public BidVO() {
    }

    public BidVO(Integer idBid) {
        this.idBid = idBid;
    }

    public BidVO(Integer idBid, Date bidDate, float bidAmount) {
        this.idBid = idBid;
        this.bidDate = bidDate;
        this.bidAmount = bidAmount;
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

    public Integer getAuctionidAuction() {
        return auctionidAuction;
    }

    public void setAuctionidAuction(Integer auctionidAuction) {
        this.auctionidAuction = auctionidAuction;
    }

    public UserVO getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(UserVO useridUser) {
        this.useridUser = useridUser;
    }
}
