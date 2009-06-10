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
 * @author lfgiraldo
 */
public class ProposedItemVotesVO implements Serializable {
    private Integer idVote;
    private String userNickname;
    private String userComment;
    private Date voteDate;
    private ProposeditemVO itemidItem;

    public ProposedItemVotesVO() {
    }

    public ProposedItemVotesVO(Integer idVote) {
        this.idVote = idVote;
    }

    public ProposedItemVotesVO(Integer idVote, String userNickname, Date voteDate) {
        this.idVote = idVote;
        this.userNickname = userNickname;
        this.voteDate = voteDate;
    }

    public Integer getIdVote() {
        return idVote;
    }

    public void setIdVote(Integer idVote) {
        this.idVote = idVote;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Date getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }

    public ProposeditemVO getItemidItem() {
        return itemidItem;
    }

    public void setItemidItem(ProposeditemVO itemidItem) {
        this.itemidItem = itemidItem;
    }

}
