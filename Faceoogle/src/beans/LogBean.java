package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.Profile;
import vm.LogViewModel;

@ManagedBean(name="logBean")
public class LogBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<LogViewModel> myLogs;

	public List<LogViewModel> getMyLogs(){
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		myLogs = Profile.getLogs(req.getParameter("user"));
		return myLogs;
	}
}
