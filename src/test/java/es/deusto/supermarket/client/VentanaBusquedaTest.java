package es.deusto.supermarket.client;

import static org.junit.Assert.assertEquals;


import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.VentanaBusqueda;
import es.deusto.spq.supermarket.server.Main;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;




@Category(PerformanceTest.class)
public class VentanaBusquedaTest {
	
	private HttpServer server;
	private WebTarget appTarget;
	private Usuario usuario = Mockito.mock(Usuario.class);
	private Product producto = Mockito.mock(Product.class);
	private static int cantidad;

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
	public void testBusquedaProd() {
		
		List<Product> listProd = Arrays.asList(
    			new Product("Platano", "Deliciosa", 3, "pedro", 100));
		Usuario us = new Usuario();
		VentanaBusqueda vent = new VentanaBusqueda(us);
		List<Product> productos = vent.busquedaProd("Platano");
		
		assertEquals(listProd.get(0).getNombre(), productos.get(0).getNombre());
		
	}
	

}