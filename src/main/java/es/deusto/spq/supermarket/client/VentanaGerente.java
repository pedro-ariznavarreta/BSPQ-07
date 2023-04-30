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
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JToolBar;

import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.Resource;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaGerente extends JFrame {
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

	private JPanel panel;

	private JButton btnCesta;

	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	// final WebTarget cestaTarget = appTarget.path("cesta");
	private JTable table_ofertas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Usuario();
					VentanaGerente frame = new VentanaGerente(u);
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	public VentanaGerente(Usuario usuarioValidado) {
		usuario = usuarioValidado;
		initialize();
	}

	/**
	 * @wbp.parser.constructor
	 */
	public VentanaGerente(Usuario usuarioValidado, int cantidadproductosa) {
		usuario = usuarioValidado;
		initialize();
	}

	public List<Product> busquedaProd(String producto) {
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
	 * Create the frame.
	 * 
	 * @param usuario2
	 */
	private void initialize() {

		setVisible(true);
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

		btnBuscar.setBounds(381, 41, 85, 21);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnBuscar);

		btnCesta = new JButton("Cesta : " + cantidad);
		btnCesta.setBackground(new Color(255, 255, 0));
		btnCesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				VentanaCesta window = new VentanaCesta(usuario);
//				window.setVisible(true);
//				setVisible(false);
			}
		});

		JButton btnBorrarProducto = new JButton("Borrar Producto");
		btnBorrarProducto.setBackground(new Color(255, 255, 0));
		btnBorrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});


		btnBorrarProducto.setBounds(44, 468, 137, 32);
		btnBorrarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnBorrarProducto);


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
		
		JButton btnAnadirProducto = new JButton("Añadir Producto");
		btnAnadirProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnadirProducto.setBackground(Color.YELLOW);
		btnAnadirProducto.setBounds(301, 472, 137, 29);
		btnAnadirProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaAñadirProductoG();
			}
		});	
		
		
		contentPane.add(btnAnadirProducto);
		
		JButton btnModificarProducto = new JButton("Modificar Producto");
		btnModificarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarProducto.setBackground(Color.YELLOW);
		btnModificarProducto.setBounds(526, 472, 151, 29);
		contentPane.add(btnModificarProducto);
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