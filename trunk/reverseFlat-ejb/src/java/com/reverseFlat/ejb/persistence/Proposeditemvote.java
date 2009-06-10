/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "proposeditemvote")
@NamedQueries({@NamedQuery(name = "Proposeditemvote.findAll", query = "SELECT p FROM Proposeditemvote p"), @NamedQuery(name = "Proposeditemvote.findByIdVote", query = "SELECT p FROM Proposeditemvote p WHERE p.idVote = :idVote"), @NamedQuery(name = "Proposeditemvote.findByUserNickname", query = "SELECT p FROM Proposeditemvote p WHERE p.userNickname = :userNickname"), @NamedQuery(name = "Proposeditemvote.findByVoteDate", query = "SELECT p FROM Proposeditemvote p WHERE p.voteDate = :voteDate")})
public class Proposeditemvote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVote")
    private Integer idVote;
    @Basic(optional = false)
    @Column(name = "user_nickname")
    private String userNickname;
    @Lob
    @Column(name = "userComment")
    private String userComment;
    @Basic(optional = false)
    @Column(name = "voteDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date voteDate;
    @JoinColumn(name = "item_idItem", referencedColumnName = "idItem")
    @ManyToOne(optional = false)
    private Proposeditem itemidItem;

    public Proposeditemvote() {
    }

    public Proposeditemvote(Integer idVote) {
        this.idVote = idVote;
    }

    public Proposeditemvote(Integer idVote, String userNickname, Date voteDate) {
        this.idVote = idVote;
        this.userNickname = userNickname;
        this.voteDate = voteDate;
    }

    public Integer getIdVote() {
        return idVote;
    }

    public void setIdVote(Integer idVote) {
        this.idVote = idVote;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public Proposeditem getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(Proposeditem itemidItem) {
        this.itemidItem = itemidItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVote != null ? idVote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proposeditemvote)) {
            return false;
        }
        Proposeditemvote other = (Proposeditemvote) object;
        if ((this.idVote == null && other.idVote != null) || (this.idVote != null && !this.idVote.equals(other.idVote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Proposeditemvote[idVote=" + idVote + "]";
    }

}
