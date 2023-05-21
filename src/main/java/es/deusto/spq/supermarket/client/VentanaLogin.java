 package es.deusto.spq.supermarket.client;
 /** @package es.deusto.spq.supermarket.client
 */

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import es.deusto.spq.supermarket.server.Resource;
import es.deusto.spq.supermarket.server.jdo.Usuario;
import javax.ws.rs.core.MediaType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class VentanaLogin extends JFrame {
	/**
	* VentanaLogin sirve para loggearte como usuario ya creado por tu nombre y contrseña
	*
	*
	* * @author JavierP
 * @version 1.0
 * @since 2023-05-20
	*/
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textContraseña;
	private static Usuario usuarios;
	private VentanaCrearCuenta cc = new VentanaCrearCuenta();

	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	MetodsClient mt = new MetodsClient();

	/**
	 * Se carga la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	/**
	 * Se crea el frame
	 */
	public VentanaLogin() {
		//cargarCsvLocal();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 460);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(38, 38, 38));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(233, 217, 27));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(42, 70, 140, 24);
		contentPane.add(lblUsername);

		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setForeground(new Color(233, 217, 27));
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContraseña.setBounds(42, 169, 140, 24);
		contentPane.add(lblContraseña);

		textEmail = new JTextField();
		textEmail.setBounds(42, 107, 284, 24);
		contentPane.add(textEmail);
		textEmail.setColumns(10);

		textContraseña = new JTextField();

		// Passwordfield para la contraseï¿½a
		textContraseña = new JPasswordField();
		textContraseña.setColumns(10);
		textContraseña.setBounds(42, 203, 284, 24);
		contentPane.add(textContraseña);

		// Checkbox de mostrar contraseï¿½a
		final JCheckBox lblShowpass = new JCheckBox("Mostrar contraseña");
		lblShowpass.setForeground(new Color(233, 217, 27));
		lblShowpass.setBackground(new Color(0, 0, 0));
		lblShowpass.setBounds(186, 244, 140, 23);
		getContentPane().add(lblShowpass);
		lblShowpass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (lblShowpass.isSelected()) {
					((JPasswordField) textContraseña).setEchoChar((char) 0);
				} else {
					((JPasswordField) textContraseña).setEchoChar('*');
				}
			}
		});

		JLabel lblLoginMecradonia = new JLabel("LOGIN");
		lblLoginMecradonia.setBackground(new Color(255, 255, 255));
		lblLoginMecradonia.setToolTipText("");
		lblLoginMecradonia.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginMecradonia.setForeground(new Color(233, 217, 27));
		lblLoginMecradonia.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		lblLoginMecradonia.setBounds(26, 21, 109, 49);
		contentPane.add(lblLoginMecradonia);

		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCerrar.setBackground(new Color(255, 255, 255));
		btnCerrar.setBounds(189, 362, 140, 36);
		contentPane.add(btnCerrar);

		JButton btnRegistrar = new JButton("REGISTRARSE");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cc.setVisible(true);
				setVisible(false);

			}
		});
		btnRegistrar.setBackground(new Color(255, 255, 255));
		btnRegistrar.setBounds(42, 362, 140, 36);
		contentPane.add(btnRegistrar);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		/**
		* Boton necesario para loggerate donde se hace la comprobación de si el usuario existe o no
		* 
		* 
		* 
		*/
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textEmail.getText().equals("admin") && textContraseña.getText().equals("admin")) {
					Usuario uu = new Usuario();
					VentanaPanelGerente window= new VentanaPanelGerente(uu);
					window.setVisible(true);
					dispose();
				} else if (textEmail.getText().equals("") || textContraseña.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Usuario incorrecto");
	
				} else {
				int valorRol = comprobarRol(textEmail.getText(), textContraseña.getText());																				    //QUITAR LUEGO !!!!!!!!	
				boolean result = login(textEmail.getText(), textContraseña.getText());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "Usuario Correcto");
						
						//dispose();
						if(valorRol == 0) {  //es cliente
							
							 VentanaBusqueda window1= new VentanaBusqueda(usuarios);
							 window1.setVisible(true);
							 
						}else if(valorRol == 1) {   //es trabajador
								
								new VentanaTrabajador();
						}else if(valorRol == 2) {    //es gerente
								//Usuario u = new Usuario();
								//System.out.println("ENTRA EN GERENTE");
								//new VentanaPanelGerente(u);
								
								Usuario uu = new Usuario();
								VentanaPanelGerente window= new VentanaPanelGerente(uu);
								window.setVisible(true);
							
								}
						dispose();
							}
						}
					}
				
			
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(233, 217, 27));
		btnLogin.setBounds(42, 298, 284, 43);
		contentPane.add(btnLogin);
		
		JLabel labelImg = new JLabel();
		labelImg.setBounds(170, 21, 140, 49);
		contentPane.add(labelImg);

		ImageIcon icon = new ImageIcon("img/COOKMASTER.png");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(labelImg.getWidth(), labelImg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);

		labelImg.setIcon(scaledIcon);

	}
	/**
	* Metodo login sirve para loggear el nuevo usuario
	* 
	* @param el nombre del usurio y la contraseña
	* @return devuelve el booleano de si se a creado correctamente ==  true y al contrario == false
	* 
	*/
	public boolean login(String usuario, String contraseña) {
		if (!usuario.equals("") && !contraseña.equals("")) {
			WebTarget userNomTarget = appTarget.path("nom").queryParam("nick", usuario);
			//System.out.println(userNomTarget);
			GenericType<Usuario> genericType = new GenericType<Usuario>() {
			};
			usuarios = userNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
			if (usuarios.getPassword().equals(contraseña) || !(usuarios == null)) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}
	/**
	* Metodo comporbarRol para saber que usuario se está loggeando
	* 
	* @param el nombre del usurio y la contraseña
	* @return devuelve el valor del usuario si es trabajador o gerente o un cliente
	* dependiedo de que rol tengo devolvera una diferente cliente=0, trabajador=1 y
	* gerente = 2
	* 
	*/
	public int comprobarRol(String usuario, String contraseña) {
		WebTarget userNomTarget = appTarget.path("login").queryParam("nick", usuario);
		GenericType<Usuario> genericType = new GenericType<Usuario>() {	};
		usuarios = userNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		//System.out.println(usuarios.getEmail());
		int valor = 0;
		if(usuarios.getTrabajador() == 1) {
			valor = 1;
		}
		if(usuarios.getGerente()==1) {
			valor = 2;
		}
		return valor;
	}

}

