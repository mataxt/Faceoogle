package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends", catalog = "faceoogle")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user;
	private String friend;

	public Friend() {}

	public Friend(String user, String friend) {
		this.user = user;
		this.friend = friend;
	}
	
	@Id
	@Column(name = "User", nullable = false)
	public String getuser() {
		return user;
	}

	public void setuser(String user) {
		this.user = user;
	}

	@Id
	@Column(name = "Friend", nullable = false)
	public String getfriend() {
		return friend;
	}

	public void setfriend(String friend) {
		this.friend = friend;
	}
	
	@Override
	public String toString() {
		return friend;
	}
}
