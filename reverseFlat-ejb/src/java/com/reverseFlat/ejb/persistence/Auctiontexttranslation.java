/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "auctiontexttranslation")
@NamedQueries({@NamedQuery(name = "Auctiontexttranslation.findAll", query = "SELECT a FROM Auctiontexttranslation a"), @NamedQuery(name = "Auctiontexttranslation.findByLanguageCode", query = "SELECT a FROM Auctiontexttranslation a WHERE a.languageCode = :languageCode"), @NamedQuery(name = "Auctiontexttranslation.findByTitle", query = "SELECT a FROM Auctiontexttranslation a WHERE a.title = :title"), @NamedQuery(name = "Auctiontexttranslation.findByIdTranslation", query = "SELECT a FROM Auctiontexttranslation a WHERE a.idTranslation = :idTranslation")})
public class Auctiontexttranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "languageCode")
    private String languageCode;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "shortDescription")
    private String shortDescription;
    @Basic(optional = false)
    @Lob
    @Column(name = "longDescription")
    private String longDescription;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTranslation")
    private Short idTranslation;
    @JoinColumn(name = "auction_idAuction", referencedColumnName = "idAuction")
    @ManyToOne(optional = false)
    private Auction auctionidAuction;

    public Auctiontexttranslation() {
    }

    public Auctiontexttranslation(Short idTranslation) {
        this.idTranslation = idTranslation;
    }

    public Auctiontexttranslation(Short idTranslation, String languageCode, String title, String shortDescription, String longDescription) {
        this.idTranslation = idTranslation;
        this.languageCode = languageCode;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Short getIdTranslation() {
        return idTranslation;
    }

    public void setIdTranslation(Short idTranslation) {
        this.idTranslation = idTranslation;
    }

    public Auction getAuctionidAuction() {
        return auctionidAuction;
    }

    public void setAuctionidAuction(Auction auctionidAuction) {
        this.auctionidAuction = auctionidAuction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTranslation != null ? idTranslation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auctiontexttranslation)) {
            return false;
        }
        Auctiontexttranslation other = (Auctiontexttranslation) object;
        if ((this.idTranslation == null && other.idTranslation != null) || (this.idTranslation != null && !this.idTranslation.equals(other.idTranslation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Auctiontexttranslation[idTranslation=" + idTranslation + "]";
    }

}
