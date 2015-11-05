package beans;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="registerBean")
public class RegisterBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private Date birthday;
	private String gender;
	
	public Date getbirthday() {
		return birthday;
	}
	public void setbirthday(Date birthday) {
		this.birthday = birthday;
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
	
	public void register() {
		System.out.println(this.name + "\n" +
				this.birthday + "\n" + 
				this.gender +
				this.username + "\n");
	}
}