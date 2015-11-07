package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import logic.Profile;
import vm.LogViewModel;

@ManagedBean(name="logBean")
public class LogBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<LogViewModel> myLogs;
	
	public List<LogViewModel> getMyLogs(){
		myLogs = Profile.getLogs((String)param['user']);
		return myLogs;
	}
}
