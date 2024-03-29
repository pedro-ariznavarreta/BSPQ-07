package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.Resource;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * VentanaCrearCuentaGerente es una clase que extiende JFrame 
 * Se podrá crear una cuenta gerente solamente por otro gerente.
 * Incluye nombre, email y contrasena.
 * 
 * @version 1.0
 * @since 2023-05-19
 */
public class VentanaCrearCuentaGerente extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static JTextField txtUsername;
	public static JTextField txtEmail;
	public static JTextField txtContraseña;
	public static JTextField txtRepetirContraseña;
	public static JTextField codigotext;
	public static int codigoverificacion;

	
	/**
	 * Sirve para actulizar los CSV a medida que se inicie sesion un usuario
	 * 
	 * @param el usuario que se va a escribir
	 */
	public void escribirEnElCsv(Usuario u){
		try {
			FileWriter fileWriter = new FileWriter("sql/csvTrabajadores.csv", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String datos = u.getUsername() + "," +u.getPassword()+","+u.getEmail()+",0,1";
			String datosUnidos = String.join(",", datos);
			
			fileWriter.getEncoding();
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
	 * Create the application.
	 */
	public VentanaCrearCuentaGerente() {
		getContentPane().setBackground(new Color(0, 0, 0));
		initialize();
	}

	private void initialize() {

		/**
		 * Valores propios de la ventana JFRAME
		 */
		setVisible(true);
		setBounds(100, 100, 375, 575);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Inizializamos todos los Jlabel de dentro de la ventana y los retocamos para
		// que sea mas bonitos visualmente hablando
		
		JLabel lblRegistrarse = new JLabel("REGISTRARSE COMO GERENTE");
		lblRegistrarse.setToolTipText("");
		lblRegistrarse.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarse.setForeground(new Color(233, 217, 27));
		lblRegistrarse.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		lblRegistrarse.setBounds(74, 39, 155, 36);
		getContentPane().add(lblRegistrarse);

		final JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(233, 217, 27));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(71, 83, 104, 29);
		getContentPane().add(lblUsername);

		final JLabel lblContraseña = new JLabel("Contrasena");
		lblContraseña.setForeground(new Color(233, 217, 27));
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContraseña.setBounds(71, 237, 86, 29);
		getContentPane().add(lblContraseña);

		final JLabel lblRepetirContraseña = new JLabel("Repetir Contraseña");
		lblRepetirContraseña.setForeground(new Color(233, 217, 27));
		lblRepetirContraseña.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRepetirContraseña.setBounds(71, 318, 168, 29);
		getContentPane().add(lblRepetirContraseña);

		final JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(233, 217, 27));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(71, 165, 63, 29);
		getContentPane().add(lblEmail);

		//JTextField
		txtUsername = new JTextField();
		txtUsername.setBounds(71, 122, 223, 23);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(71, 204, 222, 23);
		getContentPane().add(txtEmail);

		txtContraseña = new JTextField();
		txtContraseña.setBounds(71, 276, 227, 23);
		getContentPane().add(txtContraseña);

		txtRepetirContraseña = new JTextField();
		txtRepetirContraseña.setBounds(71, 357, 227, 23);
		getContentPane().add(txtRepetirContraseña);

		//Cierra la aplicacion
		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrar.setBackground(new Color(255, 255, 255));
		btnCerrar.setBounds(185, 478, 137, 36);
		getContentPane().add(btnCerrar);
		btnCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnRecibirCodigo = new JButton("REGISTRAR");
		btnRecibirCodigo.setForeground(new Color(255, 255, 255));
		btnRecibirCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRecibirCodigo.setBackground(new Color(233, 217, 27));
		
		btnRecibirCodigo.setBounds(42, 412, 280, 45);
		getContentPane().add(btnRecibirCodigo);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(260, 21, 91, 54);
		getContentPane().add(lblNewLabel);
		ImageIcon icon = new ImageIcon("img/COOKMASTER.png");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		lblNewLabel.setIcon(scaledIcon);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setBounds(42, 478, 133, 37);
		getContentPane().add(btnVolver);
		
		
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});

		btnRecibirCodigo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// COMPROBACION DE QUE LOS CAMPOS ESTAN RELLENADOS
				if (txtUsername.getText().length() == 0 || txtContraseña.getText().length() == 0
						|| txtRepetirContraseña.getText().length() == 0 || txtEmail.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Asegurese de que todos los campos estan completados", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					lblUsername.setForeground(Color.RED);
					lblContraseña.setForeground(Color.RED);
					lblRepetirContraseña.setForeground(Color.RED);
					lblEmail.setForeground(Color.RED);

				}
				// ERROR DE CONTRASE�A NO HA METIDO LAS DOS IGUALES
				else if (!txtRepetirContraseña.getText().equals(txtContraseña.getText())) {
					JOptionPane.showMessageDialog(null, "Las contrase\u00F1as no coinciden", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					lblContraseña.setForeground(Color.RED);
					lblRepetirContraseña.setForeground(Color.RED);

				}

				// COMPROBACION USUARIO EXISTENTE O NO PARA ENVIAR CODIGO AL CORREO
				else if (txtContraseña.getText().equals(txtRepetirContraseña.getText())) {

					boolean usuariousado = Resource.nomcheck(txtUsername.getText());

					if (usuariousado == true) {

						System.out.println("Usuario repetido");
						JOptionPane.showMessageDialog(null, "Este usuario ya esta usado, use otro", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Client cliente = ClientBuilder.newClient();
						final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
						
	 	                final WebTarget trabajadorGerenteTar = appTarget.path("regGerente"); //Para registrar al gerente
	 	               List<String> trabajadorGerente = new ArrayList<>();
	 	               trabajadorGerente.add(txtUsername.getText());
	 	               trabajadorGerente.add(txtContraseña.getText());
	 	               trabajadorGerente.add(txtEmail.getText());
	 	               trabajadorGerente.add(String.valueOf(0));
	 	               trabajadorGerente.add(String.valueOf(1));
	 	               
	 	              Usuario u = new Usuario(
	            		   		trabajadorGerente.get(0),
	            		   		trabajadorGerente.get(1),
	            		   		trabajadorGerente.get(2),
	            		   		Integer.parseInt(trabajadorGerente.get(3)),
	            		   		Integer.parseInt(trabajadorGerente.get(4)));
	               
	              escribirEnElCsv(u); //Guardamos en local antes de subir a la nube
	 	               
	 	               
	 	               trabajadorGerenteTar.request().post(Entity.entity(trabajadorGerente, MediaType.APPLICATION_JSON));

						JOptionPane.showMessageDialog(btnVolver, "Gerente registrado correctamente");
						dispose();
					}

				}

			}

		});

	}

}
