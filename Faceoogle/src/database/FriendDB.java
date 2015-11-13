package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class FriendDB {

	public static void addFriend(User user, User friend) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUsername());
			User f = em.find(User.class, friend.getUsername());
			u.addFriend(f);
			em.merge(u);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
	}

	public static void removeFriend(User user, User friend) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			// WHY NO TRANSACTION HERE???????????????????????????? Dvs. em.getTransaction().begin();
			System.out.println("US3R: " + user + ", MY FRÄND: " + friend);
			User f = em.find(User.class, friend.getUsername());
			System.out.println("FULL NAME FRÄND: " + f.getName());
			em.remove(f);
			//em.flush(); // FEL HÄR!!!!!
			User u = em.find(User.class, user.getUsername());
			System.out.println("FULL NAME r3kt: " + u.getName());
			em.merge(u);
//			u.setFriends(null);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("WOWOWOWOWOWOWOWOWOWOOWOWOWOWOWOWOWOWOWOWOW: " + e);
			// javax.persistence.TransactionRequiredException: no transaction is in progress <----  SKRIVS UT!!!!!!!!!!!!!!!
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
	}

	public static List<User> getFriends(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<User> friends = new ArrayList<User>();
		try {
			TypedQuery<User> q = em.createQuery("select NEW model.User(f.username) "
					+ "from User u inner join u.friends f where u.username = ?1",User.class).setParameter(1,user.toString());
			friends = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();

		return friends;
	}
}