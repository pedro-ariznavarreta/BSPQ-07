package es.deusto.supermarket.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.deusto.spq.supermarket.client.VentanaCesta;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class VentanaCestaTest {
    
    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Mock
    private Usuario usuario;

    private VentanaCesta ventanaCesta;
    
    @Before 
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // prepare static mock of ClientBuilder
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);
            //ventanaCesta = new VentanaCesta(usuario);
        }
    }

    @Test
    public void testBtnVaciarCestaActionListener() {
//        when(webTarget.path("borrar")).thenReturn(webTarget);
//        when(webTarget.path("buscar")).thenReturn(webTarget);
//        when(webTarget.queryParam(eq("Usuario"), eq(usuario.getUsername()))).thenReturn(webTarget);
//
//        // Crear un producto, añadirlo a una lista y devolver la lista cuando se solicita
//        Product product = mock(Product.class);
//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//        when(response.readEntity(Mockito.<GenericType<List<Product>>>any())).thenReturn(productList);
//
//        // Simular la acción de presionar el botón
//        ventanaCesta.btnVaciarCesta.doClick();
//
//        // Verificar las interacciones
//        verify(builder, times(1)).post(any(Entity.class));
//        verify(builder, times(1)).get();
    }
}
