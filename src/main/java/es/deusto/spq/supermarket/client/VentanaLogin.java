package es.deusto.spq.supermarket.client;



import java.awt.BorderLayout;

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
import javax.ws.rs.core.MediaType;
import es.deusto.spq.supermarket.server.jdo.User;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.UIManager;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class VentanaLogin extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private JFrame frame;
	private JTextField textnombre_usuario;
	private JTextField textContraseña;
	private static User usuarios;
	//private VentanaCrearCuenta cc = new VentanaCrearCuenta();
	
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/myapp");
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
		setBounds(100, 100, 386, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblnombre_usuario = new JLabel("Nombre de usuario");
		lblnombre_usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblnombre_usuario.setBounds(53, 70, 129, 24);
		contentPane.add(lblnombre_usuario);

		JLabel lblcontraseña = new JLabel("Contraseña");
		lblcontraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcontraseña.setBounds(53, 169, 140, 24);
		contentPane.add(lblcontraseña);

		textnombre_usuario = new JTextField();
		textnombre_usuario.setBounds(53, 113, 284, 24);
		contentPane.add(textnombre_usuario);
		textnombre_usuario.setColumns(10);

		textContraseña = new JTextField();

		// Passwordfield para la contraseï¿½a
		textContraseña = new JPasswordField();
		textContraseña.setColumns(10);
		textContraseña.setBounds(53, 204, 284, 24);
		contentPane.add(textContraseña);

		// Checkbox de mostrar contraseï¿½a
		final JCheckBox showpass = new JCheckBox("Mostrar Contrase\u00F1a");
		showpass.setForeground(SystemColor.textHighlight);
		showpass.setBackground(UIManager.getColor("Button.highlight"));
		showpass.setBounds(216, 242, 121, 23);
		getContentPane().add(showpass);
		showpass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (showpass.isSelected()) {
					((JPasswordField) textContraseña).setEchoChar((char) 0);
				} else {
					((JPasswordField) textContraseña).setEchoChar('*');
				}
			}
		});

		JLabel lblLoginMecradonia = new JLabel("LOGIN MERCADONIA");
		lblLoginMecradonia.setToolTipText("");
		lblLoginMecradonia.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginMecradonia.setForeground(SystemColor.textHighlight);
		lblLoginMecradonia.setFont(new Font("Leelawadee UI", Font.PLAIN, 24));
		lblLoginMecradonia.setBounds(10, 0, 350, 59);
		contentPane.add(lblLoginMecradonia);

		JButton cerrar = new JButton("CERRAR");
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cerrar.setBackground(Color.LIGHT_GRAY);
		cerrar.setBounds(200, 351, 137, 36);
		contentPane.add(cerrar);

		JButton crear = new JButton("REGISTRARSE");
		crear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cc.setVisible(true);
				setVisible(false);

			}
		});
		crear.setBackground(Color.LIGHT_GRAY);
		crear.setBounds(56, 351, 137, 36);
		contentPane.add(crear);

		JButton loginbtn = new JButton("LOGIN");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textnombre_usuario.getText().equals("admin") && textContraseña.getText().equals("admin")) {
//					VentanaAdmin v = new VentanaAdmin();
//					v.setVisible(true);
//					dispose();
				}else {
					boolean result = login(textnombre_usuario.getText(), textContraseña.getText());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "Usuario Correcto");
						//VentanaOpcion window1 = new VentanaOpcion(usuarios);
						//window1.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Usuario incorrecto");
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
			}
		});
		loginbtn.setForeground(Color.WHITE);
		loginbtn.setBackground(SystemColor.textHighlight);
		loginbtn.setBounds(53, 297, 284, 43);
		contentPane.add(loginbtn);
	}

	public boolean login(String usuario, String contraseña) {
		if (!usuario.equals("") && !contraseña.equals("")) {
			WebTarget userNomTarget = userTarget.path("nom").queryParam("nick",usuario);
			GenericType<Usuario> genericType = new GenericType<Usuario>() {};
			
			if (usuarios.getPassword().equals(contraseña) || !(usuarios == null)) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}

	
}
