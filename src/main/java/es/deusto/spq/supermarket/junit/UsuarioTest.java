package es.deusto.spq.supermarket.junit;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.deusto.spq.supermarket.server.jdo.Usuario;

public class UsuarioTest {
	
	Usuario c1=new Usuario("Jon","jf", "jf",0,0);
	Usuario c2= new Usuario();

	@Test
	public void GetUsername() {
		assertEquals("Jon", c1.getUsername());
	}
	@Test
	public void SetUsername() {
		c2.setUsername("Ander");
		assertEquals("Ander", c2.getUsername());
	}
	@Test
	public void GetPassword() {
		assertEquals("jf", c1.getPassword());
	}
	@Test
	public void SetPassword() {
		c2.setPassword("jf");
		assertEquals("jf", c2.getPassword());
	}
	@Test
	public void GetEmail() {
		assertEquals("jf", c1.getEmail());
	}
	@Test
	public void SetEmail() {
		c2.setEmail("jf");
		assertEquals("jf", c2.getEmail());
	}
	@Test
	public void getTrabajador() {
		assertEquals(c1.getTrabajador(), 0);
	}
	@Test
	public void setTrabajador() {
		c2.setTrabajador(0);
		assertEquals(c2.getTrabajador(), 0);
	}
	@Test
	public void getGerente() {
		assertEquals(c1.getGerente(), 0);
	}
	@Test
	public void setGerente() {
		c2.setGerente(0);
		assertEquals(c2.getGerente(), 0);
	}
	

}

