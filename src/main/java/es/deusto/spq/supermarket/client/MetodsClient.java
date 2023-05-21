package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.Resource;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

/**
 * La clase metodsClient se encarga de todos esos métodos que hacen uso de la BD para cargar o guardar
 * en un fichero local todos los usuarios.
 * 
  * @author JavierP
 * @version 1.0
 * @since 2023-05-20
 */
public class MetodsClient {
	
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	private static Usuario usuarios;
	
	/**
	 * Sirve para actulizar los CSV a medida que se inicie sesion un usuario
	 * 
	 * @param el usuario que se va a escribir
	 */
	public void escribirEnElCsvT(Usuario u){
		try {
			FileWriter fileWriter = new FileWriter("sql/csvTrabajadores.csv", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String datos = u.getUsername() + "," +u.getPassword()+","+u.getEmail()+",1,0";
			String datosUnidos = String.join(",", datos);
			
			bufferedWriter.write(datosUnidos);
			bufferedWriter.newLine();
			
			bufferedWriter.close();
		    fileWriter.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Sirve cargar todos los usuarios en la BD
	 * 
	 * 
	 */
	
public void cargarCsvLocal() {
		
		Client cliente = ClientBuilder.newClient();
		final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
		final String csvAdmins = "sql/csvTrabajadores.csv";

		
		 try {
	            Scanner lectorCSV = new Scanner(new File(csvAdmins));
	            
	            while (lectorCSV.hasNextLine()) {
	                String fila = lectorCSV.nextLine();
	                String[] valores = fila.split(",");
	                List<String> trabajadorGerente = new ArrayList<>();
	                trabajadorGerente.add(valores[0]);
	        		trabajadorGerente.add(valores[1]);
	        		trabajadorGerente.add(valores[2]);
	        		trabajadorGerente.add(String.valueOf(valores[3]));
	        		trabajadorGerente.add(String.valueOf(valores[4]));
	        		
	        		//System.out.println(valores[0]);
	        		
	        		boolean usuariousado = Resource.nomcheck(valores[0]);

					if (usuariousado == true) {
						
						//No hace nada si ya esta en la BBDD
					}else {
	        		
	        		
	                if(valores[3].equals("1")) {
	                	 final WebTarget trabajadorGerenteTar = appTarget.path("regTrabajador"); //Para registrar al trabajador
	                	trabajadorGerenteTar.request().post(Entity.entity(trabajadorGerente, MediaType.APPLICATION_JSON));
	                }
 	                if(valores[4].equals("1")) {
 	                   final WebTarget trabajadorGerenteTar = appTarget.path("regGerente"); //Para registrar al gerente
 	                	trabajadorGerenteTar.request().post(Entity.entity(trabajadorGerente, MediaType.APPLICATION_JSON));
 	                }
	        		
					}
	                
	               
	               
	            }
	            lectorCSV.close();
	            
	        } catch (FileNotFoundException e) {
	            System.out.println("El archivo no existe o no se puede abrir.");
	            e.printStackTrace();
	        }
	}



}
