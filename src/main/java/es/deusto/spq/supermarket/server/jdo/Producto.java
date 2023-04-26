package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Producto {

	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private String codigo;
	private String nombre;
	private String descripcion;
	private double precio;
	private String usuario;
	private int cantidad;

	public Producto() {

	}

	/**
	 * 
	 * @param nombre      Nombre del Producto
	 * @param descripcion Descripcion del Producto
	 * @param precio      Precio del Producto
	 * @param usuario     Usuario que vende el Producto
	 * @param cantidad    Cantidad en stock del Producto
	 */
	public Producto(String nombre, String descripcion, double precio, String usuario, int cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.usuario = usuario;
		this.cantidad = cantidad;
	}

	/**
	 * Devuelve el bombre del Usuario que vende el Producto
	 * 
	 * @return String con el nombre del Usuario vendedor
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Cambia el nombre del Usuario que vende el Producto
	 * 
	 * @param usuario String con el nombre del Usuario nuevo a introducir
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Devuelve el codigo del Producto
	 * 
	 * @return String con el codigo del Producto
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Cambia el codigo del Producto
	 * 
	 * @param codigo String del codigo del Producto a introducir
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Devuelve el nombre del Producto
	 * 
	 * @return String con el nombre del Producto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del Producto
	 * 
	 * @param nombre String del nombre del Producto a introducir
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la descripcion del Producto
	 * 
	 * @return String con la descripcion del Producto
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Cambia la descripcion del Producto
	 * 
	 * @param descripcion String de la descripcion del Producto a introducir
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve el precio del Producto
	 * 
	 * @return double con el precio del Producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Cambia el precio del Producto
	 * 
	 * @param precio double del precio del Producto a introducir
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve la cantidad de stock que hay del Producto
	 * 
	 * @return int con la cantidad del Producto
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Cambia la cantidad del Producto
	 * 
	 * @param cantidad int de la cantidad del Producto a introducir
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * To String de Producto
	 */

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
				+ precio + ", usuario=" + usuario + ", cantidad=" + cantidad + "]";
	}
}
