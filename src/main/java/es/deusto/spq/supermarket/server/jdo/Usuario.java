package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.PersistenceCapable;

/**
 * Clase USUARIO donde creamos los perfiles de los usuarios del software
 * @author Sergio
 *
 */
@PersistenceCapable(detachable = "true")
public class Usuario {
	
	
	private String username;
	private String password;
	private String email;
	
	/**
	 * Constructor de la clase USUARIO
	 * @param Usuario String con el nombre del USUARIO
	 * @param password String con la contraseña del USUARIO
	 * @param email String con el mail del USUARIO
	 */
	public Usuario(String Usuario, String password, String email) {
		this.username = Usuario;
		this.password = password;
		this.email = email;
	}
	/**
	 * Constructor vacio de la clase USUARIO
	 */
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Devuelve el nombre de usuario del USUARIO
	 * @return String con el nombre de usuario del USUARIO
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Permite cambiar el nombre de usuario del USUARIO
	 * @param username String con el nuevo nombre de usuario
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Devuelve la contraseña del USUARIO
	 * @return String de la contraseña
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Permite cambiar la contraseña del USUARIO
	 * @param password String con la nueva contraseña
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Devuelve el mail del USUARIO
	 * @return String con el main del usuario
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Permite cambiar el mail del USUARIO
	 * @param email String con el nuevo mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * toString de USUARIO
	 */
	@Override
	public String toString() {
		return "Usuarios [username=" + username + ", password=" + password + "]";
	}

}
