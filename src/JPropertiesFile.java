import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class JPropertiesFile
{
	private final String file_name_;
	
	JPropertiesFile(final String file_name)
	{
		file_name_ = file_name;
	}
	
	protected final boolean is_file_exist()
	{
		if(file_name_.isEmpty())
		{
			return false;
		}
		else
		{
			File f = new File(file_name_);
			return (f.exists() && !f.isDirectory());
		}
	}
	
	protected final Properties load()
	{
		if(!file_name_.isEmpty())
		{
			InputStream input = null;
			
			try
			{
				Properties prop = new Properties();
				input = new FileInputStream(file_name_);
				prop.load(input);
				
				input.close();
				
				return prop;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				
				return null;
			}
			finally
			{
				if(input != null)
				{
					try
					{
						input.close();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		else
		{
			System.err.println("File name is empty.");
			
			return null;
		}
	}
	
	protected void save(final Properties prop)
	{
		if(!file_name_.isEmpty())
		{
			OutputStream output = null;
			
			try
			{
				output = new FileOutputStream(file_name_);
				prop.store(output, null);
			}
			catch(IOException io)
			{
				io.printStackTrace();
			}
			finally
			{
				if(output != null)
				{
					try
					{
						output.close();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
}
