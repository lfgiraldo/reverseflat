/*
 * ProposedItemImpl.java
 *
 * Created on December 9, 2008, 11:00 AM
 *
*/

package com.reverse.server;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/*
import com.snoofing.ejb.session.ProposeditemFacadeLocal;
import com.snoofing.gwt.client.services.ProposedItemService;
import com.snoofing.gwt.client.common.valueobjects.ProposeditemVO;
import com.snoofing.gwt.server.commons.Commons;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;

import com.snoofing.persistence.Proposeditem;
*/

/**
 * Remote Service GWT para crear artículos propuestos por los usuarios. 
 * @author Luis Felipe Giraldo
 */
public class ProposedItemImpl { // extends RemoteServiceServlet implements ProposedItemService {

    /*
    @EJB
    private ProposeditemFacadeLocal proposeditemFacade;

    public ProposeditemVO[] getProposedItems(){
        List proposedItems = proposeditemFacade.findAll();
        if ( proposedItems.size() == 0  ){
            return null;
        }
        ProposeditemVO[] itemList = new ProposeditemVO[proposedItems.size()];
        Iterator iterator = proposedItems.iterator();
        for(int i=0; iterator.hasNext(); i++){
            itemList[i] = convertToProposedItemVO( (Proposeditem) iterator.next());
        }
        return itemList;
    }

    private ProposeditemVO convertToProposedItemVO(Proposeditem item) {
        ProposeditemVO valueObject = new ProposeditemVO();
        valueObject.setIdItem(item.getIdItem());
        valueObject.setUserNickname(item.getUserNickname());
        valueObject.setDescription(item.getDescription());
        
        String fullPath = super.getServletContext().getRealPath("/") + "uploadedItems/"+item.getFileName();
        Commons.convertBytesToFile(item.getPicture(), fullPath);
        String relativePath =  getThreadLocalRequest().getContextPath() + "/uploadedItems/" + item.getFileName();
        
        valueObject.setPicture(relativePath);
        valueObject.setTitle(item.getTitle());
        return valueObject;
    }
     */
}
