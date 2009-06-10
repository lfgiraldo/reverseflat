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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "auctioncategory")
@NamedQueries({@NamedQuery(name = "Auctioncategory.findAll", query = "SELECT a FROM Auctioncategory a"), @NamedQuery(name = "Auctioncategory.findByIdCategory", query = "SELECT a FROM Auctioncategory a WHERE a.idCategory = :idCategory"), @NamedQuery(name = "Auctioncategory.findByCategoryNameEs", query = "SELECT a FROM Auctioncategory a WHERE a.categoryNameEs = :categoryNameEs"), @NamedQuery(name = "Auctioncategory.findByCategoryNameEn", query = "SELECT a FROM Auctioncategory a WHERE a.categoryNameEn = :categoryNameEn")})
public class Auctioncategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCategory")
    private Short idCategory;
    @Basic(optional = false)
    @Column(name = "categoryNameEs")
    private String categoryNameEs;
    @Basic(optional = false)
    @Column(name = "categoryNameEn")
    private String categoryNameEn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryidCategory")
    private List<Auction> auctionCollection;

    public Auctioncategory() {
    }

    public Auctioncategory(Short idCategory) {
        this.idCategory = idCategory;
    }

    public Auctioncategory(Short idCategory, String categoryNameEs, String categoryNameEn) {
        this.idCategory = idCategory;
        this.categoryNameEs = categoryNameEs;
        this.categoryNameEn = categoryNameEn;
    }

    public Short getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Short idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryNameEs() {
        return categoryNameEs;
    }

    public void setCategoryNameEs(String categoryNameEs) {
        this.categoryNameEs = categoryNameEs;
    }

    public String getCategoryNameEn() {
        return categoryNameEn;
    }

    public void setCategoryNameEn(String categoryNameEn) {
        this.categoryNameEn = categoryNameEn;
    }

    public List<Auction> getAuctionCollection() {
        return auctionCollection;
    }

    public void setAuctionCollection(List<Auction> auctionCollection) {
        this.auctionCollection = auctionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategory != null ? idCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auctioncategory)) {
            return false;
        }
        Auctioncategory other = (Auctioncategory) object;
        if ((this.idCategory == null && other.idCategory != null) || (this.idCategory != null && !this.idCategory.equals(other.idCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Auctioncategory[idCategory=" + idCategory + "]";
    }

}
