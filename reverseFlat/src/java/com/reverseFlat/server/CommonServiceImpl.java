/*
 * CommonServiceImpl.java
 *
 * Created on 5 de octubre de 2008, 06:23 PM
 *
 */

package com.reverseFlat.server;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//import com.snoofing.gwt.client.services.CommonService;
//import java.security.Principal;


/**
 * Remote Service en GWT encargado de sacar el rol del usuario autenticado.
 * @author Luis Felipe Giraldo
 */
public class CommonServiceImpl { // extends RemoteServiceServlet implements CommonService {

    /*
    public String getUserGroup() {
        Principal p = getThreadLocalRequest().getUserPrincipal();
        if (p != null){
            if(getThreadLocalRequest().isUserInRole("user")){
                return getThreadLocalRequest().getUserPrincipal().getName();   
            }
            else
                if(getThreadLocalRequest().isUserInRole("admin")){
                    return getThreadLocalRequest().getUserPrincipal().getName();
                }
                else{
                    return p.getName();
                }
        }
        else
            return "";
    }
     */
}
