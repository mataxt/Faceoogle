package Tests.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.ChatDB;
import junit.framework.TestCase;
import model.Chat;

public class ChatTests extends TestCase {
	EntityManagerFactory emf;
	EntityManager em;

	@Override
	public void setUp() {
		try {
			super.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			emf = Persistence.createEntityManagerFactory("UserPU");
			em = emf.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Exception during JPA EntityManager instanciation.");
		}
	}

	@Override
	public void tearDown() {
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	public void testChatMethods() {
		try {
			Chat chat = new Chat("Chater", "Chatee", "Message");

			// Chat exists?
			chat.setId(ChatDB.addChat(chat));

			// All values correct?
			Chat result = em
					.createQuery(
							"from Chat where (chater = ?1 and chatee = ?2) or (chater = ?2 and chatee =?1) order by timestamp desc",
							Chat.class)
					.setParameter(1, chat.getChater()).setParameter(2, chat.getChatee()).getSingleResult();
			assertEquals(chat.getChater(), result.getChater());
			assertEquals(chat.getChatee(), result.getChatee());
			assertEquals(chat.getMessage(), result.getMessage());

			List<Chat> chatList = ChatDB.getChatHistory(chat);
			assertFalse("Fetching writer failed",chatList.isEmpty());
			assertEquals("Fetching chater failed",chat.getChater(), chatList.get(0).getChater());
			assertEquals("Fetching chatee failed",chat.getChatee(), chatList.get(0).getChatee());
			assertEquals("Fetching message failed",chat.getMessage(), chatList.get(0).getMessage());

			// Can remove?
			ChatDB.removeChat(chat);
			assertTrue("Remove Failer",ChatDB.getChatHistory(chat).isEmpty());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during chat method commit.");
		}
	}
}
