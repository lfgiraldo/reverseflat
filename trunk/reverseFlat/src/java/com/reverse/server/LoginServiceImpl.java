/*
 * LoginServiceImpl.java
 *
 * Created on November 10, 2008, 4:56 PM
*/

package com.reverse.server;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/*
import com.snoofing.ejb.session.UserFacadeLocal;
import com.snoofing.exception.ServerException;
import com.snoofing.gwt.client.common.exceptions.LoginException;
import com.snoofing.gwt.client.services.LoginService;
import com.snoofing.gwt.client.common.valueobjects.UserVO;
import com.snoofing.gwt.server.commons.Commons;
import com.snoofing.persistence.User;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
*/

/**
 *
 * @author Pipe
 */
public class LoginServiceImpl { // extends RemoteServiceServlet implements
    /*
    LoginService {
    
    @EJB
    private UserFacadeLocal userFacade;
    private Locale locale;
    private ResourceBundle  messages;

    public UserVO login(String nickname, String password) throws LoginException {
        try {
            String userLocale = getThreadLocalRequest().getParameter("locale");
            getThreadLocalRequest().getSession().setAttribute("locale", userLocale);
            setLocale();

            Logger.getLogger(LoginServiceImpl.class.getName()).log(Level.INFO, "User: "+nickname);

            User user = new User();
            user = (User) userFacade.login(nickname, password);
            UserVO loggedUser = Commons.UsertoUserVO(user);
            loggedUser.setRole(getRole());
            getThreadLocalRequest().getSession().setAttribute("user", loggedUser);
            return loggedUser;
        } catch (com.snoofing.exception.LoginException ex) {
            throw new LoginException(messages.getString("LoginException"));
        }
    }
    
    public void logout(){
        getThreadLocalRequest().getSession().invalidate();
    }

    public String getRole() {
        if (getThreadLocalRequest().isUserInRole("admin"))
            return "admin";
        else if (getThreadLocalRequest().isUserInRole("user"))
            return "user";
        else return "";
    }

    private void setLocale(){
        String userLocale = (String) getThreadLocalRequest().getSession().getAttribute("locale");
        if (userLocale != null){
            String country = "";
            if ("es".equals(userLocale)){
                country = "ES";
            }else if ("en".equals(userLocale)){
                country = "US";
            }
            locale = new Locale(userLocale, country);
            this.messages = ResourceBundle.getBundle("com.snoofing.gwt.server.i18n.LoginServiceImplMessages", locale);
        }else{
            getThreadLocalRequest().getSession().setAttribute("locale", "es");
            this.messages = ResourceBundle.getBundle("com.snoofing.gwt.server.i18n.LoginServiceImplMessages");
        }
    }
     */
}
