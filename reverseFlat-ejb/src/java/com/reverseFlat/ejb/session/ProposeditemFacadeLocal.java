/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.session;

import com.reverseFlat.ejb.persistence.Proposeditem;
import com.reverseFlat.ejb.persistence.Proposeditemvote;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pipe
 */
@Local
public interface ProposeditemFacadeLocal {

    void create(Proposeditem proposeditem);

    void edit(Proposeditem proposeditem);

    void remove(Proposeditem proposeditem);

    Proposeditem find(Object id);

    List<Proposeditem> findAll();

    void voteProposedItem(Proposeditemvote proposedItemVote);

    void activateProposedItem(Integer idItem);
}
