package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Friend;

public class FriendDB {
	
	public static void addFriend(Friend frd) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(frd);
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
	
	public static void removeFriend(Friend frd) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("delete from Friend f where f = ?1").setParameter(1, frd).executeUpdate();
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

	public static List<Friend> getFriends(String usr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Friend> friends = new ArrayList<Friend>();
		try {
			friends = em.createQuery("from Friend f where f.user = ?1",
					Friend.class).setParameter(1,usr).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
		
		return friends;
	}
}
