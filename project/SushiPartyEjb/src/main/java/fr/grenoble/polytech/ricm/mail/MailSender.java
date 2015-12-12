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

import fr.grenoble.polytech.ricm.entity.catalogue.Produit;
import fr.grenoble.polytech.ricm.entity.panier.Panier;
import fr.grenoble.polytech.ricm.entity.panier.PanierProduit;

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
    
    // Cette méthode permet d'envoyer un mail de notification au manager du magasin lorsqu'une commande devant être livrée 
    // à partir dudit magasin a été validé sur le site
    public void sendOrderValidateNotificationMail(String to,Panier panier) throws MessagingException {
    	String subject ="SushiParty - Notification de validation de commande "; 
    	String body = "Bonjour,<br><br>";
    		   body	 = body + "Nous vous indiquons qu'une commande a été effectuée dans votre magasin.<br>";
    		   body	 = body + "La commande doit être livrée à l'adresse suivante :<br>";
    		   body	 = body + panier.getClient().getNomComplet() + ":<br>";
    		   body	 = body + panier.getClient().getAddresse1() + ":<br>";
    		   body	 = body + panier.getClient().getAddresse2() + ":<br>";
    		   body	 = body + panier.getClient().getCodePostal() + " - " + panier.getClient().getVille()  + "<br>";
    		   body	 = body + "Si besoin, le numéro du client est : " +  panier.getClient().getTelephone() +"<br>";
    		   body	 = body + "<br><br>";
    		   
    		   body	 = body + "Les produits commandés sont les suivants :<br>";
    		   body	 = body + "<br>";
    		   body	 = body + "<table>";    		   
    		   for (PanierProduit lignePanier : panier.getProduits()) {
    			   body	 = body + "<tr>" +  "<td>" + lignePanier.getProduit().getDesignation() + "</td>" +  "<td>" + lignePanier.getQuantite() + "</td>" +   "<td>" + lignePanier.getPrixTotal() + "</td>"+"</tr>";
    		   }
    		   body	 = body + "</table>";
    		   body	 = body + "Pour un montant total de  :" + panier.getMontant()  +  "<br>";
    		   
    		   body	 = body + "Cordialement,<br>";
    		   body	 = body + "<br>";
    		   body	 = body + "Votre équipe SushiParty - ECOM 2015<br>";
    		   body	 = body + "----------------------------- <br>";
    		   body	 = body + "<a href=\"http://ecomserver.noip.me/SushiPartyWeb\">www.sushiparty.com</a><br>";

       this.sendEmail(to,subject, body);
    }

}