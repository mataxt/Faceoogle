package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.Profile;
import vm.LogViewModel;

@ViewScoped
@ManagedBean(name = "logBean")
public class LogBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<LogViewModel> myLogs;
	private List<LogViewModel> myFeed;
	private String paramUser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest()).getParameter("user");;
	private String messageLogs;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	public String getParamUser() {
		return paramUser;
	}

	public List<LogViewModel> getLogs() {
		myLogs = Profile.getLogs(paramUser);
		return myLogs;
	}

	public List<LogViewModel> getFeed() {
		myFeed = Profile.getFeed(userBean.getUsername());
		return myFeed;
	}

	public void setMyFeed(List<LogViewModel> myFeed) {
		this.myFeed = myFeed;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getMessageLogs() {
		return messageLogs;
	}

	public void setMessageLogs(String messageLogs) {
		this.messageLogs = messageLogs;
	}

	public String sendWriteLog() {
		Profile.writeLog(userBean.getUsername(), paramUser, messageLogs);
		return null;
	}

	public String sendFeedLog() {
		Profile.writeLog(userBean.getUsername(), userBean.getUsername(), messageLogs);
		return null;
	}

}