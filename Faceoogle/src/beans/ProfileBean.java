package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.UserLogic;
import vm.UserViewModel;

@ViewScoped
@ManagedBean(name = "profileBean")
public class ProfileBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String paramUser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest()).getParameter("user");;
	private UserViewModel vm;
	
	public UserViewModel getVm() {
		String user = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext()
		        .getRequest()).getParameter("user");
		vm = UserLogic.getUserInfo(user);
		return vm;
	}
	
	public void setVm(UserViewModel vm) {
		this.vm = vm;
	}
	
	public String getParamUser() {
		return paramUser;
	}
}
