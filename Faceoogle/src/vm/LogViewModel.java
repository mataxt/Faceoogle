package vm;

import model.Log;

public class LogViewModel {
	private String writer;
	private String receiver;
	private String body;
	
	public LogViewModel(Log log) {
		this.writer = log.getWriter();
		this.receiver = log.getReceiver();
		this.body = log.getBody();
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
