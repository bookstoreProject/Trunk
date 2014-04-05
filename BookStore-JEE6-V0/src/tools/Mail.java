package tools;

import org.apache.commons.mail.*;

import entities.Client;

public class Mail {

	public void send(Client c) {
		String myEmailId = "marcsayd@gmail.com";
		String myPassword = "marklechaud";
		String senderId = "bookstore@gmail.com";
		try {
			MultiPartEmail email = new MultiPartEmail();
			email.setSmtpPort(587);
			email.setAuthenticator(new DefaultAuthenticator(myEmailId, myPassword));
			//email.setDebug(true);
			email.setHostName("smtp.gmail.com");
			email.setFrom(senderId);
			email.setSubject("Résumé de votre commande");
			email.setMsg("Bonjour,\n\nVous trouverez en fichier joint le résumé de la commande que vous avez passée.\n\nMerci,\nBookstore team");
			email.addTo(c.getEmail());
			email.setTLS(true);

			EmailAttachment attachment = new EmailAttachment();
			attachment.setPath("facture.pdf");
			attachment.setDisposition(EmailAttachment.ATTACHMENT);
			attachment.setDescription("Facture");
			attachment.setName("facture.pdf");
			email.attach(attachment);

			email.send();
			//System.out.println("Mail sent!");
		} catch (Exception e) {
			System.out.println("Exception :: " + e);
		}
	}

}
