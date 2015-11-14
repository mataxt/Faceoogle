package Tests.logic;

import java.util.List;

import junit.framework.TestCase;
import logic.ChatLogic;
import vm.ChatViewModel;

public class ChatTests extends TestCase {
	@Override
	public void setUp() {
		try {
			super.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testChatMethods(){
		String chater = "Tester";
		String chatee = "Testee";
		String message = "Test";
		
		//Send chat message
		Integer id = ChatLogic.sendMessage(chater, chatee, message);
		assertFalse("Send chat message failed", id.equals(null));
		
		//Get chat history
		List<ChatViewModel> chatList = ChatLogic.getChatHistory(chater, chatee);
		assertFalse("Chat not listed", chatList.isEmpty());
		assertEquals("Get chater failed", chater, chatList.get(0).getChater());
		assertEquals("Get chatee failed", chatee, chatList.get(0).getChatee());
		assertEquals("Get message failed", message, chatList.get(0).getMessage());
	}
}
