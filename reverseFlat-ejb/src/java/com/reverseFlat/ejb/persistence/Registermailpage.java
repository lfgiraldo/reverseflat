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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "registermailpage")
@NamedQueries({@NamedQuery(name = "Registermailpage.findAll", query = "SELECT r FROM Registermailpage r"), @NamedQuery(name = "Registermailpage.findByIdPage", query = "SELECT r FROM Registermailpage r WHERE r.idPage = :idPage")})
public class Registermailpage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPage")
    private Short idPage;
    @Basic(optional = false)
    @Lob
    @Column(name = "htmlPage")
    private String htmlPage;

    public Registermailpage() {
    }

    public Registermailpage(Short idPage) {
        this.idPage = idPage;
    }

    public Registermailpage(Short idPage, String htmlPage) {
        this.idPage = idPage;
        this.htmlPage = htmlPage;
    }

    public Short getIdPage() {
        return idPage;
    }

    public void setIdPage(Short idPage) {
        this.idPage = idPage;
    }

    public String getHtmlPage() {
        return htmlPage;
    }

    public void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPage != null ? idPage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registermailpage)) {
            return false;
        }
        Registermailpage other = (Registermailpage) object;
        if ((this.idPage == null && other.idPage != null) || (this.idPage != null && !this.idPage.equals(other.idPage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Registermailpage[idPage=" + idPage + "]";
    }

}
