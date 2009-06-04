/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.persistence;

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
import javax.persistence.Lob;
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
@Table(name = "proposeditem")
@NamedQueries({@NamedQuery(name = "Proposeditem.findAll", query = "SELECT p FROM Proposeditem p"), @NamedQuery(name = "Proposeditem.findByIdItem", query = "SELECT p FROM Proposeditem p WHERE p.idItem = :idItem"), @NamedQuery(name = "Proposeditem.findByUserNickname", query = "SELECT p FROM Proposeditem p WHERE p.userNickname = :userNickname"), @NamedQuery(name = "Proposeditem.findByTitle", query = "SELECT p FROM Proposeditem p WHERE p.title = :title"), @NamedQuery(name = "Proposeditem.findByEndDate", query = "SELECT p FROM Proposeditem p WHERE p.endDate = :endDate"), @NamedQuery(name = "Proposeditem.findByPublishDate", query = "SELECT p FROM Proposeditem p WHERE p.publishDate = :publishDate"), @NamedQuery(name = "Proposeditem.findByFileName", query = "SELECT p FROM Proposeditem p WHERE p.fileName = :fileName"), @NamedQuery(name = "Proposeditem.findByActive", query = "SELECT p FROM Proposeditem p WHERE p.active = :active")})
public class Proposeditem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItem")
    private Integer idItem;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Lob
    @Column(name = "picture")
    private byte[] picture;
    @Basic(optional = false)
    @Column(name = "user_nickname")
    private String userNickname;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "publishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    @Basic(optional = false)
    @Column(name = "fileName")
    private String fileName;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemidItem")
    private List<Proposeditemvote> proposeditemvoteCollection;

    public Proposeditem() {
    }

    public Proposeditem(Integer idItem) {
        this.idItem = idItem;
    }

    public Proposeditem(Integer idItem, String description, byte[] picture, String userNickname, String title, Date publishDate, String fileName, boolean active) {
        this.idItem = idItem;
        this.description = description;
        this.picture = picture;
        this.userNickname = userNickname;
        this.title = title;
        this.publishDate = publishDate;
        this.fileName = fileName;
        this.active = active;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Proposeditemvote> getProposeditemvoteCollection() {
        return proposeditemvoteCollection;
    }

    public void setProposeditemvoteCollection(List<Proposeditemvote> proposeditemvoteCollection) {
        this.proposeditemvoteCollection = proposeditemvoteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItem != null ? idItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proposeditem)) {
            return false;
        }
        Proposeditem other = (Proposeditem) object;
        if ((this.idItem == null && other.idItem != null) || (this.idItem != null && !this.idItem.equals(other.idItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Proposeditem[idItem=" + idItem + "]";
    }

}
