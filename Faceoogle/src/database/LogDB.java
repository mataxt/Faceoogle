package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Log;

public class LogDB {
	public static List<Log> listUserLogs(String usrName) {
		List<Log> logs = new ArrayList<Log>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			String query = "from Log where receiver = ?1"; 
			logs = em.createQuery(query, Log.class).setParameter(1, usrName).getResultList();
		} finally {
			em.close();
		}
		emf.close();
		return logs;
	}
}
