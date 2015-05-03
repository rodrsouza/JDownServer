import java.util.Properties;
import javax.mail.*;

public class JImap
{
	
	public JImap()
	{
	}
	
	public boolean retrieve_emails()
	{
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try
		{
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			JCfg cfg = JCfg.getInstance();
			store.connect(cfg.getImapServer(), cfg.getUserName(), cfg.getPassword());
			
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());

			Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
            }
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);
            System.out.println("SENT DATE:" + msg.getSentDate());
            System.out.println("SUBJECT:" + msg.getSubject());
            System.out.println("CONTENT:" + bp.getContent());
            
            inbox.close(false);
            store.close();
		}
		catch(MessagingException connect_error)
		{
			connect_error.printStackTrace();
			System.err.println("Error to connect or to retrieve e-mails.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
}
