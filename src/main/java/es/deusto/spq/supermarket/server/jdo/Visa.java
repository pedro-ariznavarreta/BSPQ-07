package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
/**
 * Clase Visa para tarjetas Visa
 * @author Unai
 *
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
public class Visa {
	
	private int nTarjeta;
	private String titular;
	private int cv;
	private String fCaducidad;
	/**
	 * Constructor de la clase Visa
	 * @param nTarjeta numero de la tarjeta Visa
	 * @param titular nombre del titular de la tarjeta Visa
	 * @param cv numero secreto de la tarjeta Visa
	 * @param fCaducidad fecha de caducidad de la tarjeta Visa
	 */
	public Visa(int nTarjeta, String titular, int cv, String fCaducidad) {
		super();
		this.nTarjeta = nTarjeta;
		this.titular = titular;
		this.cv = cv;
		this.fCaducidad = fCaducidad;
	}
	/**
	 * Constructor de la clase Visa vacio
	 */
	public Visa() {
		
	}
	/**
	 * Devuelve el numero de l atarjeta Visa
	 * @return int con el numero de la terjeta Visa
	 */
	public int getnTarjeta() {
		return nTarjeta;
	}
	/**
	 * Cambia el nuemro de la tarjeta Visa
	 * @param nTarjeta int del numero de larjeta Visa a introducir
	 */
	public void setnTarjeta(int nTarjeta) {
		this.nTarjeta = nTarjeta;
	}
	/**
	 * Devuelve el nombre del titular de la tarjeta Visa
	 * @return String del titular de la tarjeta Visa
	 */
	public String getTitular() {
		return titular;
	}
	/**
	 * Cambia el nombre del titular de la tarjeta Visa
	 * @param titular String con el nombre del titular de la tarjeta Visa a introducir
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}
	/**
	 * Devuelve el numero secreto de la tarjeta Visa
	 * @return int con el numero secreto de la tarjeta Visa
	 */
	public int getCv() {
		return cv;
	}
	/**
	 * Cambia el numero secreto de la tarjeta Visa
	 * @param cv int con el numero secreto de la tarjeta Visa a introducir
	 */ 
	public void setCv(int cv) {
		this.cv = cv;
	}
	/**
	 * Devuelve la fecha de caducidad de la tarjeta Visa
	 * @return String con la fecha de caducidad de la tarjeta Visa
	 */
	public String getfCaducidad() {
		return fCaducidad;
	}
	/**
	 * Cambia la fecha de caducidad de la tarjeta Visa
	 * @param fCaducidad String con la fecha de caducidad de la tarjeta Visa a introducir
	 */
	public void setfCaducidad(String fCaducidad) {
		this.fCaducidad = fCaducidad;
	}
	/**
	 * To String de Visa
	 */
	@Override
	public String toString() {
		return "Visa [nTarjeta=" + nTarjeta + ", titular=" + titular + ", cv=" + cv + ", fCaducidad=" + fCaducidad
				+ "]";
	}
	

	
	
}
