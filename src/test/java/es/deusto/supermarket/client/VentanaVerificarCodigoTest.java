package es.deusto.supermarket.client;

import static org.mockito.Mockito.when;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
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
import es.deusto.spq.supermarket.client.VentanaVerificarCodigo;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VentanaVerificarCodigoTest {

    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    private VentanaVerificarCodigo vB;
    
    private Invocation.Builder invocationBuilder;
    
    @Mock
    private Response response;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // prepare static mock of ClientBuilder
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource/reg")).thenReturn(webTarget);

            vB = new VentanaVerificarCodigo();
        }
    }

    @Test
    public void testCrearCuenta() {
    	 // Configuramos el comportamiento del WebTarget
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(invocationBuilder);

        // Configuramos el comportamiento del Invocation.Builder
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        // Creamos los datos de entrada para el método
        String usuario = "usuario";
        String contraseña = "contraseña";
        String mail = "mail";
        Integer trabajador = 1;
        Integer gerente = 2;
        List<String> usuarioL = new ArrayList<>();
        usuarioL.add(usuario);
        usuarioL.add(contraseña);
        usuarioL.add(mail);
        usuarioL.add(String.valueOf(trabajador));
        usuarioL.add(String.valueOf(gerente));

        // Ejecutamos el método
        vB.crearCuenta(usuario, contraseña, mail, trabajador, gerente);

        // Verificamos que se haya llamado al método post() con los parámetros correctos
        verify(invocationBuilder).post(eq(Entity.entity(usuarioL, MediaType.APPLICATION_JSON)));
    }





}


    