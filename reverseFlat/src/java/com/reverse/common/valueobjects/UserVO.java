/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.common.valueobjects;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lfgiraldo
 */
public class UserVO implements Serializable {
    private Integer idUser;
    private String nickname;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private Short gender;
    private String address;
    private Integer idCity;
    private Integer idCountry;
    private String postalCode;
    private Date birthday;
    private String landPhone;
    private String cellPhone;
    private Date registerDate;
    private boolean active;
    private String role;
    private String captcha;
    private String captchaSalt;

    public UserVO() {
    }

    public UserVO(Integer idUser) {
        this.idUser = idUser;
    }

    public UserVO(Integer idUser, String nickname, String password, String email, Date registerDate, boolean active) {
        this.idUser = idUser;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
        this.active = active;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaSalt() {
        return captchaSalt;
    }

    public void setCaptchaSalt(String captchaSalt) {
        this.captchaSalt = captchaSalt;
    }

    
    
}
