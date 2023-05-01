package es.deusto.spq.supermarket.server;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.IntegrationTest;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

@Category(IntegrationTest.class)
public class ResourceTest {

	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget usuarioTarget;
	private Client c;

	@Before
	public void setUp() throws Exception {

		server = Main.startServer();
		Client c = ClientBuilder.newClient();

		appTarget = c.target(Main.BASE_URI);

	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testGetIt() {
		WebTarget productTarget = appTarget.path("resource");
		WebTarget productAllTarget = productTarget.path("allP");

		List<Product> listProd = Arrays.asList(new Product("Lechuga", "Muy sana", 2.4, "unai", 6),
				new Product("Platano", "Deliciosa", 3, "pedro", 100),
				new Product("Mango", "Recien horneado", 0.6, "javi", 75));

		GenericType<List<Product>> genericType = new GenericType<List<Product>>() {
		};
		List<Product> productos = productAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals(listProd.get(0).getNombre(), productos.get(0).getNombre());
		assertEquals(listProd.get(1).getNombre(), productos.get(1).getNombre());
		assertEquals(listProd.get(2).getNombre(), productos.get(2).getNombre());
	}

	@Test
	public void testgetNombreProductos() {

		WebTarget productTarget = appTarget.path("resource");
		WebTarget productNomTarget = productTarget.path("nomP").queryParam("nombre", "Lechuga");

		List<Product> listProd = Arrays.asList(new Product("Lechuga", "Muy sana", 2.4, "unai", 6),
				new Product("Platano", "Deliciosa", 3, "pedro", 100),
				new Product("Mango", "Recien horneado", 0.6, "javi", 75));

		GenericType<List<Product>> genericType = new GenericType<List<Product>>() {
		};
		List<Product> producto = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals(listProd.get(0).getNombre(), producto.get(0).getNombre());
	}

	@Test
	public void testgetUsuarios() {

		usuarioTarget = appTarget.path("resource");
		WebTarget userAllTarget = usuarioTarget.path("all");

		Usuario listUsuarios = new Usuario("Admin","Admin","admin@admin.com",0,1);

		GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
		};
		List<Usuario> usuarios = userAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals(listUsuarios.getUsername(), usuarios.get(5).getUsername());

	}

}
