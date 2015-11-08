package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.Profile;
import vm.LogViewModel;

@ManagedBean(name = "logBean")
public class LogBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<LogViewModel> myLogs;
	private String messageLogs;
	
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	
	public List<LogViewModel> getLogs() {
		String usr = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest()).getParameter("user");
		myLogs = Profile.getLogs(usr);
		return myLogs;
	}

	public String getMessageLogs() {
		return messageLogs;
	}
	
	public void setMessageLogs(String messageLogs) {
		this.messageLogs = messageLogs;
	}

	public String sendWriteLog() {
		String receiver = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest()).getParameter("user");
		Profile.writeLog(userBean.getUsername(), receiver, messageLogs);
		System.out.println("HELLO N00B: " + messageLogs);
		
		return null;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}
