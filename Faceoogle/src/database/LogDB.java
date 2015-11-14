package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Log;
import model.User;

public class LogDB {
	public static Integer addLog(Log log) {
		Integer logId = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(log);
			em.getTransaction().commit();
			logId = log.getId();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}
		emf.close();
		return logId;
	}

	public static List<Log> listUserLogs(String usrName) {
		List<Log> logs = new ArrayList<Log>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			String query = "from Log where receiver = ?1 order by timestamp desc";
			logs = em.createQuery(query, Log.class).setParameter(1, usrName).getResultList();
		} finally {
			em.close();
			emf.close();
		}

		return logs;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Log> listUserFeed(User user) {
		List<Log> logs = new ArrayList<Log>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			List<User> friends = em.createQuery("select u.friends from User u where u.username = ?1 ")
					.setParameter(1, user.getUsername()).getResultList();
			List<String> friendNames = new ArrayList<String>();
			friends.forEach(f -> friendNames.add(f.toString()));
			String query = "from Log where receiver in (:friends) or receiver = ?1 order by timestamp desc";
			logs = em.createQuery(query, Log.class).setParameter(1, user.getUsername())
					.setParameter("friends", friendNames).getResultList();
		} finally {
			em.close();
			emf.close();
		}

		return logs;
	}

	public static void removeLog(Log log) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			em.createQuery("delete from Log l where l= ?1").setParameter(1, log).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
			emf.close();
		}
	}
}
