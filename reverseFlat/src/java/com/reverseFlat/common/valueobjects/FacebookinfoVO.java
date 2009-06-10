/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.common.valueobjects;

//import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Luis Felipe Giraldo
 */
public class FacebookinfoVO implements Serializable {
   
    private Integer useridUser;
    private String facebookId;
    private String currentLocation;
    private String firstName;
    private String educationHistory;
    private Boolean hasAddedApp;
    private String hometownLocation;
    private String affiliations;
    private String books;
    private Date birthday;
    private String activities;
    private String aboutMe;
    private String picSmall;
    private String tv;
    private String workHistory;
    private String wallCount;
    private String meetingFor;
    private String quotes;
    private String picBig;
    private String notesCount;
    private String music;
    private String lastName;
    private String relationshipsStatus;
    private String pic;
    private String status;
    private Date profileUpdateTime;
    private String interests;
    private String timezone;
    private String picSquare;
    private String political;
    private Boolean isAppUser;
    private String movies;
    private String name;
    private String meetingSex;
    private String religion;
    private String sex;
    private String significantOtherId;
    private String hsInfo;
    private UserVO user;

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(String affiliations) {
        this.affiliations = affiliations;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getEducationHistory() {
        return educationHistory;
    }

    public void setEducationHistory(String educationHistory) {
        this.educationHistory = educationHistory;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getHsInfo() {
        return hsInfo;
    }

    public void setHsInfo(String hsInfo) {
        this.hsInfo = hsInfo;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Boolean getIsAppUser() {
        return isAppUser;
    }

    public void setIsAppUser(Boolean isAppUser) {
        this.isAppUser = isAppUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMeetingFor() {
        return meetingFor;
    }

    public void setMeetingFor(String meetingFor) {
        this.meetingFor = meetingFor;
    }

    public String getMeetingSex() {
        return meetingSex;
    }

    public void setMeetingSex(String meetingSex) {
        this.meetingSex = meetingSex;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(String notesCount) {
        this.notesCount = notesCount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
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

    public Date getProfileUpdateTime() {
        return profileUpdateTime;
    }

    public void setProfileUpdateTime(Date profileUpdateTime) {
        this.profileUpdateTime = profileUpdateTime;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getRelationshipsStatus() {
        return relationshipsStatus;
    }

    public void setRelationshipsStatus(String relationshipsStatus) {
        this.relationshipsStatus = relationshipsStatus;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public Integer getUseridUser() {
        return useridUser;
    }

    public void setUseridUser(Integer useridUser) {
        this.useridUser = useridUser;
    }

    public String getWallCount() {
        return wallCount;
    }

    public void setWallCount(String wallCount) {
        this.wallCount = wallCount;
    }

    public String getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }



}
