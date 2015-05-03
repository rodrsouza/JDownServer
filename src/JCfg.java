import java.util.Properties;


public final class JCfg extends JPropertiesFile
{
	private final static String config_file_name = "config.properties";
	private static final JCfg instance = new JCfg();
	
	private boolean need_update_ = false;
	private Properties props_;
	
	private final String imap_server_key_ = "imap_server";
	private String imap_server_ = "imap.server.com";
	private final String user_name_key_ = "username";
	private String user_name_ = "usr";
	private final String password_key_ = "password";
	private String password_ = "pwd";
	
	private JCfg()
	{
		super(config_file_name);
		if(is_file_exist())
		{
			update_var();
		}
		else
		{
			create_default_file();
		}
	}
	
	public static JCfg getInstance()
	{
		return instance;
	}
	
	private void update_var()
	{
		props_ = load();
		
		imap_server_ = props_.getProperty(imap_server_key_);
		user_name_ = props_.getProperty(user_name_key_);
		password_ = props_.getProperty(password_key_);
	}
	
	private void create_default_file()
	{
		props_ = new Properties();
		
		props_.setProperty(imap_server_key_, imap_server_);
		props_.setProperty(user_name_key_, user_name_);
		props_.setProperty(password_key_, password_);
		
		save(props_);
	}
	
	public final String getUserName()
	{
		return user_name_;
	}
	
	public final String getPassword()
	{
		return password_;
	}
	
	public final String getImapServer()
	{
		return imap_server_;
	}
}
