import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

public class JImap
{
	private boolean show_messages = true;
	private ArrayList<Message> messages_retrieved_;
	
	
	public JImap()
	{
		messages_retrieved_ = new ArrayList<Message>();
	}
	
	public final boolean hasMessages()
	{
		return !messages_retrieved_.isEmpty();
	}
	
	public ArrayList<Message> getMessages()
	{
		return messages_retrieved_;
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
			
			final int msg_count = inbox.getMessageCount();
			
			messages_retrieved_.clear();
			
			for(int i=1; i<msg_count; ++i)
			{
				Message msg = inbox.getMessage(i);
				messages_retrieved_.add(msg);
				
				if(show_messages)
				{
					Address[] in = msg.getFrom();
		            for (Address address : in) {
		                System.out.println("FROM:" + address.toString());
		            }
		            Multipart mp = (Multipart) msg.getContent();
		            BodyPart bp = mp.getBodyPart(0);
		            System.out.println("SENT DATE:" + msg.getSentDate());
		            System.out.println("SUBJECT:" + msg.getSubject());
		            System.out.println("CONTENT:" + bp.getContent());
				}
			}
            
            inbox.close(false);
            store.close();
            
            return true;
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
