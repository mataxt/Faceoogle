package beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import logic.UserLogic;

@SessionScoped
@ManagedBean(name = "userBean")
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private Date birthdate;
	private String gender;
	private String searchName;
	
	public java.util.Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(java.util.Date birthdate) {
		this.birthdate = new java.sql.Date(birthdate.getTime());
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String register() {
		if (UserLogic.addUser(username, password, name, birthdate, gender)) {
			return "index.xhtml";
		} else {
			return "register.xhtml";
		}
	}

	public String login() {
		if (UserLogic.login(username, password)) {
			return "index.xhtml";
		} else {
			return "login.xhtml";
		}
	}

	public ArrayList<String> getNames() {
		return UserLogic.getUserNamesByName(searchName);
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
