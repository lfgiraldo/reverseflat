/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.common.valueobjects;

//import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lfgiraldo
 */
public class AuctionVO implements Serializable {
    private Integer idAuction;
    private int auctionMinBids;
    private Date auctionBidEndDate;
    private Date auctionPublishDate;
    private String circledPicture;
    private String gamePicture;
    private String detailsCollagePicture;
    private String title;
    private String userNickname;
    //private BidVO[] bidCollection;
    private String fileName;
    private boolean active;
    private int minBids;
    private Date endDate;
    private Date publishDate;
    private String shortDescription;
    private String type;
    private float cogs;
    private float grossMargin;
    private float chipsPerBid3rdQ;
    private float chipsPerBid1stQ;
    private float chipsPerBid4thQ;
    private float chipsPerBid2ndQ;
    private float chipPrice;
    private int minutesAfterClose;
    private String longDescription;



    public AuctionVO() {
    }

    public AuctionVO(Integer idAuction) {
        this.idAuction = idAuction;
    }


    public Integer getIdAuction() {
        return idAuction;
    }

    public void setIdAuction(Integer idAuction) {
        this.idAuction = idAuction;
    }

    public int getAuctionMinBids() {
        return auctionMinBids;
    }

    public void setAuctionMinBids(int auctionMinBids) {
        this.auctionMinBids = auctionMinBids;
    }

    public Date getAuctionBidEndDate() {
        return auctionBidEndDate;
    }

    public void setAuctionBidEndDate(Date auctionBidEndDate) {
        this.auctionBidEndDate = auctionBidEndDate;
    }

    public Date getAuctionPublishDate() {
        return auctionPublishDate;
    }

    public void setAuctionPublishDate(Date auctionPublishDate) {
        this.auctionPublishDate = auctionPublishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /*
    public BidVO[] getBidCollection() {
        return bidCollection;
    }

    public void setBidCollection(BidVO[] bidCollection) {
        this.bidCollection = bidCollection;
    }

     */
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getChipPrice() {
        return chipPrice;
    }

    public void setChipPrice(float chipPrice) {
        this.chipPrice = chipPrice;
    }

    public float getChipsPerBid1stQ() {
        return chipsPerBid1stQ;
    }

    public void setChipsPerBid1stQ(float chipsPerBid1stQ) {
        this.chipsPerBid1stQ = chipsPerBid1stQ;
    }

    public float getChipsPerBid2ndQ() {
        return chipsPerBid2ndQ;
    }

    public void setChipsPerBid2ndQ(float chipsPerBid2ndQ) {
        this.chipsPerBid2ndQ = chipsPerBid2ndQ;
    }

    public float getChipsPerBid3rdQ() {
        return chipsPerBid3rdQ;
    }

    public void setChipsPerBid3rdQ(float chipsPerBid3rdQ) {
        this.chipsPerBid3rdQ = chipsPerBid3rdQ;
    }

    public float getChipsPerBid4thQ() {
        return chipsPerBid4thQ;
    }

    public void setChipsPerBid4thQ(float chipsPerBid4thQ) {
        this.chipsPerBid4thQ = chipsPerBid4thQ;
    }

    public float getCogs() {
        return cogs;
    }

    public void setCogs(float cogs) {
        this.cogs = cogs;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(float grossMargin) {
        this.grossMargin = grossMargin;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getMinBids() {
        return minBids;
    }

    public void setMinBids(int minBids) {
        this.minBids = minBids;
    }

    public int getMinutesAfterClose() {
        return minutesAfterClose;
    }

    public void setMinutesAfterClose(int minutesAfterClose) {
        this.minutesAfterClose = minutesAfterClose;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCircledPicture() {
        return circledPicture;
    }

    public void setCircledPicture(String circledPicture) {
        this.circledPicture = circledPicture;
    }

    public String getDetailsCollagePicture() {
        return detailsCollagePicture;
    }

    public void setDetailsCollagePicture(String detailsCollagePicture) {
        this.detailsCollagePicture = detailsCollagePicture;
    }

    public String getGamePicture() {
        return gamePicture;
    }

    public void setGamePicture(String gamePicture) {
        this.gamePicture = gamePicture;
    }



}
