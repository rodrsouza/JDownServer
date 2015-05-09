import java.util.Properties;


public final class JCfg extends JPropertiesFile
{
	private final static String config_file_name = "config.properties";
	private static final JCfg instance = new JCfg();
	
	private boolean need_update_ = false;
	private Properties props_;
	
	private final String imap_server_key_ = "imap_server";
	private String imap_server_ = "imap.server.com";
	
	private final String smtp_server_key_ = "smtp_server";
	private String smtp_server_ = "smtp.server.com";
	
	private final String smtp_port_key_ = "smtp_port";
	private String smtp_port_ = "465";
	
	private final String smtp_ssl_key_ = "smtp_ssl";
	private String smtp_ssl_ = "true";
	
	private final String smtp_need_auth_key_ = "smtp_need_auth";
	private String smtp_need_auth_ = "true";
	
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
		smtp_server_ = props_.getProperty(smtp_server_key_);
		smtp_port_ = props_.getProperty(smtp_port_key_);
		smtp_ssl_ = props_.getProperty(smtp_ssl_key_);
		smtp_need_auth_ = props_.getProperty(smtp_need_auth_key_);
		user_name_ = props_.getProperty(user_name_key_);
		password_ = props_.getProperty(password_key_);
	}
	
	private void create_default_file()
	{
		props_ = new Properties();
		
		props_.setProperty(imap_server_key_, imap_server_);
		props_.setProperty(smtp_server_key_, smtp_server_);
		props_.setProperty(smtp_port_key_, smtp_port_);
		props_.setProperty(smtp_ssl_key_, smtp_ssl_);
		props_.setProperty(smtp_need_auth_key_, smtp_need_auth_);
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
	
	public final String getSmtpServer()
	{
		return smtp_server_;
	}
	
	public final String getSmtpPort()
	{
		return smtp_port_;
	}
	
	public final String getSmtpSSL()
	{
		return smtp_ssl_;
	}
	
	public final String getSmtpNeedAuth()
	{
		return smtp_need_auth_;
	}
}
