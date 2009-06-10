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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "combochips")
@NamedQueries({@NamedQuery(name = "Combochips.findAll", query = "SELECT c FROM Combochips c"), @NamedQuery(name = "Combochips.findByIdCombo", query = "SELECT c FROM Combochips c WHERE c.idCombo = :idCombo"), @NamedQuery(name = "Combochips.findByChipsAmount", query = "SELECT c FROM Combochips c WHERE c.chipsAmount = :chipsAmount"), @NamedQuery(name = "Combochips.findByFreeChipsAmount", query = "SELECT c FROM Combochips c WHERE c.freeChipsAmount = :freeChipsAmount"), @NamedQuery(name = "Combochips.findByCost", query = "SELECT c FROM Combochips c WHERE c.cost = :cost")})
public class Combochips implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCombo")
    private Short idCombo;
    @Basic(optional = false)
    @Column(name = "chipsAmount")
    private short chipsAmount;
    @Basic(optional = false)
    @Column(name = "freeChipsAmount")
    private short freeChipsAmount;
    @Basic(optional = false)
    @Column(name = "cost")
    private float cost;

    public Combochips() {
    }

    public Combochips(Short idCombo) {
        this.idCombo = idCombo;
    }

    public Combochips(Short idCombo, short chipsAmount, short freeChipsAmount, float cost) {
        this.idCombo = idCombo;
        this.chipsAmount = chipsAmount;
        this.freeChipsAmount = freeChipsAmount;
        this.cost = cost;
    }

    public Short getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Short idCombo) {
        this.idCombo = idCombo;
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCombo != null ? idCombo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Combochips)) {
            return false;
        }
        Combochips other = (Combochips) object;
        if ((this.idCombo == null && other.idCombo != null) || (this.idCombo != null && !this.idCombo.equals(other.idCombo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Combochips[idCombo=" + idCombo + "]";
    }

}
