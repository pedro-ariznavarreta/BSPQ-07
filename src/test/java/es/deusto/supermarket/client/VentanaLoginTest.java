package es.deusto.supermarket.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

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
import es.deusto.spq.supermarket.client.VentanaBusqueda;
import es.deusto.spq.supermarket.client.VentanaLogin;
import es.deusto.spq.supermarket.server.jdo.Usuario;




public class VentanaLoginTest {

    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Captor
    private ArgumentCaptor<Entity<Usuario>> userDataEntityCaptor;

    @Mock
    private Usuario us;
   
    @Mock
    private VentanaLogin vL;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // prepare static mock of ClientBuilder
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            vL = new VentanaLogin();

            // Simulate response from database query
            
        }
    }
    
    @Test
    public void testLoginWithValidUserAndPassword() {
        String usuario = "usuario";
        String contraseña = "contraseña";
        Usuario usu = new Usuario("usuario", "contraseña", "contraseña", 0, 0);
        when(us.getPassword()).thenReturn("contraseña");
        
        when(webTarget.path("nom").queryParam("nick", "usuario")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<Usuario>() {}))
                .thenReturn(us);

        boolean resultado = vL.login(usuario, contraseña);

        assertTrue(resultado);
    }

    @Test
    public void testLoginWithInvalidUser() {
        String usuario = "";
        String contraseña = "contraseña";
        
        when(webTarget.path("nom").queryParam("nick", "usuario")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<Usuario>() {}))
                .thenReturn(us);

        boolean resultado = vL.login(usuario, contraseña);

        assertFalse(resultado);
    }

    @Test
    public void testLoginWithInvalidPassword() {
        String usuario = "usuario";
        String contraseña = "";
        
        when(webTarget.path("nom").queryParam("nick", "usuario")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<Usuario>() {}))
                .thenReturn(us);

        boolean resultado = vL.login(usuario, contraseña);

        assertFalse(resultado);
    }



}


	
