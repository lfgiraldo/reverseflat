/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "chipsincome")
@NamedQueries({@NamedQuery(name = "Chipsincome.findAll", query = "SELECT c FROM Chipsincome c"), @NamedQuery(name = "Chipsincome.findByDate", query = "SELECT c FROM Chipsincome c WHERE c.date = :date"), @NamedQuery(name = "Chipsincome.findByChipsAmount", query = "SELECT c FROM Chipsincome c WHERE c.chipsAmount = :chipsAmount"), @NamedQuery(name = "Chipsincome.findByMoneyPaid", query = "SELECT c FROM Chipsincome c WHERE c.moneyPaid = :moneyPaid"), @NamedQuery(name = "Chipsincome.findByTransactionNumber", query = "SELECT c FROM Chipsincome c WHERE c.transactionNumber = :transactionNumber"), @NamedQuery(name = "Chipsincome.findByChargeMethod", query = "SELECT c FROM Chipsincome c WHERE c.chargeMethod = :chargeMethod"), @NamedQuery(name = "Chipsincome.findByIdIncome", query = "SELECT c FROM Chipsincome c WHERE c.idIncome = :idIncome")})
public class Chipsincome implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "chipsAmount")
    private int chipsAmount;
    @Basic(optional = false)
    @Column(name = "moneyPaid")
    private BigDecimal moneyPaid;
    @Basic(optional = false)
    @Column(name = "transactionNumber")
    private String transactionNumber;
    @Column(name = "chargeMethod")
    private Short chargeMethod;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idIncome")
    private Long idIncome;
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User useridUser;

    public Chipsincome() {
    }

    public Chipsincome(Long idIncome) {
        this.idIncome = idIncome;
    }

    public Chipsincome(Long idIncome, Date date, int chipsAmount, BigDecimal moneyPaid, String transactionNumber) {
        this.idIncome = idIncome;
        this.date = date;
        this.chipsAmount = chipsAmount;
        this.moneyPaid = moneyPaid;
        this.transactionNumber = transactionNumber;
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

    public BigDecimal getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(BigDecimal moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Short getChargeMethod() {
        return chargeMethod;
    }

    public void setChargeMethod(Short chargeMethod) {
        this.chargeMethod = chargeMethod;
    }

    public Long getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(Long idIncome) {
        this.idIncome = idIncome;
    }

    public User getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(User useridUser) {
        this.useridUser = useridUser;
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
        if (!(object instanceof Chipsincome)) {
            return false;
        }
        Chipsincome other = (Chipsincome) object;
        if ((this.idIncome == null && other.idIncome != null) || (this.idIncome != null && !this.idIncome.equals(other.idIncome))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Chipsincome[idIncome=" + idIncome + "]";
    }

}
