package es.deusto.spq.supermarket.server.jdo;

import javax.jdo.annotations.PersistenceCapable;



@PersistenceCapable(detachable = "true")
public class Usuario {
	
	
	private String username;
	private String password;
	private String email;
	

	
	public Usuario(String Usuario, String password, String email) {
		this.username = Usuario;
		this.password = password;
		this.email = email;
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

	@Override
	public String toString() {
		return "Usuarios [username=" + username + ", password=" + password + "]";
	}

}
