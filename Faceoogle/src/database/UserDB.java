package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.User;

public class UserDB {
	public static boolean addUser(User usr) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usr);
			em.getTransaction().commit();
			registered = true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			registered = false;
		} finally {
			em.close();
		}
		emf.close();
		return registered;
	}

	public static boolean checkUser(User usr) {
		boolean exists = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			String query = "from User where username = ?1 and password = ?2";
			if (em.createQuery(query, User.class).setParameter(1, usr.getUsername()).setParameter(2, usr.getPassword())
					.getSingleResult() != null) {
				exists = true;
			}
		} catch (NoResultException e) {
			return false;
		} finally {
			em.close();
		}
		emf.close();
	
		return exists;
	}

	public static void removeUser(User usr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("delete from User u where u= ?1").setParameter(1, usr).executeUpdate();
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

	public static List<User> searchUserName(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<User> users = new ArrayList<User>();
		try {
			users = em.createQuery("from User where username like CONCAT('%', :namesList, '%') order by username",
					User.class).setParameter("namesList",username).setMaxResults(5).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
		
		return users;
	}
}
