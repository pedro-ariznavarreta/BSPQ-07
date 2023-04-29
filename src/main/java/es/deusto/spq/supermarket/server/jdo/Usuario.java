package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.PersistenceCapable;



@PersistenceCapable(detachable = "true")
public class Usuario {
	
	
	private String username;
	private String password;
	private String email;
	private int trabajador;
	private int gerente;
	

	
	public Usuario(String Usuario, String password, String email,int trabajador,int gerente) {
		this.username = Usuario;
		this.password = password;
		this.email = email;
		this.gerente = gerente;
		this.trabajador = trabajador;
	}

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
