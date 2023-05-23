package es.deusto.spq.supermarket.server;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import categories.PerformanceTest;

import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;



@Category(PerformanceTest.class)
public class ServerPerformanceTest {
	
	private static HttpServer server;
	private WebTarget appTarget;
	
	private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	@Rule 
	public JUnitPerfRule perfTestRule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));
	
	
	
	@BeforeClass
    public static void prepareTests() throws Exception {
        // start the server
        server = Main.startServer();
        

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();

            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
	}
	
    @Before
    public void setUp() throws Exception {
   
        Client c = ClientBuilder.newClient();
        appTarget = c.target(Main.BASE_URI).path("resource");
        
     
    }

    @AfterClass
    public static void tearDown() throws Exception {
    	 server.shutdown();

         PersistenceManager pm = pmf.getPersistenceManager();
         Transaction tx = pm.currentTransaction();
         try {
             tx.begin();
           
             tx.commit();
         } finally {
             if (tx.isActive()) {
                 tx.rollback();
             }
             pm.close();
         }
    }
    
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testGetUsuarios() {
		WebTarget userAllTarget = appTarget.path("all");
		GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
		};
		List<Usuario> usuarios = userAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals("admin", usuarios.get(0).getUsername());

	}

	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testEliminarUsuario() {
		List<String> listuser = Arrays.asList("A", "A");
		WebTarget userElimTarget = appTarget.path("elim");
		userElimTarget.request().post(Entity.entity(listuser, MediaType.APPLICATION_JSON));

		WebTarget userNomTarget = appTarget.path("nom").queryParam("nick", "A");
		GenericType<Usuario> genericType = new GenericType<Usuario>() {
		};
		Usuario usuario = userNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		assertEquals(null, usuario);

	}

	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testNomCheck() {
		WebTarget userNomCheckTarget = appTarget.path("nomcheck").queryParam("nick", "pedro");

		GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {
		};
		boolean usuario = userNomCheckTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<Boolean>() {
		});

		assertEquals(true, usuario);
	}
	
	
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testGetIt() {
	    WebTarget productAllTarget = appTarget.path("allP");
		
		List<Product> listProd = Arrays.asList(
				new Product("Lechuga", "Muy sana", 2.4, "unai",6),
				new Product("Platano", "Deliciosa", 3, "pedro",100),
				new Product("Mango", "Recien horneado", 0.6, "javi",75));

		GenericType<List<Product>> genericType = new GenericType<List<Product>>() {};
		List<Product> productos = productAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
	    assertEquals(listProd.get(0).getNombre(), productos.get(0).getNombre());
	    assertEquals(listProd.get(1).getNombre(), productos.get(1).getNombre());
	    assertEquals(listProd.get(2).getNombre(), productos.get(2).getNombre());
	}
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testgetNombreProductos() {
	    WebTarget productNomTarget = appTarget.path("nomP").queryParam("nombre", "Lechuga");
	    
	    List<Product> listProd = Arrays.asList(
				new Product("Lechuga", "Muy sana", 2.4, "unai",6),
				new Product("Platano", "Deliciosa", 3, "pedro",100),
				new Product("Mango", "Recien horneado", 0.6, "javi",75));
	    
	    GenericType<List<Product>> genericType = new GenericType<List<Product>>() {};
	    List<Product> producto = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    
	    assertEquals(listProd.get(0).getNombre(), producto.get(0).getNombre());
	}


	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testAñadirProductoCesta() {
		Product p = new Product("peras", "muy dulces", 0.5, "unai", 10);
		WebTarget cestaAñadirTarget = appTarget.path("anadir").queryParam("Producto", p.getNombre()).queryParam("Usuario", "pedro");
		
		GenericType<Boolean> genericType5 = new GenericType<Boolean>() {};
		boolean respuesta = cestaAñadirTarget.request(MediaType.APPLICATION_JSON).get(genericType5);
		
		assertEquals(true, respuesta);
		
	}


	@Test
	@JUnitPerfTest(threads = 10, durationMs = 5000)
	@JUnitPerfTestRequirement(meanLatency = 5000)
	public void testVaciarCesta() {
		WebTarget cestaBorrarTarget = appTarget.path("borrar");
		Usuario usuario = new Usuario("pedro", "1234", null, 0, 0);
		cestaBorrarTarget.request().post(Entity.entity(usuario, MediaType.APPLICATION_JSON));
		
		WebTarget cestaBuscarTarget = appTarget.path("buscar").queryParam("Usuario", "admin");
		GenericType<List<Product>> genericType = new GenericType<List<Product>>() {};
		List<Product> producto = cestaBuscarTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		
		assertTrue(producto.isEmpty());
		
	}	
	
	
	
	
	
}
