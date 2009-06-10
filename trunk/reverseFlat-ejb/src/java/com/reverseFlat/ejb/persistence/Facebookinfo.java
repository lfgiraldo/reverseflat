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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pipe
 */
@Entity
@Table(name = "facebookinfo")
@NamedQueries({@NamedQuery(name = "Facebookinfo.findAll", query = "SELECT f FROM Facebookinfo f"), @NamedQuery(name = "Facebookinfo.findByUseridUser", query = "SELECT f FROM Facebookinfo f WHERE f.useridUser = :useridUser"), @NamedQuery(name = "Facebookinfo.findByFacebookId", query = "SELECT f FROM Facebookinfo f WHERE f.facebookId = :facebookId"), @NamedQuery(name = "Facebookinfo.findByCurrentLocation", query = "SELECT f FROM Facebookinfo f WHERE f.currentLocation = :currentLocation"), @NamedQuery(name = "Facebookinfo.findByFirstName", query = "SELECT f FROM Facebookinfo f WHERE f.firstName = :firstName"), @NamedQuery(name = "Facebookinfo.findByHasAddedApp", query = "SELECT f FROM Facebookinfo f WHERE f.hasAddedApp = :hasAddedApp"), @NamedQuery(name = "Facebookinfo.findByHometownLocation", query = "SELECT f FROM Facebookinfo f WHERE f.hometownLocation = :hometownLocation"), @NamedQuery(name = "Facebookinfo.findByBirthday", query = "SELECT f FROM Facebookinfo f WHERE f.birthday = :birthday"), @NamedQuery(name = "Facebookinfo.findByPicSmall", query = "SELECT f FROM Facebookinfo f WHERE f.picSmall = :picSmall"), @NamedQuery(name = "Facebookinfo.findByWallCount", query = "SELECT f FROM Facebookinfo f WHERE f.wallCount = :wallCount"), @NamedQuery(name = "Facebookinfo.findByMeetingFor", query = "SELECT f FROM Facebookinfo f WHERE f.meetingFor = :meetingFor"), @NamedQuery(name = "Facebookinfo.findByPicBig", query = "SELECT f FROM Facebookinfo f WHERE f.picBig = :picBig"), @NamedQuery(name = "Facebookinfo.findByLastName", query = "SELECT f FROM Facebookinfo f WHERE f.lastName = :lastName"), @NamedQuery(name = "Facebookinfo.findByRelationshipsStatus", query = "SELECT f FROM Facebookinfo f WHERE f.relationshipsStatus = :relationshipsStatus"), @NamedQuery(name = "Facebookinfo.findByPic", query = "SELECT f FROM Facebookinfo f WHERE f.pic = :pic"), @NamedQuery(name = "Facebookinfo.findByStatus", query = "SELECT f FROM Facebookinfo f WHERE f.status = :status"), @NamedQuery(name = "Facebookinfo.findByProfileUpdateTime", query = "SELECT f FROM Facebookinfo f WHERE f.profileUpdateTime = :profileUpdateTime"), @NamedQuery(name = "Facebookinfo.findByTimezone", query = "SELECT f FROM Facebookinfo f WHERE f.timezone = :timezone"), @NamedQuery(name = "Facebookinfo.findByPicSquare", query = "SELECT f FROM Facebookinfo f WHERE f.picSquare = :picSquare"), @NamedQuery(name = "Facebookinfo.findByIsAppUser", query = "SELECT f FROM Facebookinfo f WHERE f.isAppUser = :isAppUser"), @NamedQuery(name = "Facebookinfo.findByName", query = "SELECT f FROM Facebookinfo f WHERE f.name = :name"), @NamedQuery(name = "Facebookinfo.findByMeetingSex", query = "SELECT f FROM Facebookinfo f WHERE f.meetingSex = :meetingSex"), @NamedQuery(name = "Facebookinfo.findByReligion", query = "SELECT f FROM Facebookinfo f WHERE f.religion = :religion"), @NamedQuery(name = "Facebookinfo.findBySex", query = "SELECT f FROM Facebookinfo f WHERE f.sex = :sex")})
public class Facebookinfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_idUser")
    private Integer useridUser;
    @Column(name = "facebookId")
    private String facebookId;
    @Column(name = "current_location")
    private String currentLocation;
    @Column(name = "first_name")
    private String firstName;
    @Lob
    @Column(name = "education_history")
    private String educationHistory;
    @Column(name = "has_added_app")
    private Boolean hasAddedApp;
    @Column(name = "hometown_location")
    private String hometownLocation;
    @Lob
    @Column(name = "affiliations")
    private String affiliations;
    @Lob
    @Column(name = "books")
    private String books;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Lob
    @Column(name = "activities")
    private String activities;
    @Lob
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "pic_small")
    private String picSmall;
    @Lob
    @Column(name = "tv")
    private String tv;
    @Lob
    @Column(name = "work_history")
    private String workHistory;
    @Column(name = "wall_count")
    private String wallCount;
    @Column(name = "meeting_for")
    private String meetingFor;
    @Lob
    @Column(name = "quotes")
    private String quotes;
    @Column(name = "pic_big")
    private String picBig;
    @Lob
    @Column(name = "notes_count")
    private String notesCount;
    @Lob
    @Column(name = "music")
    private String music;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "relationships_status")
    private String relationshipsStatus;
    @Column(name = "pic")
    private String pic;
    @Column(name = "status")
    private String status;
    @Column(name = "profile_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date profileUpdateTime;
    @Lob
    @Column(name = "interests")
    private String interests;
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "pic_square")
    private String picSquare;
    @Lob
    @Column(name = "political")
    private String political;
    @Column(name = "is_app_user")
    private Boolean isAppUser;
    @Lob
    @Column(name = "movies")
    private String movies;
    @Column(name = "name")
    private String name;
    @Column(name = "meeting_sex")
    private String meetingSex;
    @Column(name = "religion")
    private String religion;
    @Column(name = "sex")
    private String sex;
    @Lob
    @Column(name = "significant_other_id")
    private String significantOtherId;
    @Lob
    @Column(name = "hs_info")
    private String hsInfo;
    @JoinColumn(name = "user_idUser", referencedColumnName = "idUser", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Facebookinfo() {
    }

    public Facebookinfo(Integer useridUser) {
        this.useridUser = useridUser;
    }

    public Integer getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(Integer useridUser) {
        this.useridUser = useridUser;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEducationHistory() {
        return educationHistory;
    }

    public void setEducationHistory(String educationHistory) {
        this.educationHistory = educationHistory;
    }

    public Boolean getHasAddedApp() {
        return hasAddedApp;
    }

    public void setHasAddedApp(Boolean hasAddedApp) {
        this.hasAddedApp = hasAddedApp;
    }

    public String getHometownLocation() {
        return hometownLocation;
    }

    public void setHometownLocation(String hometownLocation) {
        this.hometownLocation = hometownLocation;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }

    public String getWallCount() {
        return wallCount;
    }

    public void setWallCount(String wallCount) {
        this.wallCount = wallCount;
    }

    public String getMeetingFor() {
        return meetingFor;
    }

    public void setMeetingFor(String meetingFor) {
        this.meetingFor = meetingFor;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(String notesCount) {
        this.notesCount = notesCount;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationshipsStatus() {
        return relationshipsStatus;
    }

    public void setRelationshipsStatus(String relationshipsStatus) {
        this.relationshipsStatus = relationshipsStatus;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getProfileUpdateTime() {
        return profileUpdateTime;
    }

    public void setProfileUpdateTime(Date profileUpdateTime) {
        this.profileUpdateTime = profileUpdateTime;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getPicSquare() {
        return picSquare;
    }

    public void setPicSquare(String picSquare) {
        this.picSquare = picSquare;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public Boolean getIsAppUser() {
        return isAppUser;
    }

    public void setIsAppUser(Boolean isAppUser) {
        this.isAppUser = isAppUser;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeetingSex() {
        return meetingSex;
    }

    public void setMeetingSex(String meetingSex) {
        this.meetingSex = meetingSex;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignificantOtherId() {
        return significantOtherId;
    }

    public void setSignificantOtherId(String significantOtherId) {
        this.significantOtherId = significantOtherId;
    }

    public String getHsInfo() {
        return hsInfo;
    }

    public void setHsInfo(String hsInfo) {
        this.hsInfo = hsInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (useridUser != null ? useridUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facebookinfo)) {
            return false;
        }
        Facebookinfo other = (Facebookinfo) object;
        if ((this.useridUser == null && other.useridUser != null) || (this.useridUser != null && !this.useridUser.equals(other.useridUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.snoofing.persistence.Facebookinfo[useridUser=" + useridUser + "]";
    }

}
