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
	
	public String getReceiver() {
		return receiver;
	}
	
	public String getBody() {
		return body;
	}
}