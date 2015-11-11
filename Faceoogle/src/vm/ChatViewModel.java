package vm;

import model.Chat;

public class ChatViewModel {
	private String chater;
	private String chatee;
	private String message;
	
	public ChatViewModel(Chat chat) {
		this.chater = chat.getChater();
		this.chatee = chat.getChatee();
		this.message = chat.getMessage();
	}

	public String getChater() {
		return chater;
	}

	public String getChatee() {
		return chatee;
	}

	public String getMessage() {
		return message;
	}
}