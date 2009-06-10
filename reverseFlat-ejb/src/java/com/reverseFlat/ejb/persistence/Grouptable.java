/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "grouptable")
@NamedQueries({@NamedQuery(name = "Grouptable.findAll", query = "SELECT g FROM Grouptable g"), @NamedQuery(name = "Grouptable.findByNickname", query = "SELECT g FROM Grouptable g WHERE g.grouptablePK.nickname = :nickname"), @NamedQuery(name = "Grouptable.findByRolename", query = "SELECT g FROM Grouptable g WHERE g.grouptablePK.rolename = :rolename")})
public class Grouptable implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrouptablePK grouptablePK;

    public Grouptable() {
    }

    public Grouptable(GrouptablePK grouptablePK) {
        this.grouptablePK = grouptablePK;
    }

    public Grouptable(String nickname, String rolename) {
        this.grouptablePK = new GrouptablePK(nickname, rolename);
    }

    public GrouptablePK getGrouptablePK() {
        return grouptablePK;
    }

    public void setGrouptablePK(GrouptablePK grouptablePK) {
        this.grouptablePK = grouptablePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grouptablePK != null ? grouptablePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grouptable)) {
            return false;
        }
        Grouptable other = (Grouptable) object;
        if ((this.grouptablePK == null && other.grouptablePK != null) || (this.grouptablePK != null && !this.grouptablePK.equals(other.grouptablePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Grouptable[grouptablePK=" + grouptablePK + "]";
    }

}
