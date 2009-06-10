/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.persistence;

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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luis Felipe Giraldo
 */
@Entity
@Table(name = "user")
@NamedQueries({@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"), @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"), @NamedQuery(name = "User.findByNickname", query = "SELECT u FROM User u WHERE u.nickname = :nickname"), @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"), @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"), @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"), @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"), @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"), @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"), @NamedQuery(name = "User.findByIdCity", query = "SELECT u FROM User u WHERE u.idCity = :idCity"), @NamedQuery(name = "User.findByIdCountry", query = "SELECT u FROM User u WHERE u.idCountry = :idCountry"), @NamedQuery(name = "User.findByPostalCode", query = "SELECT u FROM User u WHERE u.postalCode = :postalCode"), @NamedQuery(name = "User.findByBirthday", query = "SELECT u FROM User u WHERE u.birthday = :birthday"), @NamedQuery(name = "User.findByLandPhone", query = "SELECT u FROM User u WHERE u.landPhone = :landPhone"), @NamedQuery(name = "User.findByCellPhone", query = "SELECT u FROM User u WHERE u.cellPhone = :cellPhone"), @NamedQuery(name = "User.findByRegisterDate", query = "SELECT u FROM User u WHERE u.registerDate = :registerDate"), @NamedQuery(name = "User.findByActive", query = "SELECT u FROM User u WHERE u.active = :active"), @NamedQuery(name = "User.findByChipsBalance", query = "SELECT u FROM User u WHERE u.chipsBalance = :chipsBalance"), @NamedQuery(name = "User.findByFreeChipsBalance", query = "SELECT u FROM User u WHERE u.freeChipsBalance = :freeChipsBalance"), @NamedQuery(name = "User.findByActivationKey", query = "SELECT u FROM User u WHERE u.activationKey = :activationKey")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "gender")
    private Short gender;
    @Column(name = "address")
    private String address;
    @Column(name = "idCity")
    private Integer idCity;
    @Column(name = "idCountry")
    private Integer idCountry;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "landPhone")
    private String landPhone;
    @Column(name = "cellPhone")
    private String cellPhone;
    @Basic(optional = false)
    @Column(name = "registerDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "chipsBalance")
    private int chipsBalance;
    @Basic(optional = false)
    @Column(name = "freeChipsBalance")
    private int freeChipsBalance;
    @Basic(optional = false)
    @Column(name = "activationKey")
    private String activationKey;
    @ManyToMany(mappedBy = "userCollection")
    private List<Auction> auctionCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Facebookinfo facebookinfo;
    @OneToMany(mappedBy = "useridUser")
    private List<Pricebyauction> pricebyauctionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useridUser")
    private List<Chipsexpenditure> chipsexpenditureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useridUser")
    private List<Chipsincome> chipsincomeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useridUser")
    private List<Freechipsincome> freechipsincomeCollection;
    @OneToMany(mappedBy = "useridUserreferral")
    private List<Freechipsincome> freechipsincomeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "useridUser")
    private List<Bid> bidCollection;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String nickname, String password, Date registerDate, boolean active, int chipsBalance, int freeChipsBalance, String activationKey) {
        this.idUser = idUser;
        this.nickname = nickname;
        this.password = password;
        this.registerDate = registerDate;
        this.active = active;
        this.chipsBalance = chipsBalance;
        this.freeChipsBalance = freeChipsBalance;
        this.activationKey = activationKey;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLandPhone() {
        return landPhone;
    }

    public void setLandPhone(String landPhone) {
        this.landPhone = landPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getChipsBalance() {
        return chipsBalance;
    }

    public void setChipsBalance(int chipsBalance) {
        this.chipsBalance = chipsBalance;
    }

    public int getFreeChipsBalance() {
        return freeChipsBalance;
    }

    public void setFreeChipsBalance(int freeChipsBalance) {
        this.freeChipsBalance = freeChipsBalance;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public List<Auction> getAuctionCollection() {
        return auctionCollection;
    }

    public void setAuctionCollection(List<Auction> auctionCollection) {
        this.auctionCollection = auctionCollection;
    }

    public Facebookinfo getFacebookinfo() {
        return facebookinfo;
    }

    public void setFacebookinfo(Facebookinfo facebookinfo) {
        this.facebookinfo = facebookinfo;
    }

    public List<Pricebyauction> getPricebyauctionCollection() {
        return pricebyauctionCollection;
    }

    public void setPricebyauctionCollection(List<Pricebyauction> pricebyauctionCollection) {
        this.pricebyauctionCollection = pricebyauctionCollection;
    }

    public List<Chipsexpenditure> getChipsexpenditureCollection() {
        return chipsexpenditureCollection;
    }

    public void setChipsexpenditureCollection(List<Chipsexpenditure> chipsexpenditureCollection) {
        this.chipsexpenditureCollection = chipsexpenditureCollection;
    }

    public List<Chipsincome> getChipsincomeCollection() {
        return chipsincomeCollection;
    }

    public void setChipsincomeCollection(List<Chipsincome> chipsincomeCollection) {
        this.chipsincomeCollection = chipsincomeCollection;
    }

    public List<Freechipsincome> getFreechipsincomeCollection() {
        return freechipsincomeCollection;
    }

    public void setFreechipsincomeCollection(List<Freechipsincome> freechipsincomeCollection) {
        this.freechipsincomeCollection = freechipsincomeCollection;
    }

    public List<Freechipsincome> getFreechipsincomeCollection1() {
        return freechipsincomeCollection1;
    }

    public void setFreechipsincomeCollection1(List<Freechipsincome> freechipsincomeCollection1) {
        this.freechipsincomeCollection1 = freechipsincomeCollection1;
    }

    public List<Bid> getBidCollection() {
        return bidCollection;
    }

    public void setBidCollection(List<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.User[idUser=" + idUser + "]";
    }

}
