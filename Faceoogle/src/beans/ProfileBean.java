package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import logic.Profile;
import vm.LogViewModel;

@ManagedBean(name="profileBean")
@SessionScoped
public class ProfileBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<LogViewModel> myLogs = Profile.getLogs("1");
	
	public List<LogViewModel> getMyLogs(){
		return myLogs;
	}

}
