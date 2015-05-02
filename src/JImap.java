import java.util.Properties;


public class JImap
{
	final String imap_server_ = "imap.gmail.com";
	String user_login_;
	String password_;
	
	boolean retrieve_emails()
	{
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try
		{
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect();
		}
	}
}
