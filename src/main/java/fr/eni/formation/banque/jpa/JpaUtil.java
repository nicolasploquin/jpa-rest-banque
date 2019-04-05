package fr.eni.formation.banque.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static EntityManagerFactory factory;
	private static final ThreadLocal<EntityManager> thread = new ThreadLocal<>();
	
	static {
		System.out.println("Initialisation du persistence unit : localhost-mysql-banque-jpa.");
		factory = Persistence.createEntityManagerFactory("localhost-mysql-banque-jpa");
	}

	/**
	 * Création ou récupération en mémoire d'une instance de l'entity manager JPA.
	 * @return entity manager JPA
	 */
	public static EntityManager getEM() {
		EntityManager em = thread.get();
		if(em == null || !em.isOpen()){
			em = factory.createEntityManager();
			thread.set(em);
			System.out.println("Création de l'entity manager JPA.");
		}
		return em;
	}
	
	public static void close() {
		factory.close();
	}

//	@Override
//	protected void finalize() throws Throwable {
//		close();
//	}

}
