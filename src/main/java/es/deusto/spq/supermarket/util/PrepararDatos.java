package es.deusto.spq.supermarket.util;




import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.supermarket.server.jdo.Usuario;
import es.deusto.spq.supermarket.server.jdo.Product;


public class PrepararDatos {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Product prod1 = new Product("Lechuga", "Muy sana", 2.4, "unai", 6);
			pm.makePersistent(prod1);
			Product prod2 = new Product("Platano", "Deliciosa", 3, "pedro", 100);
			pm.makePersistent(prod2);
			Product prod3 = new Product("Mango", "Recien horneado", 0.6, "javi", 75);
			pm.makePersistent(prod3);
			Product prod4 = new Product("Tomate", "Recien cocinado", 0.9, "javi", 75);
			pm.makePersistent(prod4);
			Product prod5 = new Product("Pizza", "Recien cortada", 13, "unai", 75);
			pm.makePersistent(prod5);
			Product prod6 = new Product("Patatas", "Para freir", 1.1, "javi", 75);
			pm.makePersistent(prod6);

			Usuario usuario1 = new Usuario("pedro", "1234","pedro.ariznavarreta@opendeusto.es", 0, 0);
			pm.makePersistent(usuario1);
			Usuario usuario2 = new Usuario("admin", "admin","admin@opendeusto.es", 0, 1);
			pm.makePersistent(usuario2);
			Usuario usuario3 = new Usuario("A", "A","admin@opendeusto.es", 1, 0);
			pm.makePersistent(usuario3);
		
			
		
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}