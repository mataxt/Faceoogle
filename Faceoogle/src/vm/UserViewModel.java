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

	public String getName() {
		return name;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public String getGender() {
		return gender;
	}
}
