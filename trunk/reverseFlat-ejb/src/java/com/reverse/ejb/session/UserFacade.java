/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverse.ejb.session;

import com.reverse.ejb.commons.valueobjects.CreditCardServer;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.services.NVPCallerServices;
import com.reverse.ejb.exception.ActivationMailException;
import com.reverse.ejb.exception.PayPalServiceException;
import com.reverse.ejb.exception.ServerException;
import com.reverse.ejb.commons.utils.PayPalUtil;
import com.reverse.ejb.commons.utils.SnoofMail;
import com.reverse.ejb.exception.LoginException;
import com.reverse.ejb.exception.NonExistentUserException;
import com.reverse.ejb.exception.WrongActivationNumberException;
import com.reverse.ejb.persistence.Chipsincome;
import com.reverse.ejb.persistence.Combochips;
import com.reverse.ejb.persistence.Facebookinfo;
import com.reverse.ejb.persistence.Freechipsincome;
import com.reverse.ejb.persistence.Grouptable;
import com.reverse.ejb.persistence.Registermailpage;
import com.reverse.ejb.persistence.User;
import java.math.BigDecimal;
import java.nio.CharBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pipe
 */
@Stateless(mappedName="UserFacade")
public class UserFacade implements UserFacadeLocal, UserFacadeRemote {
    @PersistenceContext
    private EntityManager em;

    public void create(User user) {
        String passwordHash = crypt(user.getPassword());
        user.setPassword(passwordHash);
        
        String activationKey = ""+GregorianCalendar.getInstance().getTimeInMillis();
        user.setActivationKey(activationKey);

        Grouptable groupTable = new Grouptable(user.getNickname(), "user");
        em.persist(groupTable);
        em.persist(user);
    }

    public void createAdmin(User user) {
        String passwordHash = crypt(user.getPassword());
        user.setPassword(passwordHash);
        Grouptable groupTable = new Grouptable(user.getNickname(), "admin");
        em.persist(groupTable);
        em.persist(user);
    }

    public void edit(User user) {
        em.merge(user);
    }

    public void remove(User user) {
        Grouptable groupTable = new Grouptable(user.getNickname(), "user");
        em.remove(em.merge(groupTable));
        em.remove(em.merge(user));
    }

    public User find(Object id) {
        return em.find(com.reverse.ejb.persistence.User.class, id);
    }

    public User findByUserNickname(String nickname) throws NonExistentUserException {
        Query q = em.createNamedQuery("User.findByNickname").setHint("toplink.refresh", "true");
        User user = new User();
        try{
            q.setParameter("nickname", nickname);
            user = (User) q.getSingleResult();
        }catch(NoResultException e){
            System.out.println("User "+nickname+" does not exist");
            throw new NonExistentUserException("User does not exist");
        }
        return user;
    }

    public List<User> findAll() {
        return em.createQuery("select object(o) from User as o").getResultList();
    }

    @SuppressWarnings("empty-statement")
    public Boolean  existNickname(String nickname) {
        Query q = em.createNamedQuery("User.findByNickname");
        q.setParameter ("nickname", nickname);
        try{
            q.getSingleResult();
        //if it doesn't exist, return false
        }catch(NoResultException e){
            return new Boolean(false);
        //this shouldn't happen, but in development proccess, it took place. 
        }catch(NonUniqueResultException e){
            return new Boolean(true);
        }
        return new Boolean(true);
    }

    public User login(String nickname, String password) throws LoginException{
        Query q = em.createQuery("SELECT u FROM User u WHERE u.nickname = :nickname AND u.password = :password");
        //String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        //System.out.println(passwordHash);
        password = crypt(password);
        
        q.setParameter ("nickname", nickname);
        q.setParameter("password", password);
        
        
         User User = new User();
        try{
            User = (User) q.getSingleResult();
        }catch(NoResultException e){
            throw new LoginException("Failed to Log in. Please check spelling of your User/password and try again.");
        }
        return User;

    }

    private String crypt(String password) {
        byte[] defaultBytes = password.getBytes();
        try{
            MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();
            
            StringBuilder hexString = new StringBuilder();

            for (int i=0;i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString((messageDigest[i] >>> 4) & 0x0F));
                hexString.append(Integer.toHexString(0x0F & messageDigest[i]));
            }
            password = hexString.toString();

        }catch(NoSuchAlgorithmException nsae){
            nsae.printStackTrace();
        }
        return password;
    }

    public void activateUser(String nickname, String activationKey) throws NonExistentUserException, WrongActivationNumberException {
        Query q = em.createNamedQuery("User.findByNickname");
        User user = null;
        try{
            q.setParameter("nickname", nickname);
            user = (User) q.getSingleResult();
        }catch(NoResultException e){
            System.out.println("User does not exist");
            throw new NonExistentUserException("User does not exist");
        }
        if( !activationKey.equals(user.getActivationKey()) ){
            System.out.println("Wrong activation number");
            throw new WrongActivationNumberException("Wrong activation number");
        }else{
            q = em.createQuery("UPDATE User SET active = 1 WHERE nickname = :nickname");
            q.setParameter("nickname", nickname);
            q.executeUpdate();
        }


       
    }

    public void deactivateUser(Integer idUser) {
        Query q = em.createQuery("UPDATE User SET active = 0 WHERE idUser = :idUser");
        q.setParameter("idUser", idUser);
        q.executeUpdate();
    }

    public void addFreeChips(Freechipsincome freeChipsIncome) {
        User user = freeChipsIncome.getUseridUser();

        em.persist(freeChipsIncome);
        Query q = em.createNativeQuery("UPDATE user SET freeChipsBalance = freeChipsBalance + ? WHERE idUser = ?");
        q.setParameter(1, freeChipsIncome.getChipsAmount());
        q.setParameter(2, user.getIdUser());
        q.executeUpdate();
        
    }

    public Combochips getComboChips(Integer idCombo){
        Query q = em.createNamedQuery("Combochips.findByIdCombo").setHint("toplink.refresh", "true");
        Combochips combo = (Combochips) q.setParameter("idCombo", idCombo).getSingleResult();
        return combo;
    }

    public User getUserBalanceDetailed(User user) {
        Query q = em.createNamedQuery("User.findByIdUser").setHint("toplink.refresh", "true");
        q.setParameter("idUser", user.getIdUser());
        User details = (User) q.getSingleResult();
        details.getChipsexpenditureCollection().size();
        details.getChipsincomeCollection().size();
        details.getFreechipsincomeCollection().size();
        details.getFreechipsincomeCollection1().size();

        return details;
    }

    public String addPaypalChips(User user, CreditCardServer data) throws PayPalServiceException {

        String transactionId;
		NVPCallerServices caller;

		try {

            Combochips combo = getComboChips(new Integer(data.getIdCombo()));

			caller = PayPalUtil.getCaller();

			// NVPEncoder object is created and all the name value pairs are
			// loaded into it.
			NVPEncoder encoder = new NVPEncoder();

			encoder.add("METHOD", "DoDirectPayment");
			encoder.add("PAYMENTACTION", "Sale");
			encoder.add("CREDITCARDTYPE", data.getCreditCardType());
			encoder.add("ACCT", data.getCreditCardNumber());
			encoder.add("EXPDATE", data.getExpdateMonth()
					+ data.getExpdateYear());
			encoder.add("CVV2", data.getCvv2Number());

			encoder.add("FIRSTNAME", user.getName());
			encoder.add("LASTNAME", user.getLastName());
            encoder.add("EMAIL", user.getEmail());
			encoder.add("COUNTRYCODE", "ES");
			//encoder.add("STATE", userData.geti);
			encoder.add("CITY", ""+user.getIdCity());
			encoder.add("STREET", user.getAddress());
			encoder.add("ZIP", user.getPostalCode());
			encoder.add("AMT", ""+combo.getCost());
			encoder.add("CURRENCYCODE", data.getCurrency());
            encoder.add("PAYERID", ""+user.getIdUser());
			// encode method will encode the name and value and form NVP string
			// for the request
			String NVPString = encoder.encode();

			// call method will send the request to the server and return the
			// response NVPString
			String ppresponse = (String) caller.call(NVPString);

			// NVPDecoder object is created
			NVPDecoder resultValues = new NVPDecoder();
			// decode method of NVPDecoder will parse the request and decode the
			// name and value pair
			resultValues.decode(ppresponse);

			transactionId = resultValues.get("TRANSACTIONID");

			// checks for Acknowledgement and redirects accordingly to display
			// error messages
			String strAck = resultValues.get("ACK");
			if (strAck != null
					&& !(strAck.equals("Success") || strAck
							.equals("SuccessWithWarning"))) {
				throw new PayPalServiceException(resultValues
						.get("L_SHORTMESSAGE0")
						+ ": " + resultValues.get("L_LONGMESSAGE0"));
			}
            updateChipsTable(combo, user, transactionId, caller);
		} catch (Exception e) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, e.getMessage(), e);
			throw new PayPalServiceException(e.getMessage());
		}

        

		return transactionId;

    }

    private boolean refundPayPalTransaction(NVPCallerServices caller,
			String transactionId) {
		try {
			// NVPEncoder object is created and all the name value pairs are
			// loaded into it.
			NVPEncoder encoder = new NVPEncoder();

			encoder.add("METHOD", "RefundTransaction");
			encoder.add("REFUNDTYPE", "Full");
			encoder.add("TRANSACTIONID", transactionId);
			encoder.add("NOTE", "Transacción reembolsada, ya que no se pudo guardar el pago en el servidor de snoof.");

			// encode method will encode the name and value and form NVP string
			// for the request
			String nVPString = encoder.encode();

			// call method will send the request to the server and return the
			// response NVPString
			String ppresponse = (String) caller.call(nVPString);

			// NVPDecoder object is created
			NVPDecoder decoder = new NVPDecoder();

			// decode method of NVPDecoder will parse the request and decode the
			// name and value pair
			decoder.decode(ppresponse);

			// String transactionId = decoder.get("TRANSACTIONID");

			// checks for Acknowledgement and redirects accordingly to display
			// error messages
			String strAck = decoder.get("ACK");
			if (strAck != null
					&& !(strAck.equals("Success") || strAck
							.equals("SuccessWithWarning"))) {
				System.err.println(decoder.get("L_SHORTMESSAGE0") + ": "
						+ decoder.get("L_LONGMESSAGE0"));
				return false;
			}
			/*
			 * Transaction ID: decoder.get("REFUNDTRANSACTIONID") Gross Refund
			 * Amount: decoder.get("GROSSREFUNDAMT")
			 */

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

    public String doPaypalLogin(Chipsincome income, CreditCardServer data, String returnUrl, String cancelUrl) throws PayPalServiceException {
        NVPCallerServices caller = new NVPCallerServices();
        String strNVPRequest = "";
		try {
			caller = PayPalUtil.getCaller();

            //StringBuffer sbErrorMessages= new StringBuffer("");

            //NVPEncoder object is created and all the name value pairs are loaded into it.
            NVPEncoder encoder = new NVPEncoder();

            encoder.add("METHOD","SetExpressCheckout");
            encoder.add("RETURNURL",returnUrl);
            encoder.add("CANCELURL",cancelUrl);

            //encoder.add("AMT",data.getAmount());
            encoder.add("PAYMENTACTION","Sale");
            encoder.add("CURRENCYCODE",data.getCurrency());

            //request.getSession().setAttribute("paymentAmount", amount);
            //request.getSession().setAttribute("currencyCodeType", currency);
            String testEnv = "sandbox";

            //encode method will encode the name and value and form NVP string for the request
            strNVPRequest = encoder.encode();

            //call method will send the request to the server and return the response NVPString
            String ppresponse =
                (String) caller.call( strNVPRequest);

            //NVPDecoder object is created
            NVPDecoder decoder = new NVPDecoder();

            //decode method of NVPDecoder will parse the request and decode the name and value pair
            decoder.decode(ppresponse);

                //checks for Acknowledgement and redirects accordingly to display error messages
            String strAck = decoder.get("ACK");
            if(strAck !=null && !(strAck.equals("Success") || strAck.equals("SuccessWithWarning"))){
                throw new PayPalServiceException("Error en Autenticación. " +
                        decoder.get("L_SHORTMESSAGE0") + ": "
                        + decoder.get("L_LONGMESSAGE0"));

            }else {
                return "https://www."+testEnv+".paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+decoder.get("TOKEN");
            }
        } catch (Exception e) {
			//rollbackDataBase(con);
            e.printStackTrace();
			throw new PayPalServiceException(e.getMessage());
		}
    }

    public String getPaypalDetails(String token, String paymentAmount, String currencyCode, String locale) throws PayPalServiceException {
        NVPEncoder encoder = new NVPEncoder();
        NVPCallerServices caller = new NVPCallerServices();
        try{
            caller = PayPalUtil.getCaller();
			encoder.add("METHOD","GetExpressCheckoutDetails");
			encoder.add("TOKEN",token);

			//encode method will encode the name and value and form NVP string for the request
			String strNVPString = encoder.encode();

			//call method will send the request to the server and return the response NVPString
			String strNVPResponse =
				(String) caller.call( strNVPString);

			//NVPDecoder object is created
			NVPDecoder decoder = new NVPDecoder();

			//decode method of NVPDecoder will parse the request and decode the name and value pair
			decoder.decode(strNVPResponse);

			//checks for Acknowledgement and redirects accordingly to display error messages
			String strAck = decoder.get("ACK");
			if(strAck !=null && !(strAck.equals("Success") || strAck.equals("SuccessWithWarning"))){
				throw new PayPalServiceException("Error Obteniendo Detalles de Usuario. " +
                        decoder.get("L_SHORTMESSAGE0") + ": "
                        + decoder.get("L_LONGMESSAGE0"));
			}

			String shipToStreet = decoder.get("SHIPTOSTREET");
			String shipToStreet2 = decoder.get("SHIPTOSTREET2");
			String chipToCity = decoder.get("SHIPTOCITY");
			String chipToState = decoder.get("SHIPTOSTATE");
			String chipToZip = decoder.get("SHIPTOZIP");
			String chipToCountry = decoder.get("SHIPTOCOUNTRYNAME");
			token = decoder.get("TOKEN");
			String payerId = decoder.get("PAYERID");

            String url = "GetExpressCheckoutDetails.jsp"
					+ "?locale="+locale
					+ "&paymentAmount=" + paymentAmount
					+ "&currencyCodeType=" + currencyCode
					+ "&shipToStreet=" + shipToStreet
					+ "&shipToStreet2=" + shipToStreet2
					+ "&chipToCity=" + chipToCity
					+ "&chipToState=" + chipToState
					+ "&chipToZip=" + chipToZip
					+ "&chipToCountry=" + chipToCountry
					+ "&token=" + token
					+ "&payerId=" + payerId;
            return url;

        } catch (Exception e) {
			e.printStackTrace();
			throw new PayPalServiceException(e.getMessage());
		}
    }

    public String doPaypalPayment(User user, Chipsincome income, CreditCardServer data, String token, String payerId, String locale ) throws PayPalServiceException {
        String transactionId;
		String currency;
		String amount;
	    try {

            NVPCallerServices caller = new NVPCallerServices();
            caller = PayPalUtil.getCaller();

			//NVPEncoder object is created and all the name value pairs are loaded into it.
            NVPEncoder encoder = new NVPEncoder();
			encoder.add("METHOD","DoExpressCheckoutPayment");
			encoder.add("TOKEN",token);
			encoder.add("PAYERID",payerId);
			encoder.add("PAYMENTACTION","Sale");
			//encoder.add("AMT", data.get());
			encoder.add("CURRENCYCODE",data.getCurrency());

			//encode method will encode the name and value and form NVP string for the request
	    	String strNVPString = encoder.encode();

			//call method will send the request to the server and return the response NVPString
			String strNVPResponse =
				(String) caller.call( strNVPString);

			//NVPDecoder object is created
			NVPDecoder decoder = new NVPDecoder();

			//decode method of NVPDecoder will parse the request and decode the name and value pair
			decoder.decode(strNVPResponse);

			//checks for Acknowledgement and redirects accordingly to display error messages
			String strAck = decoder.get("ACK");
			if(strAck !=null && !(strAck.equals("Success") || strAck.equals("SuccessWithWarning"))){
				throw new PayPalServiceException("Error al momento de efectuar el pago. "
                        + decoder.get("L_SHORTMESSAGE0") + ": "
                        + decoder.get("L_LONGMESSAGE0"));
			}

			transactionId = decoder.get("TRANSACTIONID");
			currency = decoder.get("CURRENCYCODE");
			amount = decoder.get("AMT");

            //updateChipsTable(income, data, transactionId, caller);

		} catch (Exception e) {
			e.printStackTrace();
			throw new PayPalServiceException(e.getMessage());
		}

        //@TODO + request.getSession().getAttribute("locale")
		String url = "DoExpressCheckoutPayment.jsp"
				+ "?locale="+locale
				+ "&transactionId=" + transactionId
				+ "&currency=" + currency
				+ "&amount=" + amount;
        return url;
    }

    private void updateChipsTable(Combochips combo, User user, String transactionId, NVPCallerServices caller ) throws PayPalServiceException {
        try {
			Chipsincome income = new Chipsincome();
            //@TODO Utilizar el método correcto
            income.setChargeMethod(new Short("1"));
            income.setChipsAmount(combo.getChipsAmount());
            income.setMoneyPaid(new BigDecimal(combo.getCost()));
            income.setTransactionNumber(transactionId);
            income.setUseridUser(user);

            em.persist(income);
            Query q = em.createQuery("update User set chipsBalance = (chipsBalance + :chipsIncome) WHERE idUser = :idUser");
            q.setParameter("chipsIncome", income.getChipsAmount());
            q.setParameter("idUser", income.getUseridUser().getIdUser());
            q.executeUpdate();

            Freechipsincome freeChips = new Freechipsincome();
            freeChips.setChipsAmount(combo.getFreeChipsAmount());
            freeChips.setUseridUser(user);

            em.persist(freeChips);
            q = em.createQuery("update User set freeChipsBalance = (freeChipsBalance + :chipsIncome) WHERE idUser = :idUser");
            q.setParameter("chipsIncome", income.getChipsAmount());
            q.setParameter("idUser", income.getUseridUser().getIdUser());
            q.executeUpdate();

            //@TODO Regalar fichas a quien me invitó.

        } catch (Exception e) {
			refundPayPalTransaction(caller, transactionId);
			//rollbackDataBase(con);
            e.printStackTrace();
			throw new PayPalServiceException(e.getMessage());
		}
    }

    public void sendMail(String email, String subject, String body) throws ActivationMailException{
        SnoofMail snoofMail = new SnoofMail();
        snoofMail.sendMail(email, subject, body);
    }

    public void sendRegisterMail(User user) throws ActivationMailException {

        SnoofMail snoofMail = new SnoofMail();
        String subject = "Registration";
        String body = "";
        String email = user.getEmail();

        Query q = em.createQuery("select reg from Registermailpage as reg ORDER BY reg.idPage DESC");
        List<Registermailpage> mails = q.getResultList();
        if (mails.size() == 0){
            throw new ActivationMailException("There is not an activation HTML page.");
        }

        Registermailpage mailPage = mails.get(0);

        String activationKey = user.getActivationKey();
        body = mailPage.getHtmlPage();
        body = body.replace("__nickname".subSequence(0, "__nickname".length()), user.getNickname().subSequence(0, user.getNickname().length()));
        body = body.replace("__activationKey".subSequence(0, "__activationKey".length()), activationKey.subSequence(0, activationKey.length()));

        try {
            snoofMail.sendMail(email, subject, body);
        } catch (ActivationMailException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Facebookinfo findByFacebookId(String facebookId) throws NonExistentUserException{
        Facebookinfo facebookInfo;
        Query q = em.createNamedQuery("Facebookinfo.findByFacebookId");
        q.setParameter("facebookId", facebookId);
        try{
            facebookInfo = (Facebookinfo) q.getSingleResult();
        }catch(NoResultException e){
            System.out.println("User "+facebookId+" does not exist");
            throw new NonExistentUserException("User does not exist");
        }

        return facebookInfo;
    }

    /**
     *
     * @param facebookUser
     * @return Password of the new account to do the programatic login. 
     * @throws com.snoofing.exception.ServerException
     */
    public String createUserWithFacebookAccount(Facebookinfo facebookUser) throws ServerException{
        String time = "" + GregorianCalendar.getInstance().getTimeInMillis();
        User user = new User();
        user.setNickname(facebookUser.getFacebookId());
        user.setPassword(time);
        //@TODO Definir cuál correo llevará. 
        //user.setEmail("prueba@prueba.com");
        user.setName(facebookUser.getFirstName());
        user.setLastName(facebookUser.getLastName());
        user.setActivationKey(time);
        user.setActive(true);

        //
        create(user);
        Query q = em.createNativeQuery("SELECT LAST_INSERT_ID()");
        Vector idUser = (Vector) q.getSingleResult();
        Long id = (Long) idUser.get(0);
        facebookUser.setUseridUser(id.intValue());
        em.persist(facebookUser);
        return time;

    }
    public String updatePassword4FacebookLogin (String facebookId){
        String time = "" + GregorianCalendar.getInstance().getTimeInMillis();
        Query q = em.createNamedQuery("User.findByNickname");
        q.setParameter("nickname", facebookId);
        User user = (User) q.getSingleResult();
        String passwordHash = crypt(time);
        user.setPassword(passwordHash);

        q = em.createNativeQuery("UPDATE user SET password = ? WHERE nickname = ?");
        q.setParameter(1, passwordHash);
        q.setParameter(2, facebookId);
        q.executeUpdate();

        return time;

    }

}
