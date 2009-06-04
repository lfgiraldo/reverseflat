/*
 * GWTServiceImpl.java
 *
 * Created on November 3, 2008, 1:38 PM
 *
*/

package com.reverse.server;
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/*
import com.octo.captcha.service.CaptchaServiceException;
import com.snoofing.gwt.client.common.valueobjects.UserVO;
import com.snoofing.ejb.session.UserFacadeLocal;
import com.snoofing.exception.ActivationMailException;
import com.snoofing.gwt.client.common.exceptions.InvalidCaptchaException;
import com.snoofing.gwt.client.common.exceptions.MailException;
import com.snoofing.gwt.client.services.RegisterService;
import com.snoofing.gwt.server.commons.CaptchaServiceSingleton;
import com.snoofing.gwt.server.commons.Commons;
import com.snoofing.persistence.User;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
*/

/**
 * Remote Service GWT encargado del registro de usuarios. 
 * @author Luis Felipe Giraldo
 */
public class RegisterServiceImpl{ // extends RemoteServiceServlet implements RegisterService {

    /*
    @EJB
    private UserFacadeLocal userFacade;
    private Locale locale;
    private ResourceBundle  messages;

    public Boolean setUser(UserVO userVO) throws MailException, InvalidCaptchaException {

        setLocale();

        Boolean isResponseCorrect =Boolean.FALSE;
        //remenber that we need an id to validate!
        String captchaId = getThreadLocalRequest().getSession().getId()+userVO.getCaptchaSalt();
        //retrieve the response
        String response = userVO.getCaptcha();
        // Call the Service method

        try {
            isResponseCorrect = CaptchaServiceSingleton.getInstance().validateResponseForID(captchaId,
                    response);
        } catch (CaptchaServiceException e) {
             //should not happen, may be thrown if the id is not valid
        }

        if (!isResponseCorrect){
            throw new InvalidCaptchaException(messages.getString("InvalidCaptchaException"));
        }
        Boolean nicknameExist = userFacade.existNickname(userVO.getNickname());
        
        //If nickname exists, do not save
        if ( nicknameExist ){
            return new Boolean(true);
        }else{
            User userPersistence = Commons.UserVOtoUser(userVO);
            try {
                userFacade.create(userPersistence);
                userFacade.sendRegisterMail(userPersistence);
            } catch (ActivationMailException ex) {
                userFacade.remove(userPersistence);
                Logger.getLogger(RegisterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new MailException(messages.getString("MailException"));
            }
            return new Boolean(false);
        }
    }

    public void completeUserData (UserVO userVO) {
        User user = new User();
        user = Commons.UserVOtoUser(userVO);
        userFacade.edit(user);
    }

    private void setLocale(){
        String userLocale = (String) getThreadLocalRequest().getSession().getAttribute("locale");
        if (userLocale != null){
            locale = new Locale(userLocale);
            this.messages = ResourceBundle.getBundle("com.snoofing.gwt.server.i18n.RegisterServiceImplMessages", locale);
        }else{
            this.messages = ResourceBundle.getBundle("com.snoofing.gwt.server.i18n.RegisterServiceImplMessages");
        }
    }
     */
}
