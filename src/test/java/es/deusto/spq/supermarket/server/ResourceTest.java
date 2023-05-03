package es.deusto.spq.supermarket.server;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.supermarket.server.jdo.Usuario;

public class ResourceTest {

    @Mock
    PersistenceManager persistenceManager;

    @Mock
    Transaction transaction;

    @InjectMocks
    Resource resource;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // initializing static mock object PersistenceManagerFactory
        try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
            PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
            jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
            
            when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
            when(persistenceManager.currentTransaction()).thenReturn(transaction);
        }
    }

    @Test
    public void testDevolverUsuarios() {
        // Crear los usuarios esperados
        Usuario usuario1 = new Usuario();
        usuario1.setUsername("Juan");
        Usuario usuario2 = new Usuario();
        usuario2.setUsername("Maria");
        List<Usuario> usuariosEsperados = Arrays.asList(usuario1, usuario2);

        // Configurar la respuesta falsa para la llamada a executeList()
        Query<Usuario> queryMock = mock(Query.class);
        when(queryMock.executeList()).thenReturn(usuariosEsperados);

        PersistenceManager persistenceManagerMock = mock(PersistenceManager.class);
        when(persistenceManagerMock.newQuery(Usuario.class)).thenReturn(queryMock);

        // Inyectar la dependencia mock en la clase bajo prueba
        Resource resource = new Resource();
      

        // Ejecutar la prueba y verificar el resultado
        List<Usuario> usuariosObtenidos = resource.devolverUsuarios();
        assertEquals(usuariosObtenidos.size(), usuariosObtenidos.size());
    }

}