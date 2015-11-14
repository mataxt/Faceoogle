package logic;

import java.util.ArrayList;
import java.util.List;

import database.ChatDB;
import model.Chat;
import vm.ChatViewModel;

public class ChatLogic {

	public static Integer sendMessage(String chater, String chatee, String message) {
		Chat chat = new Chat(chater, chatee, message);
		return ChatDB.addChat(chat);
	}

	public static List<ChatViewModel> getChatHistory(String chater, String chatee) {
		Chat chat = new Chat(chater, chatee);
		List<Chat> originalChat = new ArrayList<Chat>();
		List<ChatViewModel> chatvm = new ArrayList<ChatViewModel>();
		
		originalChat = ChatDB.getChatHistory(chat);
		originalChat.forEach(c -> c.setMessage(c.getMessage().replaceAll("(.{60})", "$1\n")));
		originalChat.forEach(c -> chatvm.add(new ChatViewModel(c)));
		return chatvm;
	}
}