package es.deusto.spq.supermarket.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.logging.Logger;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class VentanaVerificarCodigo extends JFrame {
	private JTextField textcodigo;
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/res/resource");
	//final WebTarget userTarget = appTarget.path("usuarios");
	public int codigoverificacion;
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerificarCodigo window = new VentanaVerificarCodigo();
					window.setVisible(true);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaVerificarCodigo() {
		setTitle("INTRODUCIR CODIGO");
		initialize();
	}

	/**
	 * TODOS LOS VALORES DE LA VENTANA
	 */
	private void initialize() {
		setBounds(100, 100, 450, 243);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		// TEXTFIELD PARA INTRODUCIR CODIGO
		textcodigo = new JTextField();
		textcodigo.setBounds(163, 91, 92, 20);
		getContentPane().add(textcodigo);
		textcodigo.setColumns(10);

		// JLABEL QUE INDICA AL USUARIO QUE HAY QUE INTRODUCIR CODIGO
		JLabel titulo = new JLabel("INTRODUCE EL CODIGO PARA VERIFICAR TU CUENTA");
		titulo.setForeground(SystemColor.textHighlight);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(0, 34, 434, 20);
		getContentPane().add(titulo);

		// LABEL DEL CODIGO
		JLabel codigo = new JLabel("Codigo:");
		codigo.setHorizontalAlignment(SwingConstants.RIGHT);
		codigo.setBounds(49, 94, 104, 14);
		getContentPane().add(codigo);

		// BOTON IMPORTANTE DE LA VENTANA QUE COMPRUEBA EL CODIGO Y CREA LA CUENTA
		JButton crearcuenta = new JButton("Crear cuenta");
		crearcuenta.setBackground(new Color(135, 206, 250));
		crearcuenta.setBounds(137, 143, 137, 38);
		getContentPane().add(crearcuenta);

		crearcuenta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				codigoverificacion = VentanaCrearCuenta.returcodigo();

				// TODO Auto-generated method stub
				// COMPROBRACION DE CODIGO DE EMAIL
				if (!textcodigo.getText().equals(Integer.toString(codigoverificacion))) {
					System.out.println("El codigo introducido es erroneo" + textcodigo.getText() + codigoverificacion);
				} else {
					String contrasenya = VentanaCrearCuenta.returncontra();
					String nombre = VentanaCrearCuenta.returnnombre();
					String mail = VentanaCrearCuenta.returnmail();

					setVisible(false);
					System.out.println("Password OK");

					crearCuenta(nombre, contrasenya, mail);

					JOptionPane.showMessageDialog(null, "Cuenta creada correctamente. Inicie sesion.", "CUENTA CREADA",
							JOptionPane.DEFAULT_OPTION);
					VentanaLogin login = null;
					login = new VentanaLogin();
					login.setVisible(true);
				}
			}
		});
	}

	public void crearCuenta(String usuario, String contraseña, String mail) {

		WebTarget userRegTarget = appTarget.path("reg");
		List<String> usuarioL = new ArrayList<>();
		usuarioL.add(usuario);
		usuarioL.add(contraseña);
		usuarioL.add(mail);
		userRegTarget.request().post(Entity.entity(usuarioL, MediaType.APPLICATION_JSON));

	}
}
