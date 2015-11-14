package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "chat", catalog = "faceoogle")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String chater;
	private String chatee;
	private String message;
	private String timesamp;

	public Chat() {}

	public Chat(String chater, String chatee) {
		this.chater = chater;
		this.chatee = chatee;
	}
	public Chat(String chater, String chatee, String message) {
		this.chater = chater;
		this.chatee = chatee;
		this.message = message;
	} 
	
	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Chater", nullable = false)
	public String getChater() {
		return chater;
	}

	public void setChater(String chater) {
		this.chater = chater;
	}

	@Column(name = "Chatee", nullable = false)
	public String getChatee() {
		return chatee;
	}

	public void setChatee(String chatee) {
		this.chatee = chatee;
	}
	
	@Column(name = "Message", nullable = false)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name = "Timestamp", nullable = false)
	@Generated(GenerationTime.ALWAYS)
	public String getTimesamp() {
		return timesamp;
	}

	public void setTimesamp(String timesamp) {
		this.timesamp = timesamp;
	}
	
	@Override
	public String toString(){
		return message;
	}
	

}