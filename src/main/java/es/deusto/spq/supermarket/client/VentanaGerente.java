package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.*;

import javax.swing.*;
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

import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaGerente extends JFrame {
	/**
	 * En esta VentanaGerente podemos hacer busqueda de los productos disponibles en la tienda y donde exiten 
	 * tres botones uno para añadirlos otro para eliminar los productos y por otro lado otro para modificarlos
	 * 
	 * 
	 *  * @author JavierP
 * @version 1.0
 * @since 2023-05-20
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private JTextField textBuscador;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private DefaultTableModel tableModel_ofertas = new DefaultTableModel();
	private static Usuario usuario;
	private static List<Product> productos;

	private JPanel panel;


	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	// final WebTarget cestaTarget = appTarget.path("cesta");
	private JTable table_ofertas;

	/**
	 * Carga la aplicacion
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
	
	/**
	* Metodo para buscar los productos dentro de la JList
	* 
	* @param producto que se quiere buscar.
	* @return devuevlve el producto encontrado
	* 
	*/
	

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
	
	

	/**
	 * Se crea el frame
	 * 
	 * 
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
		/**
		* Boton necesario para buscar los porductos
		* 
		* 
		* 
		*/
		
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

		/**
		* Boton para eliminar los porductos
		* 
		* 
		* 
		*/
		JButton btnBorrarProducto = new JButton("Borrar Producto");
		btnBorrarProducto.setBackground(new Color(255, 255, 0));
		btnBorrarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Para borrar haz doble click encima del producto");
			}
		});


		btnBorrarProducto.setBounds(191, 470, 137, 32);
		btnBorrarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnBorrarProducto);


		JScrollPane scroll = new JScrollPane((Component) null);
		scroll.setBounds(87, 109, 527, 295);
		panel.add(scroll);
		table = new JTable(tableModel) {
			
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 2|| column == 3|| column == 4; // todas las celdas son editables salvo el codigo
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
		/*ACTUALIZAR LA BASE DE DATOS CON LOS CAMBIOS QUE SE HAGAN EN LA TABLA*/
		/*table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				
				
				int fila = e.getFirstRow();
				String cod = (String)tableModel.getValueAt(fila, 0);
				String nom = (String)tableModel.getValueAt(fila, 1);
				String d = (String)tableModel.getValueAt(fila, 2);
				String cant = (String)tableModel.getValueAt(fila, 3);
				String prec = (String)tableModel.getValueAt(fila, 3);
				
				producto.get(fila).setcod(cod);
				producto.get(fila).setnom(nom);
				producto.get(fila).setdesc(d);
				producto.get(fila).setCant(cant);
				producto.get(fila).setPrecio(prec);
				Producto m = producto.get(fila);
				
				Client cliente = ClientBuilder.newClient();
				final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
				
				final WebTarget modificarProducto = appTarget.path("modificarProducto");
				List<String> modificar = new ArrayList<>();
				modificar.add(m.getCodigo());
				modificar.add(m.getNombre());
				modificar.add(m.getDescripcion());
          
				modificarProducto.request().post(Entity.entity(modificar, MediaType.APPLICATION_JSON));
				//productos.remove(productoSeleccionado);
				JOptionPane.showMessageDialog(null, "Producto borrado");
			}
		});*/
		
		scroll.setViewportView(table);
		
		JButton btnAnadirProducto = new JButton("Añadir Producto");
		btnAnadirProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnadirProducto.setBackground(Color.YELLOW);
		btnAnadirProducto.setBounds(338, 472, 137, 29);
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
		btnModificarProducto.setBounds(485, 472, 151, 29);
		btnModificarProducto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Selecciona el producto a modificar y escribe los nuevos valores");
			}
		});	
		contentPane.add(btnModificarProducto);
		
		JButton btnAtras = new JButton("Volver");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtras.setBackground(Color.YELLOW);
		btnAtras.setBounds(44, 473, 137, 30);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaPanelGerente(usuario);
			}
		});
		
		contentPane.add(btnAtras);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					Product productoSeleccionado = productos.get(row);
					int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres Borrar este producto?", "Confirmar",
							JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						
						//borrar el producto seleccionado
						Client cliente = ClientBuilder.newClient();
						final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
						
	 	                final WebTarget borrarProducto = appTarget.path("borrarProducto");
	 	              
	 	                borrarProducto.request().post(Entity.entity(productoSeleccionado.getCodigo(), MediaType.APPLICATION_JSON));
						//productos.remove(productoSeleccionado);
	 	                JOptionPane.showMessageDialog(null, "Producto borrado");
					}
				}
			}
		});
		

	}
}