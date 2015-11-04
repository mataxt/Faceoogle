package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private Date birthDate;
	private String gender;
	private Set<User> friends;

	@Id
	@Column(name = "Username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BirthDate")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "Gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "friends", joinColumns = {@JoinColumn(name = "username", nullable = false, updatable = false) }, 
			inverseJoinColumns = {@JoinColumn(name = "username", nullable = false, updatable = false) }
			)
	public Set<User> getFriends() {
		return this.friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
}
