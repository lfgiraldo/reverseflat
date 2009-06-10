/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "snoitsplan")
@NamedQueries({@NamedQuery(name = "Snoitsplan.findAll", query = "SELECT s FROM Snoitsplan s"), @NamedQuery(name = "Snoitsplan.findByIdPlan", query = "SELECT s FROM Snoitsplan s WHERE s.idPlan = :idPlan"), @NamedQuery(name = "Snoitsplan.findByChipsAmount", query = "SELECT s FROM Snoitsplan s WHERE s.chipsAmount = :chipsAmount"), @NamedQuery(name = "Snoitsplan.findByFreeChipsAmount", query = "SELECT s FROM Snoitsplan s WHERE s.freeChipsAmount = :freeChipsAmount"), @NamedQuery(name = "Snoitsplan.findByCost", query = "SELECT s FROM Snoitsplan s WHERE s.cost = :cost")})
public class Snoitsplan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPlan")
    private Short idPlan;
    @Basic(optional = false)
    @Column(name = "chipsAmount")
    private short chipsAmount;
    @Basic(optional = false)
    @Column(name = "freeChipsAmount")
    private short freeChipsAmount;
    @Basic(optional = false)
    @Column(name = "cost")
    private BigDecimal cost;

    public Snoitsplan() {
    }

    public Snoitsplan(Short idPlan) {
        this.idPlan = idPlan;
    }

    public Snoitsplan(Short idPlan, short chipsAmount, short freeChipsAmount, BigDecimal cost) {
        this.idPlan = idPlan;
        this.chipsAmount = chipsAmount;
        this.freeChipsAmount = freeChipsAmount;
        this.cost = cost;
    }

    public Short getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Short idPlan) {
        this.idPlan = idPlan;
    }

    public short getChipsAmount() {
        return chipsAmount;
    }

    public void setChipsAmount(short chipsAmount) {
        this.chipsAmount = chipsAmount;
    }

    public short getFreeChipsAmount() {
        return freeChipsAmount;
    }

    public void setFreeChipsAmount(short freeChipsAmount) {
        this.freeChipsAmount = freeChipsAmount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Snoitsplan)) {
            return false;
        }
        Snoitsplan other = (Snoitsplan) object;
        if ((this.idPlan == null && other.idPlan != null) || (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Snoitsplan[idPlan=" + idPlan + "]";
    }

}
