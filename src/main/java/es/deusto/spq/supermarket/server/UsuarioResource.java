package es.deusto.spq.supermarket.server;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import es.deusto.spq.supermarket.server.jdo.*;

import javax.jdo.*;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Usuario> getUsuarios() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Usuario> usuarios = null;

		try {
			Query<Usuario> q = pm.newQuery(Usuario.class);
			usuarios = q.executeList();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		} finally {
			pm.close();
		}
		return usuarios;
	}

	@GET
	@Path("/nom")
	@Produces(MediaType.APPLICATION_JSON)
	public static Usuario getUsuariosLogin(@QueryParam("nick") String nick) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Usuario usuarios = null;

		try {
			Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nick + "'");

			List<Usuario> usuariosl = q.executeList();

			if(!usuariosl.isEmpty())
				usuarios = usuariosl.get(0);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		} finally {
			pm.close();
		}

		return usuarios;
	}

	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public static void insertarUsuario(List<String> usuarioL) {
		String nick = usuarioL.get(0);
		String contraseña = usuarioL.get(1);
		String email = usuarioL.get(2);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Usuario usuario1 = new Usuario(nick, contraseña,email);
			pm.makePersistent(usuario1);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}

	@POST
	@Path("/elim")
	@Produces(MediaType.APPLICATION_JSON)
	public static void eliminarUsuario(List<String> usuarioL) {
		String nick = usuarioL.get(0);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		try (Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nick + "'");) {
			List<Usuario> user = q.executeList();
			pm.deletePersistentAll(user);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		} finally {
			pm.close();
		}
	}

	@GET
	@Path("/nomcheck")
	@Produces(MediaType.APPLICATION_JSON)
	public static boolean nomcheck(@QueryParam("nick") String nick) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		boolean usuariousado = false;

		try {
			Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nick + "'");

			List<Usuario> usuariosl = q.executeList();

			if (usuariosl.isEmpty()) {
				usuariousado = false;
			} else {
				usuariousado = true;
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		} finally {
			pm.close();
		}
		return usuariousado;
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public static void modificarusuario(Usuario u) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		try (Query<Usuario> q = pm.newQuery("javax.jdo.query.SQL", "UPDATE usuario SET password= '" + u.getPassword() + "' ,email= " + u.getEmail() + " WHERE username= " + u.getUsername());) {
			q.execute();
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		} finally {
			pm.close();
		}
	}

}
