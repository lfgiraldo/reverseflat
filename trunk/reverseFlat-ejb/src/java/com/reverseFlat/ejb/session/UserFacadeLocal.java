/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.session;

import com.reverseFlat.ejb.commons.valueobjects.CreditCardServer;
import com.reverseFlat.ejb.exception.ActivationMailException;
import com.reverseFlat.ejb.exception.LoginException;
import com.reverseFlat.ejb.exception.NonExistentUserException;
import com.reverseFlat.ejb.exception.PayPalServiceException;
import com.reverseFlat.ejb.exception.ServerException;
import com.reverseFlat.ejb.exception.WrongActivationNumberException;
import com.reverseFlat.ejb.persistence.Chipsincome;
import com.reverseFlat.ejb.persistence.Combochips;
import com.reverseFlat.ejb.persistence.Facebookinfo;
import com.reverseFlat.ejb.persistence.Freechipsincome;
import com.reverseFlat.ejb.persistence.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Pipe
 */
@Local
public interface UserFacadeLocal {

    void create(User user);

    void createAdmin(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    User findByUserNickname(String nickname) throws NonExistentUserException;

    List<User> findAll();
    
    Boolean existNickname(String nick);
    
    User login (String user, String password) throws LoginException;

    void activateUser(String nickname, String activationKey) throws NonExistentUserException, WrongActivationNumberException;

    void deactivateUser(Integer idUser);

    public Combochips getComboChips(Integer idCombo);

    void addFreeChips(Freechipsincome freeChipsIncome);

    User getUserBalanceDetailed(User user);

    String addPaypalChips(User user, CreditCardServer data) throws PayPalServiceException;

    String doPaypalLogin(Chipsincome income, CreditCardServer data, String returnUrl, String cancelUrl) throws PayPalServiceException;

    String getPaypalDetails(String token, String paymentAmount, String currencyCode, String locale) throws PayPalServiceException;

    String doPaypalPayment(User user, Chipsincome income, CreditCardServer data, String token, String payerId, String locale ) throws PayPalServiceException;

    void sendRegisterMail(User user) throws ActivationMailException;

    void sendMail(String email, String subject, String body) throws ActivationMailException;

    Facebookinfo findByFacebookId(String facebookId) throws NonExistentUserException;

    String createUserWithFacebookAccount(Facebookinfo facebookUser) throws ServerException;

    String updatePassword4FacebookLogin (String facebookId);
}
