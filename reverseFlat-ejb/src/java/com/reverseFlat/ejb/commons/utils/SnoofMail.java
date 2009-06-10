/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.reverseFlat.ejb.commons.utils;

import com.reverseFlat.ejb.exception.ActivationMailException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Luis Felipe Giraldo
 */
public class SnoofMail {

    String SMTP_HOST_NAME;
    int SMTP_HOST_PORT;
    String SMTP_AUTH_USER;
    String SMTP_AUTH_PWD;
    String SMTP_MAIL_FROM;

    public SnoofMail() throws ActivationMailException {

        try{
            ResourceBundle prop = ResourceBundle.getBundle ("com.snoofing.ejb.session.mailProperties");
            SMTP_HOST_NAME = prop.getString("SMTP_HOST_NAME");
            SMTP_HOST_PORT = Integer.parseInt(prop.getString("SMTP_HOST_PORT"));
            SMTP_AUTH_USER = prop.getString("SMTP_AUTH_USER");
            SMTP_AUTH_PWD =  prop.getString("SMTP_AUTH_PWD");
            SMTP_MAIL_FROM = prop.getString("SMTP_MAIL_FROM");
        }catch(Exception e){
            throw new ActivationMailException("We had some problems sending the e-mail. Please try it again or contact us.");
        }
        
    }

    public void sendMail(String email, String subject, String body) throws ActivationMailException{
        try {

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", SMTP_HOST_NAME);
            props.put("mail.smtps.auth", "true");
            // props.put("mail.smtps.quitwait", "false");
            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(false);
            Transport transport = mailSession.getTransport();
            Message message = new MimeMessage(mailSession);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(SMTP_MAIL_FROM));
            StringTokenizer tokenizer = new StringTokenizer(email, ",");
            while(tokenizer.hasMoreTokens()){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(tokenizer.nextToken()));
            }
            
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            messageBodyPart.setHeader("MIME-Version" , "1.0" );
            messageBodyPart.setHeader("Content-Type", "text/html");
            MimeMultipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(SnoofMail.class.getName()).log(Level.SEVERE, null, ex);
            throw new ActivationMailException("We had some problems sending the activation e-mail. Please try it again or contact us.");
        } 
    }

}
