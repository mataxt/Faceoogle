package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="messagesBean")
public class MessagesBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String to;
	private String title;
	private String message;
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}