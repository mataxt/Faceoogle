package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class FriendDB {

	public static boolean addFriend(User user, User friend) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean added = false;
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUsername());
			User f = em.find(User.class, friend.getUsername());
			u.addFriend(f);
			em.merge(u);
			em.flush();
			em.getTransaction().commit();
			added = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
		return added;
	}

	public static boolean removeFriend(User user, User friend) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean removed = false;
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUsername());
			User f = em.find(User.class, friend.getUsername());
			u.removeFriend(f);
			em.merge(u);
			em.flush();
			em.getTransaction().commit();
			removed = true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
		return removed;
	}


	@SuppressWarnings("unchecked")
	public static List<User> getFriends(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<User> friends = new ArrayList<User>();
		try {
			friends = em.createQuery("select u.friends from User u where u.username = ?1").setParameter(1,user.toString()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();

		return friends;
	}
}