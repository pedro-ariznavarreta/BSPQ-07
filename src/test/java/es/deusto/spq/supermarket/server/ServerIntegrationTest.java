package es.deusto.spq.supermarket.server;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import categories.IntegrationTest;
import es.deusto.spq.supermarket.server.Main;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

@Category(IntegrationTest.class)
public class ServerIntegrationTest {

	private static final PersistenceManagerFactory pmf = JDOHelper
			.getPersistenceManagerFactory("datanucleus.properties");

	private HttpServer server;
	private WebTarget appTarget;
	private Client c;

	@Before
	public void setUp() throws Exception {

		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();
		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());
		appTarget = c.target(Main.BASE_URI).path("resource");
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test

	public void testGetUsuarios() {

		WebTarget userAllTarget = appTarget.path("all");

		List<Usuario> listUsuarios = Arrays
				.asList(new Usuario("pedro", "1234", "pedro.ariznavarreta@opendeusto.es", 0, 0));

		GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
		};
		List<Usuario> usuarios = userAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals("admin", usuarios.get(0).getUsername());

	}

	@Test

	public void testEliminarUsuario() {
		List<String> listuser = Arrays.asList("pedro", "1234");
		WebTarget userElimTarget = appTarget.path("elim");
		userElimTarget.request().post(Entity.entity(listuser, MediaType.APPLICATION_JSON));

		WebTarget userNomTarget = appTarget.path("nom").queryParam("nick", "pedro");
		GenericType<Usuario> genericType = new GenericType<Usuario>() {
		};
		Usuario usuario = userNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals(null, usuario);

	}

	@Test

	public void testNomCheck() {
		WebTarget userNomCheckTarget = appTarget.path("nomcheck").queryParam("nick", "pedro");

		GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
		};
		boolean usuario = userNomCheckTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<Boolean>() {
		});

		assertEquals(false, usuario);
	}
}