package es.deusto.spq.supermarket.server;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import categories.IntegrationTest;
import es.deusto.spq.supermarket.server.jdo.Usuario;



public class ResourceTest 
{
	private Resource resource;
	
	@Mock
	private PersistenceManager persistenceManager;

	@Mock
	private Transaction transaction;
	 
	@Before
	public void setUp() {
		 MockitoAnnotations.openMocks(this);
		 
		 try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
			 PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
	         jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
	            
	         when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
	         when(persistenceManager.currentTransaction()).thenReturn(transaction);

	         resource = new Resource();
	      }
	}

		    @Test
		    public void testGetUsuariosLogin() {
//		        String nick = "usuario";
//		        Usuario usuario = new Usuario("usuario", "contraseña", "contraseña", 0, 0);
//
//		        Query<Usuario> query = mock(Query.class);
//		        when(persistenceManager.newQuery(Usuario.class, "username == nick")).thenReturn(query);
//		        when(query.execute(nick)).thenReturn(Collections.singletonList(usuario));
//
//		        Usuario result = resource.getUsuariosLogin(nick);
//
//		        assertEquals(usuario, result);
		    
		    
	
}
	
	
	
}