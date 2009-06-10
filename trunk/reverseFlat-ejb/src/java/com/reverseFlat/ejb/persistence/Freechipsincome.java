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
@Table(name = "freechipsincome")
@NamedQueries({@NamedQuery(name = "Freechipsincome.findAll", query = "SELECT f FROM Freechipsincome f"), @NamedQuery(name = "Freechipsincome.findByIdIncome", query = "SELECT f FROM Freechipsincome f WHERE f.idIncome = :idIncome"), @NamedQuery(name = "Freechipsincome.findByDate", query = "SELECT f FROM Freechipsincome f WHERE f.date = :date"), @NamedQuery(name = "Freechipsincome.findByChipsAmount", query = "SELECT f FROM Freechipsincome f WHERE f.chipsAmount = :chipsAmount")})
public class Freechipsincome implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIncome")
    private Long idIncome;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "chipsAmount")
    private int chipsAmount;
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User useridUser;
    @JoinColumn(name = "user_idUser_referral", referencedColumnName = "idUser")
    @ManyToOne
    private User useridUserreferral;

    public Freechipsincome() {
    }

    public Freechipsincome(Long idIncome) {
        this.idIncome = idIncome;
    }

    public Freechipsincome(Long idIncome, Date date, int chipsAmount) {
        this.idIncome = idIncome;
        this.date = date;
        this.chipsAmount = chipsAmount;
    }

    public Long getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(Long idIncome) {
        this.idIncome = idIncome;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getChipsAmount() {
        return chipsAmount;
    }

    public void setChipsAmount(int chipsAmount) {
        this.chipsAmount = chipsAmount;
    }

    public User getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(User useridUser) {
        this.useridUser = useridUser;
    }

    public User getUseridUserreferral() {
        return useridUserreferral;
    }

    public void setUseridUserreferral(User useridUserreferral) {
        this.useridUserreferral = useridUserreferral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncome != null ? idIncome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Freechipsincome)) {
            return false;
        }
        Freechipsincome other = (Freechipsincome) object;
        if ((this.idIncome == null && other.idIncome != null) || (this.idIncome != null && !this.idIncome.equals(other.idIncome))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Freechipsincome[idIncome=" + idIncome + "]";
    }

}
