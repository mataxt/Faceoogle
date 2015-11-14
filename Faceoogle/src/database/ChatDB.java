package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Chat;

public class ChatDB {
	public static int addChat(Chat chat) {
		Integer chatId = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(chat);
			em.getTransaction().commit();
			chatId = chat.getId();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
		return chatId;
	}

//	public static boolean checkChat(Chat chat) {
//		boolean exists = false;
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
//		EntityManager em = emf.createEntityManager();
//		try {
//			String query = "from Chat where (chater = ?1 and chatee = ?2) or (chater = 2? and chatee =1?)";
//			if (em.createQuery(query, Chat.class).setParameter(1, chat.getChater()).setParameter(2, chat.getChatee())
//					.getSingleResult() != null) {
//				exists = true;
//			}
//		} catch (NoResultException e) {
//			return false;
//		} finally {
//			em.close();
//		}
//		emf.close();
//		return exists;
//	}

	public static List<Chat> getChatHistory(Chat chat) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Chat> history = new ArrayList<Chat>();
		try {
			history = em
					.createQuery(
							"from Chat where (chater = ?1 and chatee = ?2) or (chater = ?2 and chatee = ?1) order by timestamp asc",
							Chat.class)
					.setParameter(1, chat.getChater()).setParameter(2, chat.getChatee()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();

		return history;
	}

	public static void removeChat(Chat chat) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("delete from Chat where (chater = ?1 and chatee = ?2) or (chater = ?2 and chatee = ?1)")
					.setParameter(1, chat.getChater()).setParameter(2, chat.getChatee()).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
	}
}
