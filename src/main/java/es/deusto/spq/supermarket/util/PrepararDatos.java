package es.deusto.spq.supermarket.util;




import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.spq.supermarket.server.jdo.Usuario;
import es.deusto.spq.supermarket.server.jdo.Producto;


public class PrepararDatos {

	public static void main(String[] args) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Producto prod1 = new Producto("Lechuga", "Muy sana", 2.4, "unai", 6);
			pm.makePersistent(prod1);
			Producto prod2 = new Producto("Platano", "Deliciosa", 3, "pedro", 100);
			pm.makePersistent(prod2);
			Producto prod3 = new Producto("Mango", "Recien horneado", 0.6, "javi", 75);
			pm.makePersistent(prod3);
			Producto prod4 = new Producto("Tomate", "Recien cocinado", 0.9, "javi", 75);
			pm.makePersistent(prod4);
			Producto prod5 = new Producto("Pizza", "Recien cortada", 13, "unai", 75);
			pm.makePersistent(prod5);
			Producto prod6 = new Producto("Patatas", "Para freir", 1.1, "javi", 75);
			pm.makePersistent(prod6);

			Usuario usuario1 = new Usuario("pedro", "1234","pedro.ariznavarreta@opendeusto.es", 0, 0);
			pm.makePersistent(usuario1);
		
			
		
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
}