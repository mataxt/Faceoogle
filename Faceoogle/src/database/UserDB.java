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
		boolean exists = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			String query = "from User where username = ?1 and password = ?2"; 
			if (em.createQuery(query, User.class).setParameter(1, usr.getUsername()).setParameter(2, usr.getPassword()).getSingleResult() != null ){
				exists = true;
			} 
		} finally {
			em.close();
		}
		emf.close();
		return exists;
	}
}
