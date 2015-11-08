package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class FriendDB {
//	public static boolean addFriend(User usr) {
//		boolean registered = false;
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
//		EntityManager em = emf.createEntityManager();
//		try {
//			em.getTransaction().begin();
//			em.persist(usr);
//			em.getTransaction().commit();
//			registered = true;
//		} catch (Exception e) {
//			if (em.getTransaction().isActive()) {
//				em.getTransaction().rollback();
//			}
//			registered = false;
//		} finally {
//			em.close();
//		}
//		emf.close();
//		return registered;
//	}
	public static List<User> getFriends(String usr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<User> friends = new ArrayList<User>();
		try {
			friends = em.createQuery("from User u join u.friends f where u.username = ?1",
					User.class).setParameter(1,usr).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();

		return friends;
	}
}
