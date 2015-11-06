package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class UserDB {
	public static void addUser(User usr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usr);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		emf.close();

	}

	public static boolean checkUser(User usr) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			String u = (String) em.createQuery("SELECT password FROM users as users WHERE users.username = ?1 ")
					.setParameter(1, usr.getUsername()).getSingleResult();
			System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
		return false;
	}
}
