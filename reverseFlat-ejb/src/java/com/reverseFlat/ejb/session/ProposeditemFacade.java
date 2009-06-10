/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.session;

import com.reverseFlat.ejb.persistence.Proposeditem;
import com.reverseFlat.ejb.persistence.Proposeditemvote;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pipe
 */
@Stateless(mappedName="ProposeditemFacade")
public class ProposeditemFacade implements ProposeditemFacadeLocal, ProposeditemFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(Proposeditem proposeditem) {
        em.persist(proposeditem);
    }

    public void edit(Proposeditem proposeditem) {
        em.merge(proposeditem);
    }

    public void remove(Proposeditem proposeditem) {
        em.remove(em.merge(proposeditem));
    }

    public Proposeditem find(Object id) {
        return em.find(com.reverseFlat.ejb.persistence.Proposeditem.class, id);
    }

    public List<Proposeditem> findAll() {
        return em.createQuery("select object(o) FROM Proposeditem o").getResultList();
    }

    public void voteProposedItem(Proposeditemvote proposedItemVote) {
        em.persist(proposedItemVote);
    }

    public void activateProposedItem(Integer idItem) {
        Query q = em.createQuery("UPDATE Proposeditem SET active = 1 WHERE idItem = :idItem");
        q.setParameter("idUser", idItem);
        q.executeUpdate();
    }


}
