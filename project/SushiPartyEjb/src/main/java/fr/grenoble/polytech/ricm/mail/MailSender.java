package fr.grenoble.polytech.ricm.mail;

import java.util.Date;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@RequestScoped
public class MailSender {

    @Resource(lookup = "mail/SushiParty")
    private Session mailSession;

    public void sendEmail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = new MimeMessage(mailSession);    
    	
        message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));

        InternetAddress[] address = {new InternetAddress(to)};

        message.setRecipients(Message.RecipientType.TO, address);

        message.setSubject(subject);

        message.setSentDate(new Date());

        message.setContent(body,"text/html; charset=utf-8");

        Transport.send(message);

    }
    
    
    public void sendAccountValidationMail(String to,String validationLink) throws MessagingException {
    	String subject ="Compte SushiParty - E-mail de validation"; 
    	String body = "Bonjour,<br><br>";
    		   body	 = body + "Nous vous remercions d'avoir ouvert un compte SushiParty. Pour activer votre compte, veuillez cliquer sur le lien suivant :<br>";
    		   body	 = body + "<br>";	
    		   body	 = body + "<a href=" + validationLink + ">" + validationLink +"</a>";
    		   body	 = body + "<br><br>";
    		   body	 = body + "<b>Si l'activation ne fonctionne pas après avoir cliqué sur le lien, vous pouvez copier le lien dans votre fenêtre de navigateur ou le saisir directement.<br><br><br></b>";
    		   body	 = body + "Cordialement,<br>";
    		   body	 = body + "<br>";
    		   body	 = body + "Votre équipe SushiParty - ECOM 2015<br>";
    		   body	 = body + "----------------------------- <br>";
    		   body	 = body + "<a href=\"http://ecomserver.noip.me/SushiPartyWeb\">www.sushiparty.com</a><br>";

       this.sendEmail(to,subject, body);
    }
    
    public void sendResetAccountPasswordMail(String to,String resetAccountPasswordLink) throws MessagingException {
    	String subject ="Compte SushiParty - E-mail de validation"; 
    	String body = "Bonjour,<br><br>";
    		   body	 = body + "Nous vous remercions d'avoir ouvert un compte SushiParty. Pour activer votre compte, veuillez cliquer sur le lien suivant :<br>";
    		   body	 = body + "<br>";	
    		   body	 = body + "<a href=" + resetAccountPasswordLink + ">" + resetAccountPasswordLink +"</a>";
    		   body	 = body + "<br><br>";
    		   body	 = body + "<b>Si l'activation ne fonctionne pas après avoir cliqué sur le lien, vous pouvez copier le lien dans votre fenêtre de navigateur ou le saisir directement.<br><br><br></b>";
    		   body	 = body + "Cordialement,<br>";
    		   body	 = body + "<br>";
    		   body	 = body + "Votre équipe SushiParty - ECOM 2015<br>";
    		   body	 = body + "----------------------------- <br>";
    		   body	 = body + "<a href=\"http://ecomserver.noip.me/SushiPartyWeb\">www.sushiparty.com</a><br>";

       this.sendEmail(to,subject, body);
    }

}