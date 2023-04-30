package es.deusto.supermarket.client;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import categories.PerformanceTest;
import es.deusto.spq.supermarket.server.Main;
import es.deusto.spq.supermarket.server.jdo.Usuario;


@Category(PerformanceTest.class)
public class VentanaLoadingTest {

	
	private HttpServer server;
	private WebTarget appTarget;
	WebTarget productTarget;
	private Usuario usuario = Mockito.mock(Usuario.class);
	private JTextField textField = Mockito.mock(JTextField.class);
	private JTextField textField_1 = Mockito.mock(JTextField.class);
	private JTextField textField_2 = Mockito.mock(JTextField.class);
	private JTextField textField_3 = Mockito.mock(JTextField.class);
	
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
	    	
	    	List<String> prod = Arrays.asList("Pera");
	    	WebTarget productElimTarget = appTarget.path("elimP");
	    	productElimTarget.request().post(Entity.entity(prod, MediaType.APPLICATION_JSON));
	    	
	        server.stop();
	    }
	@Test
	public void valor100Test() {
		JProgressBar bar = new JProgressBar();
		bar.setValue(100);
		assertEquals(100, bar.getValue());
	}

}