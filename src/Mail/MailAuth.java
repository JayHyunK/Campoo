package Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
	 
public class MailAuth extends Authenticator{
	PasswordAuthentication passwordAuth;
	public MailAuth() {
		String mail_id = "programmer.jonghyun";
	    String mail_pw = "*";
	        
	    passwordAuth = new PasswordAuthentication(mail_id, mail_pw);
	}
	    
	public PasswordAuthentication getPasswordAuthentication() {
	        return passwordAuth;
	}
}
