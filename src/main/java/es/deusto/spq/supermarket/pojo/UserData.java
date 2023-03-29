package es.deusto.spq.supermarket.pojo;

public class UserData {

    private String login;
    private String password;
	int esTrabajador = 0;
	int esAdministrador = 0;

    public UserData() {
        // required by serialization
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }
    public int getEsTrabajador() {
    	return this.esTrabajador;
    }
    
    public int getEsAdministrador() {
    	return this.esAdministrador;
    }

    public void setEsTrabajador(int esTrabajador) {
		this.esTrabajador = esTrabajador;
	}

	public void setEsAdministrador(int esAdministrador) {
		this.esAdministrador = esAdministrador;
	}

	public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "[login=" + login + ", password=" + password + "]";
    }
}