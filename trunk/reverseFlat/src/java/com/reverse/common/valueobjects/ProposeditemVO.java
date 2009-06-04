/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.common.valueobjects;

//import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author lfgiraldo
 */
public class ProposeditemVO implements Serializable {
    private Integer idItem;
    private String description;
    private String picture;
    private String userNickname;
    private String title;
    private Date endDate;
    private Date publishDate;
    private String fileName;
    private Collection<ProposedItemVotesVO> proposedItemVotesCollection;

    public ProposeditemVO() {
    }

    public ProposeditemVO(Integer idItem) {
        this.idItem = idItem;
    }

    public ProposeditemVO(Integer idItem, String description, String picture, String userNickname, String title, Date publishDate, String fileName) {
        this.idItem = idItem;
        this.description = description;
        this.picture = picture;
        this.userNickname = userNickname;
        this.title = title;
        this.publishDate = publishDate;
        this.fileName = fileName;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Collection<ProposedItemVotesVO> getProposedItemVotesCollection() {
        return proposedItemVotesCollection;
    }

    public void setProposedItemVotesCollection(Collection<ProposedItemVotesVO> proposedItemVotesCollection) {
        this.proposedItemVotesCollection = proposedItemVotesCollection;
    }


}
