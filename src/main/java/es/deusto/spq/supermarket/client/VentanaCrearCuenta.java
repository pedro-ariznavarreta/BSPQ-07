package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.SwingConstants;

import es.deusto.spq.supermarket.server.Resource;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * VentanaCrearCuenta es una clase que extiende JFrame y representa la ventana
 * de creación de cuenta en la aplicación. Incluye campos para ingresar el
 * nombre de usuario, email y contraseña.
 * 
 *  * @author JavierP
 * @version 1.0
 * @since 2023-05-20
 */
public class VentanaCrearCuenta extends JFrame {
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static JTextField txtUsername;
	public static JTextField txtEmail;
	public static JTextField txtContraseña;
	public static JTextField txtRepetirContraseña;
	public static JTextField codigotext;
	public static int codigoverificacion;


	 /**
     * Constructor para la clase VentanaCrearCuenta. Inicializa la ventana y su contenido.
     */
	public VentanaCrearCuenta() {
		getContentPane().setBackground(new Color(0, 0, 0));
		initialize();
	}

	/**
	 * Inicializamos todos los elementos de la ventana crearcuenta los cuales
	 * separaremos mas adelante mediante mas usuarios
	 */

	private void initialize() {

		/**
		 * Valores propios de la ventana JFRAME
		 */
		setBounds(100, 100, 375, 575);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Inizializamos todos los Jlabel de dentro de la ventana y los retocamos para
		// que sea mas bonitos visualmente hablando
		JLabel lblRegistrarse = new JLabel("REGISTRARSE");
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

		final JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setForeground(new Color(233, 217, 27));
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContraseña.setBounds(71, 237, 86, 29);
		getContentPane().add(lblContraseña);

		final JLabel lblRepetirContraseña = new JLabel("Repeir Contraseña");
		lblRepetirContraseña.setForeground(new Color(233, 217, 27));
		lblRepetirContraseña.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRepetirContraseña.setBounds(71, 318, 168, 29);
		getContentPane().add(lblRepetirContraseña);

		final JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(233, 217, 27));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(71, 165, 63, 29);
		getContentPane().add(lblEmail);

		// Los JTextField
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

		// Jbutton cerrar. Simplemente cierra la aplicacion
		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrar.setBackground(new Color(255, 255, 255));
		btnCerrar.setBounds(185, 478, 137, 36);
		getContentPane().add(btnCerrar);
		btnCerrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		JButton btnRecibirCodigo = new JButton("RECIBIR CÓDIGO");
		btnRecibirCodigo.setForeground(new Color(255, 255, 255));
		btnRecibirCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRecibirCodigo.setBackground(new Color(233, 217, 27));
		btnRecibirCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
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
				// TODO Auto-generated method stub
				VentanaLogin login = null;
				login = new VentanaLogin();
				setVisible(false);
				login.setVisible(true);
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
						/**
						 * 
						 * MUESTRA LA VENTANA DE LOADING MAS ADELANTE SE MANDARA EL CODIGO AUNQUE SE
						 * SIMULARA COMO QUE SE ESTA MANDANDO A CONTINUACION
						 */

						new Thread(new Hilo()).start();
						setVisible(false);
						Loading load = new Loading();
						load.setVisible(true);
					}

				}

			}

		});

	}
	/**
     * Clase Hilo utilizada para la generación del código de verificación.
     */
	public class Hilo implements Runnable {

		@Override
		public void run() {
			codigoverificacion = (int) (Math.random() * 8999) + 1000;

		}

	}
	/**
     * Devuelve el código de verificación generado aleatoriamente.
     *
     * @return int código de verificación.
     */
	public static int returcodigo() {

		return codigoverificacion;
	}
	/**
     * Devuelve el nombre de usuario ingresado en el campo de texto.
     *
     * @return String nombre de usuario.
     */
	public static String returnnombre() {
		String nombre = txtUsername.getText();
		return nombre;
	}
	 /**
     * Devuelve el email ingresado en el campo de texto.
     *
     * @return String email.
     */
	public static String returnmail() {
		String mail = txtEmail.getText();
		return mail;
	}
	 /**
     * Devuelve la contraseña ingresada en el campo de texto.
     *
     * @return String contraseña.
     */
	public static String returncontra() {
		String contrasenya = txtContraseña.getText();
		return contrasenya;
	}
	/**
     * Devuelve siempre cero, que puede ser usado para inicializar campos que requieren un Integer.
     *
     * @return Integer valor de cero.
     */
	public static Integer returntrabajador() {
		return 0;
	}
	public static Integer returngerente() {
		return 0;
	}

}
