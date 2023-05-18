package es.deusto.spq.supermarket.server.jdo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CestaTest {

private Cesta c;
	
	@Before
	public void crearProducto() {
		c = new Cesta("Lechuga", new Date(121, 10, 1), "javi");
	}
	
	@Test
	public void testGetNombreProducto() {
		assertEquals("Lechuga", c.getNombreproducto());
	}
	
	@Test
	public void testGetFechaExpiracion() {
		assertEquals(new Date(121, 10, 1), c.getFechaExpiracion());
	}
	
	@Test
	public void testGetNombreUsuario() {
		assertEquals("javi", c.getNombreUsuario());
	}
	
	@Test
	public void testSetNombreProducto() {
		c.setNombreproducto("Manzana");
		assertEquals("Manzana", c.getNombreproducto());
	}
	
	@Test
	public void testSetFechaExpiracion() {
		c.setFechaExpiracion(new Date(120, 10, 1));
		assertEquals(new Date(120, 10, 1), c.getFechaExpiracion());
	}
	
	@Test
	public void testSetNombreUsuario() {
		c.setNombreUsuario("inigo");
		assertEquals("inigo", c.getNombreUsuario());
	}

}
