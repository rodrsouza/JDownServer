import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class JSmtp
{
	public JSmtp()
	{
	}
	
	public boolean sendEmail(final JMailSend msgSend)
	{	
		// sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", JCfg.getInstance().getSmtpServer());
        properties.put("mail.smtp.port", JCfg.getInstance().getSmtpPort());
        properties.put("mail.smtp.auth", JCfg.getInstance().getSmtpNeedAuth());
        properties.put("mail.smtp.starttls.enable", JCfg.getInstance().getSmtpSSL());
        properties.put("mail.user", JCfg.getInstance().getUserName());
        properties.put("mail.password", JCfg.getInstance().getPassword());
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(JCfg.getInstance().getUserName(), JCfg.getInstance().getPassword());
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        try
        {
			msg.setFrom(new InternetAddress(msgSend.getFrom()));
		}
        catch (AddressException ad)
        {
        	System.err.println("JSmtp: Can't convert " + msgSend.getFrom() + " into a InternetAddress.");
        	
        	return false;
        }
        catch (MessagingException e1)
        {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return false;
		}
        
        ArrayList<InternetAddress> toAddresses = new ArrayList<InternetAddress>();
        for( String address : msgSend.getTo())
        {
	        try
	        {
	        	toAddresses.add(new InternetAddress(address));
	        }
	        catch (AddressException e1)
	        {// TODO Auto-generated catch block
	        	e1.printStackTrace();
	        	
	        	return false;
	        }
        }
        
        try
        {
			msg.setRecipients(Message.RecipientType.TO, (Address[]) toAddresses.toArray());
		}
        catch (MessagingException e1)
        {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			return false;
		}
        msg.setSubject(msgSend.getSubject());
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(msgSend.getContent(), "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        ArrayList<String> attachments = msgSend.getAttachments(); 
        if(!attachments.isEmpty())
        {
            for (String filePath : attachments)
            {
                MimeBodyPart attachPart = new MimeBodyPart();
                try 
                {
                    attachPart.attachFile(filePath);
                }
                catch(MessagingException e)
                {
                	System.err.println("JSmtp: Can't attach the file: " + filePath);
                	
                	return false;
                }
                catch (IOException ex)
                {
                    System.err.println("JSmtp: Failed to attach the file: ");
                	ex.printStackTrace();
                	
                	return false;
                }
 
                try 
                {
					multipart.addBodyPart(attachPart);
				}
                catch (MessagingException e)
                {
					System.err.println("JSmtp: Can't add attachment to the BodyPart.");
					e.printStackTrace();
					
					return false;
				}
            }
        }
 
        // sets the multi-part as e-mail's content
        try
        {
			msg.setContent(multipart);
		}
        catch (MessagingException e)
        {
			System.err.println("JSmtp: Can't add content to the message.");
			e.printStackTrace();
			
			return false;
		}
 
        // sends the e-mail
        try
        {
			Transport.send(msg);
		}
        catch (MessagingException e)
        {
			System.err.println("JSmtp: Unable to send message.");
			
			return false;
		}
        
        return true;
	}
}
