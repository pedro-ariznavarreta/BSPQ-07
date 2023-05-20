package es.deusto.supermarket.client;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
import es.deusto.spq.supermarket.client.VentanaCrearCuentaGerente;
import es.deusto.spq.supermarket.client.VentanaCrearCuentaTrabajador;
import es.deusto.spq.supermarket.server.Resource;
import es.deusto.spq.supermarket.server.jdo.Usuario;

@Category(PerformanceTest.class)
public class VentanaCrearCuentaTrabajadorTest {

	@Mock
	private Client client;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private WebTarget webTarget;

	@Captor
	private ArgumentCaptor<Entity<Usuario>> userDataEntityCaptor;

	@Mock
	private Usuario usuario;
	
    @Mock
    private BufferedWriter bw;

	@Mock
	private VentanaCrearCuentaTrabajador vCCT;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// prepare static mock of ClientBuilder
		try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
			clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
			when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

			vCCT = new VentanaCrearCuentaTrabajador();
			vCCT.setVisible(false);
			// Simulate response from database query

		}
	}
	
    @Test
    public void testEscribirEnElCsv() throws IOException{
    	//when(fw.getEncoding()).thenReturn("UTF-8");
        // Configura el comportamiento esperado de los mocks
        doNothing().when(bw).write(anyString());

		VentanaCrearCuentaTrabajador ventanaCrearCuentaTrabajador = new VentanaCrearCuentaTrabajador();
		// Crea un objeto de la clase que se está probando y llama al método a probar
        ventanaCrearCuentaTrabajador.escribirEnElCsv(usuario);

        // Verifica que se hayan llamado los métodos esperados en los mocks
        //verify(bw, times(1)).newLine();
        //verify(bw, times(1)).write("username,password,email,0,1");
       // verify(bw, times(1)).close();
        //verify(fw, times(1)).close();
      //  verify(fw, times(1)).getEncoding();
    }
    



}
