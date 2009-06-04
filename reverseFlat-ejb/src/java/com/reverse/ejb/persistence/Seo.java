/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.persistence;

import java.io.Serializable;
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
@Table(name = "seo")
@NamedQueries({@NamedQuery(name = "Seo.findAll", query = "SELECT s FROM Seo s"), @NamedQuery(name = "Seo.findByLanguageCode", query = "SELECT s FROM Seo s WHERE s.languageCode = :languageCode"), @NamedQuery(name = "Seo.findByRevisit", query = "SELECT s FROM Seo s WHERE s.revisit = :revisit"), @NamedQuery(name = "Seo.findByKeywords", query = "SELECT s FROM Seo s WHERE s.keywords = :keywords"), @NamedQuery(name = "Seo.findByVerify", query = "SELECT s FROM Seo s WHERE s.verify = :verify"), @NamedQuery(name = "Seo.findByGenerator", query = "SELECT s FROM Seo s WHERE s.generator = :generator"), @NamedQuery(name = "Seo.findByAudience", query = "SELECT s FROM Seo s WHERE s.audience = :audience"), @NamedQuery(name = "Seo.findByAuthor", query = "SELECT s FROM Seo s WHERE s.author = :author"), @NamedQuery(name = "Seo.findBySubject", query = "SELECT s FROM Seo s WHERE s.subject = :subject"), @NamedQuery(name = "Seo.findByDescription", query = "SELECT s FROM Seo s WHERE s.description = :description"), @NamedQuery(name = "Seo.findByLanguage", query = "SELECT s FROM Seo s WHERE s.language = :language"), @NamedQuery(name = "Seo.findByTitle", query = "SELECT s FROM Seo s WHERE s.title = :title"), @NamedQuery(name = "Seo.findByMetatitle", query = "SELECT s FROM Seo s WHERE s.metatitle = :metatitle")})
public class Seo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "languageCode")
    private String languageCode;
    @Basic(optional = false)
    @Column(name = "revisit")
    private String revisit;
    @Basic(optional = false)
    @Column(name = "keywords")
    private String keywords;
    @Basic(optional = false)
    @Column(name = "verify")
    private String verify;
    @Basic(optional = false)
    @Column(name = "generator")
    private String generator;
    @Basic(optional = false)
    @Column(name = "audience")
    private String audience;
    @Basic(optional = false)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "language")
    private String language;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "metatitle")
    private String metatitle;

    public Seo() {
    }

    public Seo(String languageCode) {
        this.languageCode = languageCode;
    }

    public Seo(String languageCode, String revisit, String keywords, String verify, String generator, String audience, String author, String subject, String description, String language, String title, String metatitle) {
        this.languageCode = languageCode;
        this.revisit = revisit;
        this.keywords = keywords;
        this.verify = verify;
        this.generator = generator;
        this.audience = audience;
        this.author = author;
        this.subject = subject;
        this.description = description;
        this.language = language;
        this.title = title;
        this.metatitle = metatitle;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getRevisit() {
        return revisit;
    }

    public void setRevisit(String revisit) {
        this.revisit = revisit;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetatitle() {
        return metatitle;
    }

    public void setMetatitle(String metatitle) {
        this.metatitle = metatitle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageCode != null ? languageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seo)) {
            return false;
        }
        Seo other = (Seo) object;
        if ((this.languageCode == null && other.languageCode != null) || (this.languageCode != null && !this.languageCode.equals(other.languageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Seo[languageCode=" + languageCode + "]";
    }

}
