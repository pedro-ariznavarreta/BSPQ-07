package es.deusto.spq.supermarket.server;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.swing.JProgressBar;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.VentanaBusqueda;
import es.deusto.spq.supermarket.client.VentanaLogin;
import es.deusto.spq.supermarket.client.VentanaVerificarCodigo;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;



@Category(PerformanceTest.class)
public class ServerPerformanceTest {

	
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

        appTarget = c.target(Main.BASE_URI).path("resource");

    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }
    
    

	@Test
	public void testBusquedaProd() {
		
		List<Product> listProd = Arrays.asList(
    			new Product("Lechuga", "Muy sana", 2.4, "unai", 6));
		Usuario us = new Usuario();
		VentanaBusqueda vent = new VentanaBusqueda(us);
		List<Product> productos = vent.busquedaProd("Lechuga");
		
		assertEquals(listProd.get(0).getNombre(), productos.get(0).getNombre());
		
	}
	
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 300)
	public void testAñadir() {
		
		
		
		when(usuario.getUsername()).thenReturn("unai");
		String nombreusuario = usuario.getUsername();
		when(producto.getNombre()).thenReturn("Manzana");
		String nombreproducto = producto.getNombre();

		WebTarget anadirTarget = appTarget.path("anadir").queryParam("Producto", nombreproducto).queryParam("Usuario", nombreusuario);
		GenericType<Boolean> genericType5 = new GenericType<Boolean>() {
		};
		boolean respuesta = anadirTarget.request(MediaType.APPLICATION_JSON).get(genericType5);
		
		assertEquals(true, respuesta);
	}
	
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 300)
	public void testContar() {
		
		WebTarget contarTarget = appTarget.path("contar").queryParam("Usuario", usuario.getUsername());
		GenericType<Integer> genericType7 = new GenericType<Integer>() {
		};
		cantidad = contarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
		
		assertEquals(0, cantidad);
	}
	@Test
	public void valor100Test() {
		JProgressBar bar = new JProgressBar();
		bar.setValue(100);
		assertEquals(100, bar.getValue());
	}
	
	@Test
	public void testLogin() {
		
//		WebTarget userTarget = appTarget.path("usuarios");
//	    WebTarget userAllTarget = appTarget.path("all");
//		
//		List<Usuario> usuario1 = Arrays.asList(
//    			new Usuario("inigo", "1234", "ini",0,0));
//		VentanaLogin vent = new VentanaLogin();
//		boolean result = vent.login("inigo", "1234");
//		boolean comp = false;
//		WebTarget userNomTarget = userTarget.path("nom").queryParam("nick", usuario1.get(0).getUsername());
//		GenericType<Usuario> genericType = new GenericType<Usuario>() {};
//		Usuario usuarios = userNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
//		if(usuarios.getPassword().equals(usuario1.get(0).getPassword())) {
//			comp = true;
//		}
//		
//		assertEquals(result, comp);
//		
 
    
    
}
	@Test
	@PerfTest(invocations = 1000, threads = 20)
    @Required(max = 1200, average = 300)
	public void testCrearCuenta() {
		
		VentanaVerificarCodigo v = new VentanaVerificarCodigo();
		v.crearCuenta("thom", "1234", "thom@gmail.com",0,0);
		
		 
		WebTarget usuarioNomTarget = appTarget.path("nom").queryParam("nick", "thom");
		GenericType<Usuario> genericType = new GenericType<Usuario>() {
		};
		Usuario u = usuarioNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		assertEquals("thom", u.getUsername());

	}
	
	
	
	
	
	
}
