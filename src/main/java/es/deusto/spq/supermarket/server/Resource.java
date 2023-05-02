package es.deusto.spq.supermarket.server;

import javax.jdo.PersistenceManager;


import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.List;


import javax.jdo.JDOHelper;
import javax.jdo.Transaction;


import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Producto;

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

//	@POST
//	@Path("/sayMessage")
//	public Response sayMessage(DirectMessage directMessage) {
//		User user = null;
//		try {
//			tx.begin();
//			logger.info("Creating query ...");
//
//			try (Query<?> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \""
//					+ directMessage.getUserData().getLogin() + "\" &&  password == \""
//					+ directMessage.getUserData().getPassword() + "\"")) {
//				q.setUnique(true);
//				user = (User) q.execute();
//
//				logger.info("User retrieved: {}", user);
//				if (user != null) {
//					Message message = new Message(directMessage.getMessageData().getMessage());
//					user.getMessages().add(message);
//					pm.makePersistent(user);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			tx.commit();
//		} finally {
//			if (tx.isActive()) {
//				tx.rollback();
//			}
//		}
//
//		if (user != null) {
//			cont++;
//			logger.info(" * Client number: {}", cont);
//			MessageData messageData = new MessageData();
//			messageData.setMessage(directMessage.getMessageData().getMessage());
//			return Response.ok(messageData).build();
//		} else {
//			return Response.status(Status.BAD_REQUEST)
//					.entity("Login details supplied for message delivery are not correct").build();
//		}
//	}
//
//	@POST
//	@Path("/register")
//	public Response registerUser(UserData userData) {
//		try {
//			tx.begin();
//			logger.info("Checking whether the user already exits or not: '{}'", userData.getLogin());
//			User user = null;
//			try {
//				user = pm.getObjectById(User.class, userData.getLogin());
//			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
//				logger.info("Exception launched: {}", jonfe.getMessage());
//			}
//			logger.info("User: {}", user);
//			if (user != null) {
//				logger.info("Setting password user: {}", user);
//				user.setPassword(userData.getPassword());
//				logger.info("Password set user: {}", user);
//			} else {
//				logger.info("Creating user: {}", user);
//				user = new User(userData.getLogin(), userData.getPassword(), userData.getEsTrabajador(),
//						userData.getEsAdministrador());
//				pm.makePersistent(user);
//				logger.info("User created: {}", user);
//			}
//			tx.commit();
//			return Response.ok().build();
//		} finally {
//			if (tx.isActive()) {
//				tx.rollback();
//			}
//
//		}
//	}

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
		//sUsuario Inigo = new Usuario("Inigo","Susilla","aaaa",0,1);
		try {
			
			Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nick + "'");

			List<Usuario> usuariosl = q.executeList();
			if (!usuariosl.isEmpty()) {
				usuarios = usuariosl.get(0);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			pm.close();
		}
		
		return usuarios;
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public static Usuario getLogin(@QueryParam("nick") String nick) { //Login para trabajadores y gerentes
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Usuario usuarios = null;
		try {
			Query<Usuario> q = pm.newQuery("SELECT FROM " + Usuario.class.getName() + " WHERE username== '" + nick + "'");
			
			List<Usuario> usuariosl = q.executeList();
			if (!usuariosl.isEmpty()) {
				usuarios = usuariosl.get(0);
			}
			q.close();
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
	@Path("/regGerente")
	@Consumes(MediaType.APPLICATION_JSON)
	public static void insertarGerente(List<String> usuarioG) {
		String nick = usuarioG.get(0);
		String contraseña = usuarioG.get(1);
		String email = usuarioG.get(2);
		int a =0;
		int b = 1;
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
	@Path("/regTrabajador")
	@Consumes(MediaType.APPLICATION_JSON)
	public static void insertarTrabajador(List<String> usuarioT) {
		String nick = usuarioT.get(0);
		String contraseña = usuarioT.get(1);
		String email = usuarioT.get(2);
		int a = 1;
		int b = 0;
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
	@Path("/regProductos")
	@Consumes(MediaType.APPLICATION_JSON)
	public static void insertarProductos(List<String> productos) {
		String nom = productos.get(0);
		String cod = productos.get(1);
		String desc = productos.get(2);
		String precio = productos.get(3);
		String cant = productos.get(4);
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Producto producto1 = new Producto(nom, cod, desc, precio, cant);
			pm.makePersistent(producto1);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

	}
		
	//Devuelve los productos en una lista
	@GET
	@Path("/productos")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Producto> devolverProductos() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Producto> todosProductos = null;

		try {
			Query<Producto> q = pm.newQuery(Producto.class);
			todosProductos = q.executeList();
		} catch (Exception e) {

		} finally {
			pm.close();
		}
		return todosProductos;
	}

	@POST
	@Path("elim")
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
	
	//Eliminar productos de la base de datos ~
	@POST
	@Path("elimProductos")
	@Produces(MediaType.APPLICATION_JSON)
	public static void eliminarProducto(List<String> producto) {
		//String nick = producto.get(0);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		try (Query<Producto> q = pm.newQuery("DELETE * FROM " + Producto.class.getName());) {
			List<Producto> user = q.executeList();
			pm.deletePersistentAll(user);
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			pm.close();
		}
	}

	@GET
	@Path("nomcheck")
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
	@Path("codcheck")
	@Produces(MediaType.APPLICATION_JSON)
	public static boolean codcheck(@QueryParam("nick") String nick) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		boolean usuariousado = false;

		try {
			Query<Usuario> q = pm.newQuery("SELECT FROM " + Producto.class.getName() + " WHERE cod== '" + nick + "'");

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
	public static List<Product> getProductosNom(@QueryParam("nombre") String nombre) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		List<Product> productos = null;
		try {
			Query<Product> q = pm.newQuery("SELECT FROM " + Product.class.getName() + " WHERE nombre== '" + nombre + "'");

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
	public static List<Product> getProductos() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		Query<Product> q = pm.newQuery(Product.class);

		List<Product> productos = q.executeList();

		pm.close();

		return productos;

	}
}
