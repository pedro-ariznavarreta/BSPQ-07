package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.PersistenceCapable;


/**
 * La clase Usuario representa un usuario del sistema en la base de datos.
 * La anotación @PersistenceCapable permite que los objetos de esta clase sean almacenados y recuperados en la base de datos.
 *
 * @author JavierP
 * @version 1.0
 * @since 2023-05-20
 */
@PersistenceCapable(detachable = "true")
public class Usuario {
	
	
	private String username;
	private String password;
	private String email;
	private int trabajador;
	private int gerente;
	


	/**
	 * Constructor que inicializa un nuevo objeto Usuario con los detalles proporcionados.
	 *
	 * @param Usuario El nombre de usuario
	 * @param password La contraseña del usuario
	 * @param email El correo electrónico del usuario
	 * @param trabajador Valor que indica si el usuario es trabajador
	 * @param gerente Valor que indica si el usuario es gerente
	 */
	
	public Usuario(String Usuario, String password, String email,int trabajador,int gerente) {
		this.username = Usuario;
		this.password = password;
		this.email = email;
		this.gerente = gerente;
		this.trabajador = trabajador;
	}

	
	/**
	 * Constructor por defecto para Usuario. Este es requerido para la persistencia JDO.
	 */
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(int trabajador) {
		this.trabajador = trabajador;
	}

	public int getGerente() {
		return gerente;
	}

	public void setGerente(int gerente) {
		this.gerente = gerente;
	}

}
