package es.deusto.spq.supermarket.server.jdo;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import java.util.HashSet;

@PersistenceCapable
public class User {
	@PrimaryKey
	String login=null;
	String password=null;
	int esTrabajador = 0;
	int esAdministrador = 0;
	
	public int getEsTrabajador() {
		return esTrabajador;
	}

	public void setEsTrabajador(int esTrabajador) {
		this.esTrabajador = esTrabajador;
	}

	public int getEsAdministrador() {
		return esAdministrador;
	}

	public void setEsAdministrador(int esAdministrador) {
		this.esAdministrador = esAdministrador;
	}

	@Persistent(mappedBy="user", dependentElement="true")
	@Join
	Set<Message> messages = new HashSet<>();
	
	
	
	public User(String login, String password, int esTrabajador, int esAdministrador) {
		this.login = login;
		this.password = password;
		this.esAdministrador = esAdministrador;
		this.esTrabajador = esTrabajador;
	}
	
	public void addMessage(Message message) {
		messages.add(message);
	}

	public void removeMessage(Message message) {
		messages.remove(message);
	}

	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	 public Set<Message> getMessages() {return this.messages;}
	 
	 public String toString() {
		StringBuilder messagesStr = new StringBuilder();
		for (Message message: this.messages) {
			messagesStr.append(message.toString() + " - ");
		}
        return "User: login --> " + this.login + ", password -->  " + this.password + ", messages --> [" + messagesStr + "]";
    }
}
