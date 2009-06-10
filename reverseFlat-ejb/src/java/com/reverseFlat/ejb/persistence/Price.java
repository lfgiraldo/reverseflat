/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "price")
@NamedQueries({@NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p"), @NamedQuery(name = "Price.findByIdPrice", query = "SELECT p FROM Price p WHERE p.idPrice = :idPrice"), @NamedQuery(name = "Price.findByTitle", query = "SELECT p FROM Price p WHERE p.title = :title")})
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrice")
    private Short idPrice;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Lob
    @Column(name = "description")
    private String description;
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceidPrice")
    private List<Pricebyauction> pricebyauctionCollection;

    public Price() {
    }

    public Price(Short idPrice) {
        this.idPrice = idPrice;
    }

    public Price(Short idPrice, String title) {
        this.idPrice = idPrice;
        this.title = title;
    }

    public Short getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(Short idPrice) {
        this.idPrice = idPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<Pricebyauction> getPricebyauctionCollection() {
        return pricebyauctionCollection;
    }

    public void setPricebyauctionCollection(List<Pricebyauction> pricebyauctionCollection) {
        this.pricebyauctionCollection = pricebyauctionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrice != null ? idPrice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Price)) {
            return false;
        }
        Price other = (Price) object;
        if ((this.idPrice == null && other.idPrice != null) || (this.idPrice != null && !this.idPrice.equals(other.idPrice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Price[idPrice=" + idPrice + "]";
    }

}
