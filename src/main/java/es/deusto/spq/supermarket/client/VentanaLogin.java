package es.deusto.spq.supermarket.client;

import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

import es.deusto.spq.supermarket.server.jdo.Usuario;
import javax.ws.rs.core.MediaType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class VentanaLogin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private JFrame frame;
	private JTextField textEmail;
	private JTextField textContraseña;
	private static Usuario usuarios;
	private VentanaCrearCuenta cc = new VentanaCrearCuenta();

	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget userTarget = appTarget.path("usuarios");
	final WebTarget userAllTarget = userTarget.path("all");

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public VentanaLogin() {
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

		JLabel lblLoginMecradonia = new JLabel("LOGIN COOKMASTER");
		lblLoginMecradonia.setBackground(new Color(255, 255, 255));
		lblLoginMecradonia.setToolTipText("");
		lblLoginMecradonia.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginMecradonia.setForeground(new Color(233, 217, 27));
		lblLoginMecradonia.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		lblLoginMecradonia.setBounds(10, 1, 350, 59);
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
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textEmail.getText().equals("admin") && textContraseña.getText().equals("admin")) {
					// VentanaAdmin v = new VentanaAdmin();
					// v.setVisible(true);
					dispose();
				} else {
					boolean result = login(textEmail.getText(), textContraseña.getText());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "Usuario Correcto");
						// VentanaOpcion window1 = new VentanaOpcion(usuarios);
						// window1.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Usuario incorrecto");
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(233, 217, 27));
		btnLogin.setBounds(42, 298, 284, 43);
		contentPane.add(btnLogin);
	}

	public boolean login(String usuario, String contraseña) {
		if (!usuario.equals("") && !contraseña.equals("")) {
			WebTarget userNomTarget = appTarget.path("nom").queryParam("nick", usuario);
			System.out.println(userNomTarget);
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

}
