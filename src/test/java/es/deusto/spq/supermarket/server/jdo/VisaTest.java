package es.deusto.spq.supermarket.server.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VisaTest {
	
	private Visa v;
	
	@Before
	public void crearVisa() {
		v = new Visa(12345, "pedro", 333, "18/01/2023");
	}
	
	@Test
	public void getnTarjeta() {
		assertEquals(12345, v.getnTarjeta());
	}
	@Test
	public void getTitular() {
		assertEquals("pedro", v.getTitular());
	}
	@Test
	public void getCV() {
		assertEquals(333, v.getCv());
	}
	@Test
	public void getfCaducidad() {
		assertEquals("18/01/2023", v.getfCaducidad());
	}
	@Test
	public void setnTarjeta() {
		v.setnTarjeta(54321);
		assertEquals(54321, v.getnTarjeta());
	}
	@Test
	public void setTitular() {
		v.setTitular("javi");
		assertEquals("javi", v.getTitular());
	}
	@Test
	public void setCV() {
		v.setCv(111);
		assertEquals(111, v.getCv());
	}
	@Test
	public void setfCaducidad() {
		v.setfCaducidad("18/01/2023");
		assertEquals("18/01/2023", v.getfCaducidad());
	}

}
