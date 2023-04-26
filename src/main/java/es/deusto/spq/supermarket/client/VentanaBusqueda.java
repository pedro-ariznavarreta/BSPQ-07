package es.deusto.spq.supermarket.client;

import java.awt.BorderLayout;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
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
import es.deusto.spq.supermarket.server.jdo.Producto;
import es.deusto.spq.supermarket.server.jdo.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaBusqueda extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private JTextField textBuscador;
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private DefaultTableModel tableModel_ofertas = new DefaultTableModel();
	private static Usuario usuario;
	private static int cantidad;
	private static List<Producto> productos;
	
	

	private JPanel panel;
	
	private JButton btnCesta;
	
	
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	//final WebTarget cestaTarget = appTarget.path("cesta");
	private JTable table_ofertas;
	/**
	 * Launch the application.
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
	
	public List<Producto> busquedaProd(String producto) {
		List<Producto> productos = null;

		if (producto.equals("") ) {
			GenericType<List<Producto>> genericType = new GenericType<List<Producto>>() {
			};
			productos = productAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		} else {
			WebTarget productNomTarget = appTarget.path("nomP").queryParam("nombre", producto);
			GenericType<List<Producto>> genericType = new GenericType<List<Producto>>() {
			};
			productos = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		}

		return productos;

	}
	
	

	/**
	 * Create the frame.
	 * @param usuario2 
	 */
	private void initialize() {
		//WebTarget contarTarget = cestaTarget.path("contar").queryParam("Usuario", usuario.getUsername());
		//GenericType<Integer> genericType7 = new GenericType<Integer>() {};
		//cantidad = contarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
		
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
				for(int i = 0; i< tableModel.getRowCount(); i++) {
					tableModel.removeRow(i);
				}
				productos = busquedaProd(textBuscador.getText());
				for (Producto p : productos) {
					tableModel.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getDescripcion(), p.getCantidad(), p.getPrecio()});
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
		
		JButton btnVolver = new JButton("Finalizar");
		btnVolver.setBackground(new Color(255, 255, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaLogin log = new VentanaLogin();
				log.setVisible(true);
				setVisible(false);
				VentanaLogin v2 = new VentanaLogin();
				v2.setVisible(true);
				
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("Favoritos");
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setBounds(525, 42, 89, 23);
		contentPane.add(btnNewButton);
		btnVolver.setBounds(44, 468, 91, 32);
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnVolver);
		
		JButton btnAñadir = new JButton("Añadir a la cesta");
		btnAñadir.setBackground(new Color(255, 255, 0));
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String nombreusuario = usuario.getUsername();
//				String nombreproducto = productos.get(table.getSelectedRow()).getNombre();
//				WebTarget anadirTarget = cestaTarget.path("anadir").queryParam("Producto", nombreproducto).queryParam("Usuario", nombreusuario);
//				GenericType<Boolean> genericType5 = new GenericType<Boolean>() {};
//				boolean respuesta = anadirTarget.request(MediaType.APPLICATION_JSON).get(genericType5);
//
//				WebTarget contarTarget = cestaTarget.path("contar").queryParam("Usuario", nombreusuario);
//				GenericType<Integer> genericType7 = new GenericType<Integer>() {};
//				cantidad = contarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
//				btnCesta.setText("Cesta : " + cantidad);
//				if (respuesta != true) {
//					System.out.println("El producto no se añade a la cesta");
//				}
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
		
		table = new JTable((TableModel) null);
		table.setModel(tableModel);
		tableModel.addColumn("Codigo");
	    tableModel.addColumn("Nombre");
	    tableModel.addColumn("Descripcion");
	    tableModel.addColumn("Stock");
	    tableModel.addColumn("Precio");
		scroll.setViewportView(table);
	
		
		
		
		
	}
}