package model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users", catalog = "faceoogle")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private Date birthDate;
	private String gender;
	
	public User() {}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String name, Date birthDate, String gender) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
	}
	
	@Id
	@Column(name = "Username", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BirthDate", nullable = false)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "Gender", nullable = false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString(){
		return username;
	}
}
