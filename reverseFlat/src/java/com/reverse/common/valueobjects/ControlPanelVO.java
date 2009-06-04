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
 * @author Luis Felipe Giraldo
 */
public class ControlPanelVO implements Serializable {
    private int chipsBalance;
    private String lastBidValue;
    private int actualBidCost;
    private String actualWinner;
    private String uniqueBids;
    private BidVO lastBid;
    private String hint;
    private float firstQ;
    private float secondQ;
    private float thirdQ;
    private String firstQCost;
    private String secondQCost;
    private String thirdQCost;
    private Date endDate;
    private String userBids;

    private AuctionVO currentAuction;

    public int getActualBidCost() {
        return actualBidCost;
    }

    public void setActualBidCost(int actualBidCost) {
        this.actualBidCost = actualBidCost;
    }

    public String getActualWinner() {
        return actualWinner;
    }

    public void setActualWinner(String actualWinner) {
        this.actualWinner = actualWinner;
    }

    public int getChipsBalance() {
        return chipsBalance;
    }

    public void setChipsBalance(int chipsBalance) {
        this.chipsBalance = chipsBalance;
    }

    public String getLastBidValue() {
        return lastBidValue;
    }

    public void setLastBidValue(String lastBidValue) {
        this.lastBidValue = lastBidValue;
    }

    public BidVO getLastBid() {
        return lastBid;
    }

    public void setLastBid(BidVO lastBid) {
        this.lastBid = lastBid;
    }

    public String getUniqueBids() {
        return uniqueBids;
    }

    public void setUniqueBids(String uniqueBids) {
        this.uniqueBids = uniqueBids;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public float getFirstQ() {
        return firstQ;
    }

    public void setFirstQ(float firstQ) {
        this.firstQ = firstQ;
    }

    public float getSecondQ() {
        return secondQ;
    }

    public void setSecondQ(float secondQ) {
        this.secondQ = secondQ;
    }

    public float getThirdQ() {
        return thirdQ;
    }

    public void setThirdQ(float thirdQ) {
        this.thirdQ = thirdQ;
    }

    public String getFirstQCost() {
        return firstQCost;
    }

    public void setFirstQCost(String firstQCost) {
        this.firstQCost = firstQCost;
    }

    public String getSecondQCost() {
        return secondQCost;
    }

    public void setSecondQCost(String secondQCost) {
        this.secondQCost = secondQCost;
    }

    public String getThirdQCost() {
        return thirdQCost;
    }

    public void setThirdQCost(String thirdQCost) {
        this.thirdQCost = thirdQCost;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserBids() {
        return userBids;
    }

    public void setUserBids(String userBids) {
        this.userBids = userBids;
    }

    public AuctionVO getCurrentAuction() {
        return currentAuction;
    }

    public void setCurrentAuction(AuctionVO currentAuction) {
        this.currentAuction = currentAuction;
    }

    

}
