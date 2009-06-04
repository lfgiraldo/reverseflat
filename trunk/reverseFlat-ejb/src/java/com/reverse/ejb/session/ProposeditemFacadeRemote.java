/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.session;

import com.reverse.ejb.persistence.Proposeditem;
import com.reverse.ejb.persistence.Proposeditemvote;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Pipe
 */
@Remote
public interface ProposeditemFacadeRemote {

    void create(Proposeditem proposeditem);

    void edit(Proposeditem proposeditem);

    void remove(Proposeditem proposeditem);

    Proposeditem find(Object id);

    List<Proposeditem> findAll();

    void voteProposedItem(Proposeditemvote proposedItemVote);

    void activateProposedItem(Integer idItem);
}
