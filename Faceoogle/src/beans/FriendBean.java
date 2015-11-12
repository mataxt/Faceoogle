package beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import logic.FriendLogic;

@ViewScoped
@ManagedBean(name = "friendBean")
public class FriendBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String isMyFriend;
	private String paramUser = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
			.getRequest()).getParameter("user");
	private List<String> friendList;
	
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String addFriend() {
		FriendLogic.addFriend(userBean.getUsername(), paramUser);
		return "profile.xhtml?faces-redirect=true" + "&user=" + paramUser;
	}

	public String removeFriend() {
		FriendLogic.removeFriend(userBean.getUsername(), paramUser);
		return "profile.xhtml?faces-redirect=true" + "&user=" + paramUser;
	}

	public String getIsMyFriend() {
		if(paramUser == null) {
			return "OWN";
		}
		isMyFriend = FriendLogic.isFriend(userBean.getUsername(), paramUser);
		return isMyFriend;
	}

	public void setIsMyFriend(String isMyFriend) {
		this.isMyFriend = paramUser;
	}

	public List<String> getFriendList() {
		friendList = FriendLogic.getFriends(userBean.getUsername());
		return friendList;
	}
}