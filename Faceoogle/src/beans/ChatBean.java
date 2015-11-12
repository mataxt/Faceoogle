package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.ChatLogic;
import vm.ChatViewModel;

@ViewScoped
@ManagedBean(name = "chatBean")
public class ChatBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<ChatViewModel> chatMessages = new ArrayList<ChatViewModel>();
	private String paramUser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest()).getParameter("user");
	private String message;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public List<ChatViewModel> getChatMessages() {
		chatMessages = ChatLogic.getChatHistory(userBean.getUsername(), paramUser);
		return chatMessages;
	}

	public void setChatMessages(List<ChatViewModel> chatMessages) {
		this.chatMessages = chatMessages;
	}

	public String getParamUser() {
		return paramUser;
	}

	public void setParamUser(String paramUser) {
		this.paramUser = paramUser;
	}
	
	public String sendMessage() {
		if(paramUser == null) {
			return "index.xhtml";
		}
		ChatLogic.sendMessage(userBean.getUsername(), paramUser, message);
		return null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
