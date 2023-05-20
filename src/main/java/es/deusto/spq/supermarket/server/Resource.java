/** @package es.deusto.spq.supermarket.server


@brief Esto es PARA CREAR todos los métdoos de la Base de datos por medio de etiquetas
*/

package es.deusto.spq.supermarket.server;

import javax.jdo.PersistenceManager;


import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.spq.supermarket.server.jdo.Cesta;
import es.deusto.spq.supermarket.server.jdo.Compra;
import es.deusto.spq.supermarket.server.jdo.Pedido;
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
/**
 * Clase cada etiqueta sirve para un método diferente de la bd
 * @author Pedro
 *
 */
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
	/**
	 * Metodo de la bd
	 * @return Devuelve una lista con todos los usuarios
	 */

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
	/**
	 * Busca los usuarios por su nombr
	 * @return Devuelve el usuario encotrado
	 */
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
	/**
	 * Hace login de los usuarios
	 * @return Devuelve los usuarios loggeados
	 */
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
	
	/**
	 * Registra los usuarios con sus atributos especificos
	 * 
	 */
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
	
	/**
	 * Registra el gerente con sus atributos especificos
	 * 
	 */
	
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
	
	/**
	 * Registra el trabajador con sus atributos especificos
	 * 
	 */
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
	
	/**
	 * Registra los productos con sus atributos especificos
	 * 
	 */
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
		
	/**
	 * Metodo de la bd
	 * @return Devuelve una lista con todos los productos en una lista
	 */
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
	
	/**
	 * Elimina el usuario especificamente por su nombre
	 * 
	 */
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
	
	/**
	 * Elimina todos los productos
	 * 
	 */
	@POST
	@Path("/borrarProducto")
	@Consumes(MediaType.APPLICATION_JSON)
	public static List<Product> borrarProducto(@QueryParam("cod") String codigo) {
		System.out.println("Entra en el método " + codigo);
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try (Query<Product> q = pm.newQuery("SELECT FROM " + Producto.class.getName() + " WHERE cod == '" + codigo + "'")) {
			tx.begin();
			List<Product> prod = q.executeList();
			System.out.println(prod.get(0));

			pm.deletePersistentAll(prod);
			tx.commit();
			System.out.println("PRODUCTO BORRADO");
			//logger.info("PRODUCTO BORRADO");
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return null;
	}
	/**
	 * Comprueba si existe o no por el nombre el usuario de la BD
	 * @return Devuelve el usuario usado de la BD
	 */
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
	
	/**
	 * Comprueba el rol del usuario si es cliente, gerente o trabajador
	 * @return Devuelve el usuario idoneo
	 */
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
	/**
	 *Metodo de la BD
	 * @return Devuelve toda la lista de usuarios
	 */
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
	/**
	 *Busca el producto por su nombre
	 * @return Devuelve el producto
	 */
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
	/**
	 *Metodo de la BD
	 * @return Devuelve todos los productos
	 */
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
	/**
	 *Cuenta los productos de la cesta
	 * @return Devuelve un contador con los productos
	 */
	@GET
	@Path("/contar")
	@Produces(MediaType.APPLICATION_JSON)
	public static int contarPoductos(@QueryParam("Usuario") String usuario) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		int contador = 0;

		try (Query<Cesta> q = pm
				.newQuery("SELECT FROM " + Cesta.class.getName() + " WHERE NombreUsuario == '" + usuario + "'")) {
			List<Cesta> cestav = q.executeList();

			for (Cesta cesta : cestav) {
				contador = contador + 1;
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			pm.close();
		}
		return contador;
	}
	
	/**
	 *Añade todos los productos a los usuarios especificos
	 * @return Devuelve un booleano 
	 */

	@GET
	@Path("/anadir")
	@Produces(MediaType.APPLICATION_JSON)
	public static boolean anadirProductoCesta(@QueryParam("Producto") String producto,
		@QueryParam("Usuario") String usuario) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean respuesta = false;

		Cesta cesta = new Cesta(producto, null, usuario);
		try {
			tx.begin();
			logger.info("Guardando objeto: " + cesta);
			pm.makePersistent(cesta);
			tx.commit();
			respuesta = true;

		} catch (Exception ex) {
			logger.info(ex.getMessage());
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return respuesta;
	}
	/**
	 *Hace una busqueda del usuario por su nombre y elimina ese usuario
	 * 
	 */

	@POST
	@Path("/borrar")
	@Consumes(MediaType.APPLICATION_JSON)
	public static void vaciarCesta(Usuario usuario) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();

		try (Query<Cesta> q = pm.newQuery(
				"SELECT FROM " + Cesta.class.getName() + " WHERE NombreUsuario == '" + usuario.getUsername() + "'")) {
			List<Cesta> cestav = q.executeList();
			logger.info("Cesta borrada");

			pm.deletePersistentAll(cestav);
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			pm.close();
		}
	}
	/**
	 *Hace una busqueda del usuario y del producto por su nombre
	 * @return Devuelve un booleano 
	 */

	@GET
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Product> verProductosCesta(@QueryParam("Usuario") String usuario) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		ArrayList<Product> productos = new ArrayList<>();

		try {
			Query<Cesta> q = pm
					.newQuery("SELECT FROM " + Cesta.class.getName() + " WHERE NombreUsuario == '" + usuario + "'");

			List<Cesta> cestav = q.executeList();

			for (Cesta cesta : cestav) {

				Query<Product> qq = pm.newQuery(
						"SELECT FROM " + Product.class.getName() + " WHERE nombre== '" + cesta.getNombreproducto() + "'");

				List<Product> productosr = qq.executeList();

				productos.add(productosr.get(0));

			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			pm.close();
		}
		return productos;
	}
	/**
	 *Añade el pedido a la compra
	 * 
	 */
	 
	  	@POST
	  	@Path("/anyadir")
	  	@Produces(MediaType.APPLICATION_JSON)
		public static void anadirPedido(List<String> pedidoL) throws Exception {
	  		System.out.println("Guardando");
	  		String nombre = pedidoL.get(0);
	  		String fechaPago = pedidoL.get(1);
	  		String direccion = pedidoL.get(2);
	  		List<String> productos = new ArrayList<String>();
	  		for(int i = 3; i < pedidoL.size(); i++) {
	  			productos.add(pedidoL.get(i));
	  			System.out.println(productos);
	  		}
	  		
	  		Date fecha = new SimpleDateFormat("yyy-MM-dd").parse(fechaPago);
	  		System.out.println("Guardando 2");
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			
			try {
				tx.begin();
				Pedido p = new Pedido(nombre, fecha, productos, direccion);
				System.out.println(p);
				pm.makePersistent(p);
				tx.commit();
			} catch(Exception e){
				logger.info(e.getMessage());
			}finally {
				pm.close();	
			}
		}
	  	
	  	/**
		 *Hace el metodo de compra de la BD
		 * 
		 */

		@POST
		@Path("/comprar")
		@Consumes(MediaType.APPLICATION_JSON)
		public static void comprarProductos(Usuario usuario) {

			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Product producto = new Product();


			List<Cesta> cestav = null;
			try (Query<Cesta> cesta = pm.newQuery("SELECT FROM "+Cesta.class.getName()+" WHERE NombreUsuario == '" + usuario.getUsername() + "'")) {
				cestav = cesta.executeList();
			} catch (Exception e) {
				logger.info(e.getMessage());
			} 
			
			for (Cesta cesv : cestav) {
				try (Query qq = pm.newQuery("javax.jdo.query.SQL", "UPDATE producto SET CANTIDAD = CANTIDAD - 1   WHERE Nombre = '" + producto.getNombre() + "'")) {
					producto.setNombre(cesv.getNombreproducto());
					qq.execute();
				} catch (Exception e) {
					logger.info(e.getMessage());
				} 
				
			}
		}
		/**
		 *Se registran las compras en la BD
		 * 
		 */
		@POST
		@Path("/regCompra")
		@Consumes(MediaType.APPLICATION_JSON)
		public static void guardarCompra(Compra compra) {

			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			if(compra.getProductos().size() < 1) {
				System.out.println("No funciona");
				System.out.println(compra.getUsuario());
				System.out.println(compra.getProductos());
				System.out.println(compra.getFecha());
			}else {
				try {
				tx.begin();
				Compra com = new Compra(compra.getProductos(), compra.getUsuario(),compra.getFecha());
				DateFormat fecha= new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String fechaBuena = fecha.format(Long.parseLong(compra.getFecha()));
				compra.setFecha(fechaBuena);
				System.out.println("El usuario " + com.getUsuario()+ " ha comprado:");
				pm.makePersistent(com);
				tx.commit();
			}finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}

		}
		}
		
		/**
		 *
		 * @return Devuelve una lista de todas las compras
		 */
		@GET
		@Path("/listarCompra")
		@Produces(MediaType.APPLICATION_JSON)
		public static List<Compra> verCompra( String usuario) {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			List<Compra> todaLaCompra = null; 
			try {
				Query<Compra> q = pm.newQuery("SELECT FROM " + Compra.class.getName());
				//Query<Compra> q = pm.newQuery(Compra.class);
				todaLaCompra = q.executeList();
				
				
				/*for (int i = 0; i < todaLaCompra.size(); i++) {
					System.out.println(todaLaCompra.get(i).getProductos());
				}*/
			}finally {
				
				pm.close();
			}
			
			return todaLaCompra;
		}
		
		@POST
		@Path("/vaciarCompra")
		public static void borrarTodasCompras() {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			List<Compra> todaLaCompra = null;
			try {
				tx.begin();
				Query<Compra> q = pm.newQuery("SELECT FROM " + Compra.class.getName());
				todaLaCompra =  q.executeList();
				pm.deletePersistent(todaLaCompra);
				tx.commit();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}

		}
		
		
		
}
