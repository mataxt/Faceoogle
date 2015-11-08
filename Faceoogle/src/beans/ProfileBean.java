package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.Profile;
import vm.UserViewModel;

@SessionScoped
@ManagedBean(name = "profileBean")
public class ProfileBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private UserViewModel vm;
	
	public UserViewModel getVm() {
		String user = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest()).getParameter("user");
		vm = Profile.getUserInfo(user);
		return vm;
	}
	
	public void setVm(UserViewModel vm) {
		this.vm = vm;
	}
}
