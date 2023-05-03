package es.deusto.spq.supermarket.server.jdo;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductoTest {

	private Producto p;
	
	
	
	@Before
	public void crearProducto() {
		p = new Producto("Platano", "423k4j2oj2", "De Canarias", "11", "23");
		
	}
	
	@Test
	public void testGetNom() {
		assertEquals("Platano", p.getnom());
	}
	
	@Test
	public void testGetCod() {
		assertEquals("423k4j2oj2", p.getcod());
	}
	
	@Test
	public void testGetDesc() {
		assertEquals("De Canarias", p.getdesc());
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals("11", p.getPrecio());
	}
	
	@Test
	public void testGetCantidad() {
		assertEquals("23", p.getCant());
	}

	
	@Test
	public void testSetNombre() {
		p.setnom("Melo");
		assertEquals("Melo", p.getnom());
	}
	
	@Test
	public void testSetCod() {
		p.setcod("4304mr434j");
		assertEquals("4304mr434j", p.getcod());
	}
	
	@Test
	public void testSetDesc() {
		p.setdesc("De Malaga");
		assertEquals("De Malaga", p.getdesc());
	}
	
	@Test
	public void testSetPrecio() {
		p.setPrecio("12");
		assertEquals("12", p.getPrecio());
	}
	
	@Test
	public void testSetCantidad() {
		p.setCant("10");
		assertEquals("10", p.getCant());
	}


	@Test
	public void testComrpobarProductosDifrentes() {
	Producto p1 = new Producto("Leche", "001", "Leche entera", "1.20", "10");
	Producto p2 = new Producto("Queso", "002", "Queso manchego", "2.50", "5");
	assertNotEquals(p1, p2);
	}
	
}
