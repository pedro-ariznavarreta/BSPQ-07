package es.deusto.supermarket.client;

import static org.junit.Assert.assertEquals;


import java.util.Arrays;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
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
import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.VentanaLogin;
import es.deusto.spq.supermarket.server.Main;
import es.deusto.spq.supermarket.server.jdo.Usuario;




@Category(PerformanceTest.class)
public class VentanaLoginTest {
	
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
        server.stop();
    }
    
	@Test
	public void testLogin() {
		
		 usuarioTarget = appTarget.path("resource");
		 WebTarget userAllTarget = usuarioTarget.path("all");
		 
		List<Usuario> usuario1 = Arrays.asList(
    			new Usuario("Admin","Admin","admin@admin.com",0,1));
		VentanaLogin vent = new VentanaLogin();
		boolean result = vent.login("pedro", "1234");
		boolean comp = false;
		WebTarget userNomTarget = usuarioTarget.path("nom").queryParam("nick", usuario1.get(0).getUsername());
		GenericType<Usuario> genericType = new GenericType<Usuario>() {
		};
		Usuario usuarios = userNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		if(usuarios.getPassword().equals(usuario1.get(0).getPassword())) {
			comp = true;
		}
		
		assertEquals(result, comp);
		
	}
}