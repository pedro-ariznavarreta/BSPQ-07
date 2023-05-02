package es.deusto.spq.supermarket.server.jdo;



import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductoTest {

	private Product p;
	
	
	
	@Before
	public void crearProducto() {
		p = new Product("Platano", "Deliciosa", 3, "pedro", 100);
		
	}
	@Test
	public void testConstructor() {
	Producto p = new Producto("Leche", "001", "Leche entera", "1.20", "10");
	assert p.getnom().equals("Leche");
	assert p.getcod().equals("001");
	assert p.getdesc().equals("Leche entera");
	assert p.getPrecio().equals("1.20");
	assert p.getCant().equals("10");
	}
	@Test
	public void testGetNombre() {
		assertEquals("Platano", p.getNombre());
	}
	
	@Test
	public void testGetDescipcion() {
		assertEquals("Deliciosa", p.getDescripcion());
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(3, p.getPrecio(), 0.001);
	}
	
	@Test
	public void testGetUsuario() {
		assertEquals("pedro", p.getUsuario());
	}
	
	@Test
	public void testGetCantidad() {
		assertEquals(100, p.getCantidad());
	}

	
	@Test
	public void testSetNombre() {
		p.setNombre("Melo");
		assertEquals("Melo", p.getNombre());
	}
	
	@Test
	public void testSetDescipcion() {
		p.setDescripcion("Rica");
		assertEquals("Rica", p.getDescripcion());
	}
	
	@Test
	public void testSetPrecio() {
		p.setPrecio(2.6);
		assertEquals(2.6, p.getPrecio(), 0.001);
	}
	
	@Test
	public void testSetUsuario() {
		p.setUsuario("javi");
		assertEquals("javi", p.getUsuario());
	}
	
	@Test
	public void testSetCantidad() {
		p.setCantidad(10);
		assertEquals(10, p.getCantidad());
	}


	@Test
	public void testComrpobarProductosDifrentes() {
	Producto p1 = new Producto("Leche", "001", "Leche entera", "1.20", "10");
	Producto p2 = new Producto("Queso", "002", "Queso manchego", "2.50", "5");
	assert !p1.equals(p2);
	}
	
}
