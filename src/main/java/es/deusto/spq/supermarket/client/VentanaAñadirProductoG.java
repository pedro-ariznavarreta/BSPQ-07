package es.deusto.spq.supermarket.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

public class VentanaAñadirProductoG extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static JTextField txtNom;
	public static JTextField txtCod;
	public static JTextField txtDesc;
	public static JTextField txtPrecio;
	public static int codigoverificacion;
	private JTextField txtCant;
	
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget userTarget = appTarget.path("regProductos");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAñadirProductoG signup = new VentanaAñadirProductoG();
					signup.setVisible(false);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaAñadirProductoG() {
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
		setVisible(true);
		setBounds(100, 100, 375, 575);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		// Inizializamos todos los Jlabel de dentro de la ventana y los retocamos para
		// que sea mas bonitos visualmente hablando
		JLabel lblRegistrarse = new JLabel("AÑADIR PRODUCTO");
		lblRegistrarse.setToolTipText("");
		lblRegistrarse.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarse.setForeground(new Color(233, 217, 27));
		lblRegistrarse.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		lblRegistrarse.setBounds(59, 43, 263, 29);
		getContentPane().add(lblRegistrarse);

		final JLabel lblNomProducto = new JLabel("Nombre del producto");
		lblNomProducto.setForeground(new Color(233, 217, 27));
		lblNomProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomProducto.setBounds(74, 83, 184, 29);
		getContentPane().add(lblNomProducto);

		final JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setForeground(new Color(233, 217, 27));
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(74, 210, 86, 29);
		getContentPane().add(lblDescripcion);

		final JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(new Color(233, 217, 27));
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(74, 272, 168, 29);
		getContentPane().add(lblPrecio);

		final JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setForeground(new Color(233, 217, 27));
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(74, 147, 63, 29);
		getContentPane().add(lblCodigo);

		// Los JTextField
		txtNom = new JTextField();
		txtNom.setBounds(74, 113, 223, 23);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtCod = new JTextField();
		txtCod.setColumns(10);
		txtCod.setBounds(75, 176, 222, 23);
		getContentPane().add(txtCod);

		txtDesc = new JTextField();
		txtDesc.setBounds(74, 238, 227, 23);
		getContentPane().add(txtDesc);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(74, 299, 227, 23);
		getContentPane().add(txtPrecio);

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

		JButton btnAñadir = new JButton("AÑADIR");
		btnAñadir.setForeground(new Color(255, 255, 255));
		btnAñadir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAñadir.setBackground(new Color(233, 217, 27));
		
		btnAñadir.setBounds(42, 412, 280, 45);
		getContentPane().add(btnAñadir);
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
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad disp.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(233, 217, 27));
		lblNewLabel_1.setBounds(74, 333, 140, 23);
		getContentPane().add(lblNewLabel_1);
		
		txtCant = new JTextField();
		txtCant.setBounds(74, 360, 227, 23);
		getContentPane().add(txtCant);
		
		
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Usuario u = new Usuario();
				new VentanaGerente(u);
				dispose();
			}
		});

		btnAñadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// COMPROBACION DE QUE LOS CAMPOS ESTAN RELLENADOS
				if (txtNom.getText().length() == 0 || txtCod.getText().length() == 0
						|| txtDesc.getText().length() == 0 || txtPrecio.getText().length() == 0 || txtCant.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Asegurese de que todos los campos estan completados", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					lblRegistrarse.setForeground(Color.RED);
					lblNomProducto.setForeground(Color.RED);
					lblDescripcion.setForeground(Color.RED);
					lblPrecio.setForeground(Color.RED);
					lblCodigo.setForeground(Color.RED);

				}

					boolean usuariousado = Resource.nomcheck(txtCod.getText());

					if (usuariousado == true) {

						System.out.println("Producto Repetido");
						JOptionPane.showMessageDialog(null, "Este producto ya esta registrado, use otro", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} else {
						
	 	               List<String> producto = new ArrayList<>();
	 	               producto.add(txtNom.getText());
	 	               producto.add(txtCod.getText());
	 	               producto.add(txtDesc.getText());
	 	               producto.add(txtPrecio.getText());
	 	               producto.add(txtCant.getText());
	 	               
	 	               userTarget.request().post(Entity.entity(producto, MediaType.APPLICATION_JSON));

						JOptionPane.showMessageDialog(btnVolver, "Producto registrado correctamente");
						dispose();
					}
				}
		});
	}
}
