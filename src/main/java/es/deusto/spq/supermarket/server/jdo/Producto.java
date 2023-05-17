
package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.PersistenceCapable;



@PersistenceCapable(detachable = "true")
public class Producto {
	
	private String nom;
	private String cod;
	private String desc;
	private String precio;
	private String cant;
	

	public Producto(String nom, String cod, String desc, String precio, String cant) {
		this.nom = nom;
		this.cod = cod;
		this.desc = desc;
		this.precio = precio;
		this.cant = cant;
	}

	public String getnom() {
		return nom;
	}

	public void setnom(String nom) {
		this.nom = nom;
	}

	public String getcod() {
		return cod;
	}

	public void setcod(String cod) {
		this.cod = cod;
	}

	public String getdesc() {
		return desc;
	}

	public void setdesc(String desc) {
		this.desc = desc;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getCant() {
		return cant;
	}

	public void setCant(String cant) {
		this.cant = cant;
	}

	
	
}
