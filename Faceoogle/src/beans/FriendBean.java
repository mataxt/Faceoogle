package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "friendBean")
public class FriendBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String addFriend() {
		String user = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("user");
		System.out.println("\n\nADD HERE: " + user + " \n\n");
		return null;
	}

	public String removeFriend() {
		String user = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getParameter("user");
		System.out.println("\n\nREMOVE HERE: " + user + " \n\n");
		return null;
	}
	
	public boolean isFriend(String user){
		System.out.println("\n\nISFIREND HERE: " + user + " \n\n");
		return user.equals("1");
	}
}
