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
}
