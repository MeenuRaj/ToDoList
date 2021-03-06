
package customTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Tlist;

public class DBUtil {
private static final EntityManagerFactory emf = 
Persistence.createEntityManagerFactory("ToDoList");
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
	
	
	public static void update(Tlist prod) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(prod);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
}