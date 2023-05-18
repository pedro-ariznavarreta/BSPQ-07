/** @package es.deusto.spq.supermarket.server.jdo



    @brief Esto es del jdo pedido
*/
package es.deusto.spq.supermarket.server.jdo;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

/**
 * Clase PEDIDO en la que se encuentran los PRODUCTO s comprados por el USUARIO
 * @author Pedro
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Pedido {
	

	private String nombre;
	private Date fechaPago;
	private List<String> productos;
	private String direccion;
	
	/**
	 * Contructor de la clase PEDIDO
	 * @param nombre String con el nombre 
	 * @param fechaPago Date con la fecha de pago del PEDIDO
	 * @param productos Lista con los nombres de los PRODUCTO s comprados en el PEDIDO
	 * @param direccion String con la direccion de llegada del PEDIDO
	 */
	public Pedido(String nombre, Date fechaPago, List<String> productos, String direccion) {
		super();
		this.nombre = nombre;
		this.fechaPago = fechaPago;
		
		this.productos = productos;
		
		this.direccion = direccion;
	}
	/**
	 * Devuelve el nombre
	 * @return String con el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Permite cambiar el nombre 
	 * @param nombre String con el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Devuelve la fecha de pago del PEDIDO
	 * @return date con la fecha
	 */
	public Date getFechaPago() {
		return fechaPago;
	}
	/**
	 * Permite cambiar la fecha de pago del PEDIDO
	 * @param fechaPago date de la nueva fecha de pago
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * Devuelve la lista de PRODUCTO s comprados en el PEDIDO
	 * @return Lita con los productos
	 */
	public List<String> getProductos() {
		return productos;
	}
	/**
	 * Permite cambiar los PRODUCTO s comprados en el PEDIDO
	 * @param productos lista con los nombre de los nuevos productos
	 */
	public void setProductos(List<String> productos) {
		this.productos = productos;
	}
	/**
	 * Devuelve la direccion del PEDIDO
	 * @return String de la direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Permite cmabiar la direccion del evio del PEDIDO
	 * @param direccion String con la nueva direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * toString de la clase PEDIDO
	 */
	@Override
	public String toString() {
		return "Pedido [nombre=" + nombre + ", fechaPago=" + fechaPago + ", productos=" + productos + ", direccion="
				+ direccion + "]";
	}
	
	
	
	
	
}
