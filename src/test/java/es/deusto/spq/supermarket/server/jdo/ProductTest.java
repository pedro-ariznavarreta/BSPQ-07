package es.deusto.spq.supermarket.server.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProductTest {

    @Test
    public void testConstructorAndGetters() {
        String nombre = "Manzana";
        String descripcion = "Manzana fresca";
        double precio = 1.5;
        String usuario = "usuario1";
        int cantidad = 10;

        Product producto = new Product(nombre, descripcion, precio, usuario, cantidad);

        assertEquals(nombre, producto.getNombre());
        assertEquals(descripcion, producto.getDescripcion());
        assertEquals(precio, producto.getPrecio(), 0.001);
        assertEquals(usuario, producto.getUsuario());
        assertEquals(cantidad, producto.getCantidad());
    }

    @Test
    public void testSetters() {
        String nombre = "Manzana";
        String descripcion = "Manzana fresca";
        double precio = 1.5;
        String usuario = "usuario1";
        int cantidad = 10;

        Product producto = new Product(nombre, descripcion, precio, usuario, cantidad);

        String nuevoNombre = "Pera";
        producto.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, producto.getNombre());

        String nuevaDescripcion = "Pera fresca";
        producto.setDescripcion(nuevaDescripcion);
        assertEquals(nuevaDescripcion, producto.getDescripcion());

        double nuevoPrecio = 2.0;
        producto.setPrecio(nuevoPrecio);
        assertEquals(nuevoPrecio, producto.getPrecio(), 0.001);

        String nuevoUsuario = "usuario2";
        producto.setUsuario(nuevoUsuario);
        assertEquals(nuevoUsuario, producto.getUsuario());

        int nuevaCantidad = 5;
        producto.setCantidad(nuevaCantidad);
        assertEquals(nuevaCantidad, producto.getCantidad());
    }

    @Test
    public void testToString() {
        String nombre = "Manzana";
        String descripcion = "Manzana fresca";
        double precio = 1.5;
        String usuario = "usuario1";
        int cantidad = 10;

        Product producto = new Product(nombre, descripcion, precio, usuario, cantidad);

        String expected = "Producto [codigo=null, nombre=Manzana, descripcion=Manzana fresca, precio=1.5, usuario=usuario1, cantidad=10]";
        assertEquals(expected, producto.toString());
    }
}
