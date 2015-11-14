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
@Table(name = "logs", catalog = "faceoogle")
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String writer;
	private String receiver;
	private String body;
	private String timesamp;

	public Log() {}

	public Log(String writer, String receiver, String body) {
		super();
		this.writer = writer;
		this.receiver = receiver;
		this.body = body;
	}

	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Body", nullable = false)
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "Writer", nullable = false)
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Column(name = "Receiver", nullable = false)
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "Timestamp", nullable = false)
	@Generated(GenerationTime.ALWAYS)
	public String getTimesamp() {
		return timesamp;
	}

	public void setTimesamp(String timesamp) {
		this.timesamp = timesamp;
	}
}
