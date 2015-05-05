import java.util.ArrayList;


public class JMailSend
{
	private ArrayList<String> to_;
	private ArrayList<String> attachment_list_;
	private String from_;
	private String subject_;
	private String content_;
	
	public JMailSend()
	{
		to_ = new ArrayList<String>();
		attachment_list_ = new ArrayList<String>();
		
		subject_ = "";
		content_ = "";
	}
	
	public final ArrayList<String> getTo()
	{
		return to_;
	}
	
	public void addTo(final String to)
	{
		to_.add(to);
	}
	
	public void addAttachment(final String attachment)
	{
		attachment_list_.add(attachment);
	}
	
	public void setSubject(final String subject)
	{
		subject_ = subject;
	}
	
	public final void setContent(final String content)
	{
		content_ = content;
	}
	
	public final String getContent()
	{
		return content_;
	}
	
	public final ArrayList<String> getAttachments()
	{
		return attachment_list_;
	}
	
	public final String getFrom()
	{
		return from_;
	}
	
	public final String getSubject()
	{
		return subject_;
	}
}
