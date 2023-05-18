package es.deusto.spq.supermarket.server.jdo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

public class PedidoTest {
	
private Pedido p;
	
	@Before
    public void crearPedido() {
		List<String> productos = new ArrayList<String>();
		productos.add("manzana");
		productos.add("pera");
        p = new Pedido("comida", new Date(121, 10, 1), productos, "Universidad de Desuto");
    }
	
	@Test
	public void getNombreTest() {
		assertEquals("comida", p.getNombre());
	}
	@Test
	public void getFechaPagoTest() {
		assertEquals(new Date(121, 10, 1), p.getFechaPago());
	}
	@Test
	public void getProductosTest() {
		assertEquals("manzana", p.getProductos().get(0));
		assertEquals("pera", p.getProductos().get(1));
	}
	@Test
	public void getDireccionTest() {
		assertEquals("Universidad de Desuto", p.getDireccion());
	}
	@Test
	public void setNombreTest() {
		p.setNombre("fruta");
		assertEquals("fruta", p.getNombre());
	}
	@Test
	public void setFechaPagoTest() {
		p.setFechaPago(new Date(120, 10, 1));
		assertEquals(new Date(120, 10, 1), p.getFechaPago());
	}
	@Test
	public void setProductosTest() {
		List<String> productos2 = new ArrayList<String>();
		productos2.add("fresas");
		productos2.add("naranjas");
		p.setProductos(productos2);
		assertEquals("fresas", p.getProductos().get(0));
		assertEquals("naranjas", p.getProductos().get(1));
	}
	@Test
	public void setDireccionTest() {
		p.setDireccion("Universidad de Leioa");
		assertEquals("Universidad de Leioa", p.getDireccion());
	}

}
