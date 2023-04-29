package es.deusto.spq.supermarket.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.List;


import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.spq.supermarket.pojo.DirectMessage;
import es.deusto.spq.supermarket.pojo.MessageData;
import es.deusto.spq.supermarket.pojo.UserData;
import es.deusto.spq.supermarket.server.jdo.Message;
import es.deusto.spq.supermarket.server.jdo.Producto;
import es.deusto.spq.supermarket.server.jdo.User;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.github.cliftonlabs.json_simple.JsonObject;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;
	private PersistenceManager pm = null;
	private Transaction tx = null;

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}



	@GET
	@Path("/usuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Usuario> devolverUsuarios() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Usuario> todosUsuarios = null;

		try {
			Query<Usuario> q = pm.newQuery(Usuario.class);
			todosUsuarios = q.executeList();
		} catch (Exception e) {

		} finally {
			pm.close();
		}
		return todosUsuarios;
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

			if (!usuariosl.isEmpty())
				usuarios = usuariosl.get(0);
		} catch (Exception e) {
			logger.info(e.getMessage());
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
		String trabajador = usuarioL.get(3);
		String gerente = usuarioL.get(4);
		int a = Integer.parseInt(trabajador);
		int b = Integer.parseInt(gerente);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Usuario usuario1 = new Usuario(nick, contraseña, email, a, b);
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
			logger.catching(e);
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
			logger.catching(e);
		} finally {
			pm.close();
		}
		return usuariousado;
	}

	@GET
	@Path("rol")
	@Produces(MediaType.APPLICATION_JSON)
	public static int rol(@QueryParam("nick") String nick) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		int valor = 0;

		try {
			Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nick + "'");

			List<Usuario> usuariosl = q.executeList();

			if (usuariosl.get(0).getTrabajador() == 1) {
				valor = 1;
			}
			if (usuariosl.get(0).getGerente() == 1) {
				valor = 2;
			}

		} catch (Exception e) {
			logger.catching(e);
		} finally {
			pm.close();
		}
		return valor;
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Usuario> getUsuarios() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Usuario> usuarios = null;

		try {
			Query<Usuario> q = pm.newQuery(Usuario.class);
			usuarios = q.executeList();
		} catch (Exception e) {

		} finally {
			pm.close();
		}
		return usuarios;
	}

	@GET
	@Path("/nomP")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Producto> getProductosNom(@QueryParam("nombre") String nombre) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Producto> productos = null;
		try {
			Query<Producto> q = pm.newQuery("SELECT FROM " + Producto.class.getName() + " WHERE nombre== '" + nombre + "'");

			productos = q.executeList();
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			pm.close();
		}

		return productos;
	}

	@GET
	@Path("/allP")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Producto> getProductos() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Producto> q = pm.newQuery(Producto.class);

		List<Producto> productos = q.executeList();

		pm.close();

		return productos;

	}
}
