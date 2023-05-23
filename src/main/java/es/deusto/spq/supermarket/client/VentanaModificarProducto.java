package es.deusto.spq.supermarket.client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaModificarProducto extends JFrame {
	/*
	 * Ventana en la que te mostrá en textfield el producto que has seleccionado pudiendo modificarlo
	 */
	
	private static final long serialVersionUID = 1L;


	public static JTextField txtNom;
	
	public static JTextField txtDesc;
	public static JTextField txtPrecio;
	public static JTextField txtCant;
	public static int codigoverificacion;
	
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget userTarget = appTarget.path("regProductos");

	/**
	 * Crea la aplicacion
	 */
	
	public VentanaModificarProducto(Product p) {
		getContentPane().setBackground(new Color(0, 0, 0));
		initialize(p);
	}

	/**
	 * Inicializamos todos los elementos de la ventana crearcuenta los cuales
	 * separaremos mas adelante mediante mas usuarios
	 */

	private void initialize(Product producto) {

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
		
		JLabel lblRegistrarse = new JLabel("MODIFICAR PRODUCTO");
		lblRegistrarse.setToolTipText("");
		lblRegistrarse.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarse.setForeground(new Color(233, 217, 27));
		lblRegistrarse.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		lblRegistrarse.setBounds(42, 41, 280, 29);
		getContentPane().add(lblRegistrarse);

		final JLabel lblNomProducto = new JLabel("Nombre del producto");
		lblNomProducto.setForeground(new Color(233, 217, 27));
		lblNomProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomProducto.setBounds(74, 105, 184, 29);
		getContentPane().add(lblNomProducto);

		final JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setForeground(new Color(233, 217, 27));
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setBounds(74, 184, 86, 29);
		getContentPane().add(lblDescripcion);

		final JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setForeground(new Color(233, 217, 27));
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(74, 260, 168, 29);
		getContentPane().add(lblPrecio);

		final JLabel lblCodigo = new JLabel("Cantidad");
		lblCodigo.setForeground(new Color(233, 217, 27));
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(74, 330, 86, 29);
		getContentPane().add(lblCodigo);

		// Los JTextField
		txtNom = new JTextField();
		txtNom.setBounds(74, 134, 223, 23);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setBounds(74, 213, 223, 23);
		getContentPane().add(txtDesc);

		txtPrecio = new JTextField();
		txtPrecio.setBounds(74, 289, 223, 23);
		getContentPane().add(txtPrecio);

		// Cierra la aplicacion
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

		JButton btnAñadir = new JButton("MODIFICAR");
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
		
		txtCant = new JTextField();
		txtCant.setBounds(74, 358, 223, 23);
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

		txtNom.setText(producto.getNombre());
		txtDesc.setText(producto.getDescripcion());
		txtCant.setText(Integer.toString(producto.getCantidad()));
		txtPrecio.setText(Long.toString((long) producto.getPrecio()));
		
		/*
		 * BOTON PARA MODIFICAR EL PRODUCTO DE LA BASE DE DATOS
		 */
		
		btnAñadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// COMPROBACION DE QUE LOS CAMPOS ESTAN RELLENADOS
				if (txtNom.getText().length() == 0 || txtDesc.getText().length() == 0 || 
						txtPrecio.getText().length() == 0 || txtCant.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Asegurese de que todos los campos estan completados", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					lblRegistrarse.setForeground(Color.RED);
					lblNomProducto.setForeground(Color.RED);
					lblDescripcion.setForeground(Color.RED);
					lblPrecio.setForeground(Color.RED);
					lblCodigo.setForeground(Color.RED);
				}else {
					/*
					 * COJE EL PRODUCTO Y LO METE EN LA BASE DE DATOS Y LO MODIFICA
					 */
	 	               Product pr = new Product();
	 	               pr.setCodigo(producto.getCodigo());
	 	               pr.setNombre(txtNom.getText());
	 	               pr.setDescripcion(txtDesc.getText());
	 	               pr.setPrecio(Double.parseDouble(txtPrecio.getText()));
	 	               pr.setCantidad(Integer.parseInt(txtCant.getText()));
	 	               
	 	               Client cliente = ClientBuilder.newClient();
	 	               final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
						
	 	               final WebTarget modProd = appTarget.path("modificarProductos");
	 	               
	 	               modProd.request().post(Entity.entity(pr, MediaType.APPLICATION_JSON));

	 	               JOptionPane.showMessageDialog(btnVolver, "Producto modificado correctamente");
	 	               dispose();
				}
			}
		});
	}
}
