package es.deusto.spq.supermarket.client;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;


/**
 * VentanaBusqueda es la clase que representa la interfaz de la ventana de la busqueda del supermercado.
 * En esta ventana, el usuario puede ver los productos que estan disponibles,  añadirlos a la cesta,
 * y buscarlos. Tambien existe la posiblidad de poner los prodcutos en favoritos.
 * Esta ventana interactúa con un servicio REST para recuperar y gestionar los productos de la cesta.
 * 
 * @version 1.0
 */

public class VentanaBusqueda extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private JTextField textBuscador;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private DefaultTableModel tableModel_ofertas = new DefaultTableModel();
	private static Usuario usuario;
	private static int cantidad;
	private static List<Product> productos;
	private static List<Product> listaFavoritos = new ArrayList<>();
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	private JPanel panel;
	MetodsClient mt = new MetodsClient();
	private JButton btnCesta;

	
	private JTable table_ofertas;

	/**
	 * Carga la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Usuario();
					VentanaBusqueda frame = new VentanaBusqueda(u);
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	public VentanaBusqueda(Usuario usuarioValidado) {
		usuario = usuarioValidado;
		initialize();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaBusqueda(Usuario usuarioValidado, int cantidadproductosa) {
		usuario = usuarioValidado;
		initialize();
	}
	
	/**
	* Metodo para buscar los productos dentro de la JList
	* 
	* @param producto que se quiere buscar.
	* @return devuevlve el producto encontrado
	* 
	*/
	
	
	public  List<Product> busquedaProd(String producto) {
		List<Product> productos = null;

		if (producto.equals("")) {
			GenericType<List<Product>> genericType = new GenericType<List<Product>>() {
			};
			productos = productAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		} else {
			WebTarget productNomTarget = appTarget.path("nomP").queryParam("nombre", producto);
			GenericType<List<Product>> genericType = new GenericType<List<Product>>() {
			};
			productos = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		}

		return productos;

	}
	/**
	* Metodo para actualizar la tabla para buscar los productos
	* 
	*
	* 
	*/
	public void actualizarTabla() {
		// Limpiamos el modelo de tabla
		tableModel.setRowCount(0);

		// Llenamos la tabla con los productos favoritos
		for (Product p : listaFavoritos) {
			Object[] fila = { p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getCantidad(), p.getPrecio() };
			tableModel.addRow(fila);
		}
	}

	/**
	 *Se crea la Ventana
	 * 
	 * @param Pedro
	 */
	private void initialize() {
		 WebTarget contarTarget = appTarget.path("contar").queryParam("Usuario",
		 usuario.getUsername());
		 GenericType<Integer> genericType7 = new GenericType<Integer>() {};
		 cantidad =
		 contarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(87, 111, 527, 293);
		contentPane.add(panel);
		panel.setVisible(true);

		table_ofertas = new JTable((TableModel) null);
		table_ofertas.setModel(tableModel_ofertas);
		tableModel_ofertas.addColumn("Codigo");
		tableModel_ofertas.addColumn("Nombre");
		tableModel_ofertas.addColumn("Descripcion");
		tableModel_ofertas.addColumn("Stock");
		tableModel_ofertas.addColumn("Precio");
		tableModel_ofertas.addColumn("Precio nuevo");

		JLabel lblBuscador = new JLabel("Buscador");
		lblBuscador.setForeground(new Color(255, 255, 0));
		lblBuscador.setBounds(44, 38, 91, 26);
		lblBuscador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblBuscador);

		textBuscador = new JTextField();
		textBuscador.setBounds(131, 44, 246, 19);
		contentPane.add(textBuscador);
		textBuscador.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(255, 255, 0));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				productos = busquedaProd(textBuscador.getText());
				while (tableModel.getRowCount() > 0) {
					tableModel.removeRow(0);
				}
				for (Product p : productos) {
					if (p.getNombre().toLowerCase().contains(textBuscador.getText().toLowerCase())) {
						tableModel.addRow(new Object[] { p.getCodigo(), p.getNombre(), p.getDescripcion(),
								p.getCantidad(), p.getPrecio() });
					}
				}
			}
		});
		/**
		* Boton para buscar el producto y hacer un filtrado dentro de la JLIST
		* 
		* 
		* 
		*/
		btnBuscar.setBounds(381, 41, 85, 21);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnBuscar);

		btnCesta = new JButton("Cesta : " + cantidad);
		btnCesta.setBackground(new Color(255, 255, 0));
		btnCesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCesta window = new VentanaCesta(usuario);
				window.setVisible(true);
				setVisible(false);
			}
		});
		/**
		* Boton para volver a la ventana anterior
		* 
		* 
		* 
		*/
		JButton btnVolver = new JButton("Finalizar");
		btnVolver.setBackground(new Color(255, 255, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin log = new VentanaLogin();
				log.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		/**
		* Boton filtrar por favoritos
		* 
		* 
		* 
		*/
		JButton botonFavoritos = new JButton("Favoritos");
		botonFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actualizamos la tabla con los productos favoritos
				actualizarTabla();
			}
		});
		botonFavoritos.setBackground(new Color(255, 255, 0));
		botonFavoritos.setBounds(525, 42, 89, 23);
		contentPane.add(botonFavoritos);
		btnVolver.setBounds(44, 468, 91, 32);
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnVolver);
		/**
		* Boton para añadir los productos a la cesta
		* 
		* 
		* 
		*/
		JButton btnAñadir = new JButton("Añadir a la cesta");
		btnAñadir.setBackground(new Color(255, 255, 0));
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreusuario = usuario.getUsername();
				String nombreproducto = productos.get(table.getSelectedRow()).getNombre();
				WebTarget anadirTarget = appTarget.path("anadir").queryParam("Producto", nombreproducto).queryParam("Usuario", nombreusuario);
				GenericType<Boolean> genericType5 = new GenericType<Boolean>() {};
				boolean respuesta = anadirTarget.request(MediaType.APPLICATION_JSON).get(genericType5);

				WebTarget contarTarget = appTarget.path("contar").queryParam("Usuario", nombreusuario);
				GenericType<Integer> genericType7 = new GenericType<Integer>() {};
				cantidad = contarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
				btnCesta.setText("Cesta : " + cantidad);
				if (respuesta != true) {
					System.out.println("El producto no se añade a la cesta");
				}
			}
		});
		btnAñadir.setBounds(283, 468, 146, 32);
		btnAñadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnAñadir);

		btnCesta.setBounds(564, 468, 91, 32);
		btnCesta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCesta);

		JScrollPane scroll = new JScrollPane((Component) null);
		scroll.setBounds(87, 109, 527, 295);
		panel.add(scroll);
		table = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false; // todas las celdas no son editables
			}
		};
		table.setModel(tableModel);
		table.setRowSelectionAllowed(true); // permitir selección de fila
		table.setColumnSelectionAllowed(false); //
		tableModel.addColumn("Codigo");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Descripcion");
		tableModel.addColumn("Stock");
		tableModel.addColumn("Precio");
		scroll.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					Product productoSeleccionado = productos.get(row);
					int opcion = JOptionPane.showConfirmDialog(null, "¿Añadir a favoritos?", "Confirmar",
							JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						listaFavoritos.add(productoSeleccionado);
						JOptionPane.showMessageDialog(null, "Producto añadido a favoritos.");
					}
				}
			}
		});

	}
	

}