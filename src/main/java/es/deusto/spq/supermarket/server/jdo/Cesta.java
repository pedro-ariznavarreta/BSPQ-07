/** @package es.deusto.testing.junit

    @brief Esto es la clase jdo de Cesta
*/
package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.*;


import java.util.Date;


/**
 * Clase CESTA donde se recogen los PRODUCTO deseados por un USUARIO
 * @author Pedro
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Cesta {

    private String Nombreproducto;
    private Date fechaExpiracion;
    private String NombreUsuario;
    /**
     * Constructor de la clase CESTA
     * @param nombreproducto String con el nombre del PRODUCTO
     * @param fechaExpiracion Date de la fecha de expiracion
     * @param nombreUsuario String con el nombre del USUARIO dueño de la CESTA
     */
	public Cesta(String nombreproducto, Date fechaExpiracion, String nombreUsuario) {
		super();
		Nombreproducto = nombreproducto;
		this.fechaExpiracion = fechaExpiracion;
		NombreUsuario = nombreUsuario;
	}
	/**
	 * Devuelve el nombre del PRODUCTO
	 * @return String con el nombre del PRODUCTO
	 */
	public String getNombreproducto() {
		return Nombreproducto;
	}
	/**
	 * Permite cambiar el nombre del PRODUCTO
	 * @param nombreproducto String con el nombre del nuevo PRODUCTO
	 */
	public void setNombreproducto(String nombreproducto) {
		Nombreproducto = nombreproducto;
	}
	/**
	 * Devuelve la fecha de expiracion
	 * @return Date con la fecha de expiracion
	 */
	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}
	/**
	 * Permite cambiar la fecha de expiracion
	 * @param fechaExpiracion Date con la nueva fecha
	 */
	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	/**
	 * Devuelve el nombre de USUARIO dueño de la CESTA
	 * @return String con el nombre de USUARIO
	 */
	public String getNombreUsuario() {
		return NombreUsuario;
	}
	/**
	 * Permite cambiar el nombre de USUARIO de la CESTA
	 * @param nombreUsuario String con el nuevo nombre de USUARIO
	 */
	public void setNombreUsuario(String nombreUsuario) {
		NombreUsuario = nombreUsuario;
	}
	/**
	 * toString de la clase CESTA
	 */
	@Override
	public String toString() {
		return "Cesta [Nombreproducto=" + Nombreproducto + ", fechaExpiracion=" + fechaExpiracion + ", NombreUsuario="
				+ NombreUsuario + "]";
	}
    
	
    
	
    
	

   
}