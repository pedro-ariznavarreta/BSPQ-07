package es.deusto.supermarket.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import categories.PerformanceTest;
import es.deusto.spq.supermarket.client.MetodsClient;
import es.deusto.spq.supermarket.client.VentanaVerificarCodigo;
import es.deusto.spq.supermarket.server.jdo.Usuario;



public class VentanaVerificarCodigoTest {
    
    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Captor
    private ArgumentCaptor<Entity<Usuario>> usuarioDataEntityCaptor;
    
    private VentanaVerificarCodigo ventanaVerificarCodigo;
    
    @Before 
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            ventanaVerificarCodigo =new VentanaVerificarCodigo();
            ventanaVerificarCodigo.setVisible(false);
        }
    }
    
    
    @Test
    public void registerUserTest() {
        WebTarget mockedUserRegTarget = mock(WebTarget.class);
        when(webTarget.path("reg")).thenReturn(mockedUserRegTarget);
        when(mockedUserRegTarget.request()).thenReturn(builder);

        Response mockedResponse = mock(Response.class);
        when(builder.post(any(Entity.class))).thenReturn(mockedResponse);

        String username = "user1";
        String password = "password1";
        String email = "user1@example.com";
        int trabajador = 1;
        int gerente = 0;
        ventanaVerificarCodigo.crearCuenta(username, password, email, trabajador, gerente);

        verify(builder).post(any(Entity.class));
    }
}
