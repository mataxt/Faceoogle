package vm;

import java.sql.Date;

import model.User;

public class UserViewModel {
	private String username;
	private String name;
	private Date birthDate;
	private String gender;
	
	public UserViewModel(User user) {
		this.username = user.getUsername();
		this.name = user.getName();
		this.birthDate = user.getBirthDate();
		this.gender = user.getGender();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
