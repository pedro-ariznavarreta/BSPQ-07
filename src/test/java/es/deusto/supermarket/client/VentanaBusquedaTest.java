package es.deusto.supermarket.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import categories.PerformanceTest;

import es.deusto.spq.supermarket.client.VentanaBusqueda;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;


public class VentanaBusquedaTest {

	

	    @Mock
	    private Client client;

	    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
	    private WebTarget webTarget;

	    @Captor
	    private ArgumentCaptor<Entity<Usuario>> userDataEntityCaptor;

	    @Mock
	    private Usuario us;
	   

	    private VentanaBusqueda vB;

	    @Before
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);

	        // prepare static mock of ClientBuilder
	        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
	            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
	            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

	            vB = new VentanaBusqueda(us);
	        }
	    }


    @Test
    public void busquedaProdTest() {
        String producto = "Leche";
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("Leche Pascual", "Lácteos", 1.20, "viva", 0));
        expected.add(new Product("Leche Celta", "Lácteos", 1.10, "r", 0));

        when(webTarget.path("nomP").queryParam("nombre", producto)
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Product>>() {}))
                        .thenReturn(expected);

        List<Product> actual = vB.busquedaProd(producto);

        assertEquals(expected, actual);
        
        //Busqueda de producto inexisistente
        
        verify(webTarget.path("nomP").queryParam("nombre", producto)
                .request(MediaType.APPLICATION_JSON)).get(new GenericType<List<Product>>() {});
        
        when(webTarget.path("nomP").queryParam("nombre", "inexistente")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<Product>>() {}))
                        .thenReturn(Collections.emptyList());

        actual = vB.busquedaProd("inexistente");

        assertEquals(Collections.emptyList(), actual);

        verify(webTarget.path("nomP").queryParam("nombre", "inexistente")
                .request(MediaType.APPLICATION_JSON)).get(new GenericType<List<Product>>() {});
    }
}
	
