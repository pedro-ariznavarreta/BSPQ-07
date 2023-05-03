package es.deusto.supermarket.client;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.swing.JProgressBar;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.Loading;
import es.deusto.spq.supermarket.server.jdo.Usuario;



public class VentanaLoadingTest {
	 @Mock
	    private Client client;

	    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
	    private WebTarget webTarget;

	    @Captor
	    private ArgumentCaptor<Entity<Usuario>> userDataEntityCaptor;

	    @Mock
	    private Usuario us;
	    
	    @Mock
	    private Loading vT;
	    
	    @Before
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);

	        // prepare static mock of ClientBuilder
	        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
	            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
	            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

	            vT = new Loading();

	            // Simulate response from database query
	            
	        }
	    }
	    
	    @Test
		public void test() {
			try {
				vT = new Loading();
			}catch(Exception e) {
				assertTrue(false);
			}
		}
		@Test
		public void valor100Test() {
			JProgressBar bar = new JProgressBar();
			bar.setValue(100);
			assertEquals(100, bar.getValue());
		}

}