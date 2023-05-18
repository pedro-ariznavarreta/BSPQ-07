package es.deusto.spq.supermarket.server.jdo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class CompraTest {

    private Compra compra;
    private List<Product> productos;

    @Before
    public void setUp() {
        Product product1 = new Product();
        product1.setCodigo("001");
        product1.setNombre("Product 1");
        product1.setDescripcion("Description 1");
        product1.setPrecio(100.0);
        product1.setUsuario("User1");
        product1.setCantidad(1);

        Product product2 = new Product();
        product2.setCodigo("002");
        product2.setNombre("Product 2");
        product2.setDescripcion("Description 2");
        product2.setPrecio(200.0);
        product2.setUsuario("User2");
        product2.setCantidad(2);

        productos = Arrays.asList(product1, product2);
        compra = new Compra(productos, "TestUser", "18-05-2023");
    }

    @Test
    public void testGetProductos() {
        assertEquals(productos, compra.getProductos());
    }

    @Test
    public void testSetProductos() {
        Product product3 = new Product();
        product3.setCodigo("003");
        product3.setNombre("Product 3");
        product3.setDescripcion("Description 3");
        product3.setPrecio(300.0);
        product3.setUsuario("User3");
        product3.setCantidad(3);

        List<Product> newProductos = Arrays.asList(product3);
        compra.setProductos(newProductos);
        assertEquals(newProductos, compra.getProductos());
    }

    @Test
    public void testGetUsuario() {
        assertEquals("TestUser", compra.getUsuario());
    }

    @Test
    public void testSetUsuario() {
        compra.setUsuario("NuevoUser");
        assertEquals("NuevoUser", compra.getUsuario());
    }

    @Test
    public void testGetFecha() {
        assertEquals("18-05-2023", compra.getFecha());
    }

    @Test
    public void testSetFecha() {
        compra.setFecha("20-05-2023");
        assertEquals("20-05-2023", compra.getFecha());
    }
}
