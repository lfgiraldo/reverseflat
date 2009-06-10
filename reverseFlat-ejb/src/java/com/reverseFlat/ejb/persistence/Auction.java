/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "auction")
@NamedQueries({@NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"), @NamedQuery(name = "Auction.findByIdAuction", query = "SELECT a FROM Auction a WHERE a.idAuction = :idAuction"), @NamedQuery(name = "Auction.findByMinBids", query = "SELECT a FROM Auction a WHERE a.minBids = :minBids"), @NamedQuery(name = "Auction.findByEndDate", query = "SELECT a FROM Auction a WHERE a.endDate = :endDate"), @NamedQuery(name = "Auction.findByPublishDate", query = "SELECT a FROM Auction a WHERE a.publishDate = :publishDate"), @NamedQuery(name = "Auction.findByUserNickname", query = "SELECT a FROM Auction a WHERE a.userNickname = :userNickname"), @NamedQuery(name = "Auction.findByPictureFileName", query = "SELECT a FROM Auction a WHERE a.pictureFileName = :pictureFileName"), @NamedQuery(name = "Auction.findByActive", query = "SELECT a FROM Auction a WHERE a.active = :active"), @NamedQuery(name = "Auction.findByType", query = "SELECT a FROM Auction a WHERE a.type = :type"), @NamedQuery(name = "Auction.findByCogs", query = "SELECT a FROM Auction a WHERE a.cogs = :cogs"), @NamedQuery(name = "Auction.findByGrossMargin", query = "SELECT a FROM Auction a WHERE a.grossMargin = :grossMargin"), @NamedQuery(name = "Auction.findByChipsPerBid3rdQ", query = "SELECT a FROM Auction a WHERE a.chipsPerBid3rdQ = :chipsPerBid3rdQ"), @NamedQuery(name = "Auction.findByChipsPerBid1stQ", query = "SELECT a FROM Auction a WHERE a.chipsPerBid1stQ = :chipsPerBid1stQ"), @NamedQuery(name = "Auction.findByChipsPerBid4thQ", query = "SELECT a FROM Auction a WHERE a.chipsPerBid4thQ = :chipsPerBid4thQ"), @NamedQuery(name = "Auction.findByChipsPerBid2ndQ", query = "SELECT a FROM Auction a WHERE a.chipsPerBid2ndQ = :chipsPerBid2ndQ"), @NamedQuery(name = "Auction.findByMinutesAfterClose", query = "SELECT a FROM Auction a WHERE a.minutesAfterClose = :minutesAfterClose"), @NamedQuery(name = "Auction.findByDeadlineToRegister", query = "SELECT a FROM Auction a WHERE a.deadlineToRegister = :deadlineToRegister"), @NamedQuery(name = "Auction.findByRequiredUsers", query = "SELECT a FROM Auction a WHERE a.requiredUsers = :requiredUsers"), @NamedQuery(name = "Auction.findByOpeningDate", query = "SELECT a FROM Auction a WHERE a.openingDate = :openingDate"), @NamedQuery(name = "Auction.findByClosingDate", query = "SELECT a FROM Auction a WHERE a.closingDate = :closingDate"), @NamedQuery(name = "Auction.findByMosaicFileName", query = "SELECT a FROM Auction a WHERE a.mosaicFileName = :mosaicFileName"), @NamedQuery(name = "Auction.findByCircledFileName", query = "SELECT a FROM Auction a WHERE a.circledFileName = :circledFileName"), @NamedQuery(name = "Auction.findByChipCost", query = "SELECT a FROM Auction a WHERE a.chipCost = :chipCost")})
public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAuction")
    private Integer idAuction;
    @Basic(optional = false)
    @Column(name = "minBids")
    private int minBids;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "publishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    @Column(name = "user_nickname")
    private String userNickname;
    @Basic(optional = false)
    @Column(name = "pictureFileName")
    private String pictureFileName;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @Column(name = "cogs")
    private float cogs;
    @Basic(optional = false)
    @Column(name = "grossMargin")
    private float grossMargin;
    @Basic(optional = false)
    @Column(name = "chipsPerBid3rdQ")
    private short chipsPerBid3rdQ;
    @Basic(optional = false)
    @Column(name = "chipsPerBid1stQ")
    private short chipsPerBid1stQ;
    @Basic(optional = false)
    @Column(name = "chipsPerBid4thQ")
    private short chipsPerBid4thQ;
    @Basic(optional = false)
    @Column(name = "chipsPerBid2ndQ")
    private short chipsPerBid2ndQ;
    @Basic(optional = false)
    @Column(name = "minutesAfterClose")
    private short minutesAfterClose;
    @Lob
    @Column(name = "circledPicture")
    private byte[] circledPicture;
    @Lob
    @Column(name = "mosaicPicture")
    private byte[] mosaicPicture;
    @Column(name = "deadlineToRegister")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadlineToRegister;
    @Column(name = "requiredUsers")
    private Short requiredUsers;
    @Column(name = "openingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;
    @Column(name = "closingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;
    @Basic(optional = false)
    @Column(name = "mosaicFileName")
    private String mosaicFileName;
    @Basic(optional = false)
    @Column(name = "circledFileName")
    private String circledFileName;
    @Basic(optional = false)
    @Column(name = "chipCost")
    private float chipCost;
    @JoinTable(name = "registereduserinclub", joinColumns = {@JoinColumn(name = "auction_idAuction", referencedColumnName = "idAuction")}, inverseJoinColumns = {@JoinColumn(name = "user_idUser", referencedColumnName = "idUser")})
    @ManyToMany
    private List<User> userCollection;
    @JoinColumn(name = "category_idCategory", referencedColumnName = "idCategory")
    @ManyToOne(optional = false)
    private Auctioncategory categoryidCategory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    private List<Pricebyauction> pricebyauctionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionidAuction")
    private List<Chipsexpenditure> chipsexpenditureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionidAuction")
    private List<Auctiontexttranslation> auctiontexttranslationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auctionidAuction")
    private List<Bid> bidCollection;

    public Auction() {
    }

    public Auction(Integer idAuction) {
        this.idAuction = idAuction;
    }

    public Auction(Integer idAuction, int minBids, Date publishDate, byte[] picture, String pictureFileName, boolean active, float cogs, float grossMargin, short chipsPerBid3rdQ, short chipsPerBid1stQ, short chipsPerBid4thQ, short chipsPerBid2ndQ, short minutesAfterClose, String mosaicFileName, String circledFileName, float chipCost) {
        this.idAuction = idAuction;
        this.minBids = minBids;
        this.publishDate = publishDate;
        this.picture = picture;
        this.pictureFileName = pictureFileName;
        this.active = active;
        this.cogs = cogs;
        this.grossMargin = grossMargin;
        this.chipsPerBid3rdQ = chipsPerBid3rdQ;
        this.chipsPerBid1stQ = chipsPerBid1stQ;
        this.chipsPerBid4thQ = chipsPerBid4thQ;
        this.chipsPerBid2ndQ = chipsPerBid2ndQ;
        this.minutesAfterClose = minutesAfterClose;
        this.mosaicFileName = mosaicFileName;
        this.circledFileName = circledFileName;
        this.chipCost = chipCost;
    }

    public Integer getIdAuction() {
        return idAuction;
    }

    public void setIdAuction(Integer idAuction) {
        this.idAuction = idAuction;
    }

    public int getMinBids() {
        return minBids;
    }

    public void setMinBids(int minBids) {
        this.minBids = minBids;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCogs() {
        return cogs;
    }

    public void setCogs(float cogs) {
        this.cogs = cogs;
    }

    public float getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(float grossMargin) {
        this.grossMargin = grossMargin;
    }

    public short getChipsPerBid3rdQ() {
        return chipsPerBid3rdQ;
    }

    public void setChipsPerBid3rdQ(short chipsPerBid3rdQ) {
        this.chipsPerBid3rdQ = chipsPerBid3rdQ;
    }

    public short getChipsPerBid1stQ() {
        return chipsPerBid1stQ;
    }

    public void setChipsPerBid1stQ(short chipsPerBid1stQ) {
        this.chipsPerBid1stQ = chipsPerBid1stQ;
    }

    public short getChipsPerBid4thQ() {
        return chipsPerBid4thQ;
    }

    public void setChipsPerBid4thQ(short chipsPerBid4thQ) {
        this.chipsPerBid4thQ = chipsPerBid4thQ;
    }

    public short getChipsPerBid2ndQ() {
        return chipsPerBid2ndQ;
    }

    public void setChipsPerBid2ndQ(short chipsPerBid2ndQ) {
        this.chipsPerBid2ndQ = chipsPerBid2ndQ;
    }

    public short getMinutesAfterClose() {
        return minutesAfterClose;
    }

    public void setMinutesAfterClose(short minutesAfterClose) {
        this.minutesAfterClose = minutesAfterClose;
    }

    public byte[] getCircledPicture() {
        return circledPicture;
    }

    public void setCircledPicture(byte[] circledPicture) {
        this.circledPicture = circledPicture;
    }

    public byte[] getMosaicPicture() {
        return mosaicPicture;
    }

    public void setMosaicPicture(byte[] mosaicPicture) {
        this.mosaicPicture = mosaicPicture;
    }

    public Date getDeadlineToRegister() {
        return deadlineToRegister;
    }

    public void setDeadlineToRegister(Date deadlineToRegister) {
        this.deadlineToRegister = deadlineToRegister;
    }

    public Short getRequiredUsers() {
        return requiredUsers;
    }

    public void setRequiredUsers(Short requiredUsers) {
        this.requiredUsers = requiredUsers;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getMosaicFileName() {
        return mosaicFileName;
    }

    public void setMosaicFileName(String mosaicFileName) {
        this.mosaicFileName = mosaicFileName;
    }

    public String getCircledFileName() {
        return circledFileName;
    }

    public void setCircledFileName(String circledFileName) {
        this.circledFileName = circledFileName;
    }

    public float getChipCost() {
        return chipCost;
    }

    public void setChipCost(float chipCost) {
        this.chipCost = chipCost;
    }

    public List<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(List<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Auctioncategory getCategoryidCategory() {
        return categoryidCategory;
    }

    public void setCategoryidCategory(Auctioncategory categoryidCategory) {
        this.categoryidCategory = categoryidCategory;
    }

    public List<Pricebyauction> getPricebyauctionCollection() {
        return pricebyauctionCollection;
    }

    public void setPricebyauctionCollection(List<Pricebyauction> pricebyauctionCollection) {
        this.pricebyauctionCollection = pricebyauctionCollection;
    }

    public List<Chipsexpenditure> getChipsexpenditureCollection() {
        return chipsexpenditureCollection;
    }

    public void setChipsexpenditureCollection(List<Chipsexpenditure> chipsexpenditureCollection) {
        this.chipsexpenditureCollection = chipsexpenditureCollection;
    }

    public List<Auctiontexttranslation> getAuctiontexttranslationCollection() {
        return auctiontexttranslationCollection;
    }

    public void setAuctiontexttranslationCollection(List<Auctiontexttranslation> auctiontexttranslationCollection) {
        this.auctiontexttranslationCollection = auctiontexttranslationCollection;
    }

    public List<Bid> getBidCollection() {
        return bidCollection;
    }

    public void setBidCollection(List<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuction != null ? idAuction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        if ((this.idAuction == null && other.idAuction != null) || (this.idAuction != null && !this.idAuction.equals(other.idAuction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Auction[idAuction=" + idAuction + "]";
    }

}
