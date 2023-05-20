/** @package es.deusto.spq.supermarket.server.jdo
 *     @brief Esto es del jdo compra
*/
 
package es.deusto.spq.supermarket.server.jdo;

import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Compra {
	private List<Product> productos;
	private String usuario;
	private String fecha;
	
	public Compra(List<Product> productosCliente, String usu, String fec) {
		this.productos = productosCliente;
		this.usuario = usu;
		this.fecha = fec;	
	}

	public List<Product> getProductos() {
		return productos;
	}

	public void setProductos(List<Product> productos) {
		this.productos = productos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
