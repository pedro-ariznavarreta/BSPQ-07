package es.deusto.supermarket.client;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.VentanaBusqueda;
import es.deusto.spq.supermarket.server.Main;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

@Category(PerformanceTest.class)
public class VentanaBusquedaTest {
	
	private HttpServer server;
	private WebTarget appTarget;
	private Usuario usuarioMock;
	private Product productoMock;
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
        
        // create mocks for Usuario and Product
        usuarioMock = mock(Usuario.class);
        productoMock = mock(Product.class);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

	@Test
	public void testBusquedaProd() {
		// specify mock behavior for Producto
		when(productoMock.getNombre()).thenReturn("Platano");
		when(productoMock.getDescripcion()).thenReturn("Deliciosa");
		when(productoMock.getPrecio()).thenReturn(3.0);
		when(productoMock.getUsuario()).thenReturn("pedro");
		when(productoMock.getCantidad()).thenReturn(100);
		
		// specify mock behavior for Usuario
		
		
		// create VentanaBusqueda with mocked Usuario
		VentanaBusqueda vent = new VentanaBusqueda(usuarioMock);
		
		// perform search for "Platano"
		List<Product> productos = vent.busquedaProd("Platano");
		
		// verify that the expected Product is returned
		assertEquals("Platano", productos.get(0).getNombre());
	}
}