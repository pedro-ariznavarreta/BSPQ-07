package es.deusto.supermarket.client;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.mockito.Mockito;

import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.VentanaVerificarCodigo;
import es.deusto.spq.supermarket.server.Main;
import es.deusto.spq.supermarket.server.jdo.Usuario;

@Category(PerformanceTest.class)
public class VentanaVerificarCodigoTest {

	private HttpServer server;
	private WebTarget appTarget;
	private WebTarget usuarioTarget;
	
	 @Before
	    public void setUp() throws Exception {
	        // start the server
	        server = Main.startServer();
	        // create the client
	        Client c = ClientBuilder.newClient();
	        // uncomment the following line if you want to enable
	        // support for JSON in the client (you also have to uncomment
	        // dependency on jersey-media-json module in pom.xml and Main.startServer())
	        // --
	        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

	        appTarget = c.target(Main.BASE_URI);
	    }

	    @After
	    public void tearDown() throws Exception {
	    	
	    	List<String> user = Arrays.asList("pedro");
	    	WebTarget userElimTarget = appTarget.path("elim");
	    	userElimTarget.request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
	    	
	        server.stop();
	    }
		@Test
		public void testCrearCuenta() {
			
//			VentanaVerificarCodigo v = new VentanaVerificarCodigo();
//			v.crearCuenta("pedro", "1234","pedro.ariznavarreta@opendeusto.es", 0, 0);
//			
//			 
//			WebTarget usuarioNomTarget = appTarget.path("nom").queryParam("nick", "pedro");
//			GenericType<Usuario> genericType = new GenericType<Usuario>() {
//			};
//			Usuario u = usuarioNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
//			
//			assertEquals("pedro", u.getUsername());

		}

}
