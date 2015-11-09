package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.Profile;
@SessionScoped
@ManagedBean(name = "friendBean")
public class FriendBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String isMyFriend;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String addFriend() {
		String user = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("user");
		Profile.addFriend(userBean.getUsername(), user);
		return null;
	}

	public String removeFriend() {
		String user = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("user");
		Profile.removeFriend(userBean.getUsername(), user);
		return null;
	}

	public String getIsMyFriend() {
		String user = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("user");
		isMyFriend = Profile.isFriend(userBean.getUsername(), user);
		return isMyFriend;
	}

	public void setIsMyFriend(String isMyFriend) {
		this.isMyFriend = isMyFriend;
	}
}
