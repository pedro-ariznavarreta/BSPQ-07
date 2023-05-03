package es.deusto.supermarket.client;

import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.supermarket.client.VentanaAñadirProductoG;
import es.deusto.spq.supermarket.client.VentanaBusqueda;
import es.deusto.spq.supermarket.server.jdo.Usuario;

public class VentanaAnadirProductoGTest {

    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Captor
    private ArgumentCaptor<Entity<Usuario>> userDataEntityCaptor;

    @Mock
    private Usuario us;
   

	@Mock
	VentanaAñadirProductoG vr;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // prepare static mock of ClientBuilder
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            vr = new VentanaAñadirProductoG();
        }
    }

	
	@Test
	public void test() {
		try {
			vr = new VentanaAñadirProductoG();
		}catch(Exception e) {
			assertTrue(false);
		}
	}
}

