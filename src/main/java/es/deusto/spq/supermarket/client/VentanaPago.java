package es.deusto.spq.supermarket.client;

import java.util.*;

import java.util.logging.Logger;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.jdo.Pedido;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;
import es.deusto.spq.supermarket.server.jdo.Visa;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Color;

public class VentanaPago extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	public JLabel textPrecio;

	private static List<Product> productos;
	private static Usuario usuario;

	private JComboBox<String> comboBox;
	private JTextField textCV;
	private JTextField textFechaCaducidad;
	private JTextField textTitular;
	private JTextField textNumeroTarjeta;

	private Panel panelVisa;
	private JLabel lblNumerotarjeta;
	private JLabel lblPrecio;
	private JLabel lblTitular;
	private JLabel lblFechaCaducidad;
	private JLabel lblCV;

	private JList<Product> list;
	DefaultListModel<Product> modelProducto = new DefaultListModel<>();

	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");

	private JTextField textDireccion;
	private JTextField textCupon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPago frame = new VentanaPago(usuario, productos);
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
	public VentanaPago(Usuario usuarioVerificado, List<Product> productosSeleccionados) {

		usuario = usuarioVerificado;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeleccion = new JLabel("Seleccionar metodo de pago: ");
		lblSeleccion.setForeground(new Color(255, 255, 0));
		lblSeleccion.setBackground(new Color(0, 0, 0));
		lblSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeleccion.setBounds(56, 34, 248, 35);
		contentPane.add(lblSeleccion);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Elige uno...", "Visa" }));
		comboBox.setToolTipText("");
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(275, 43, 96, 21);
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectionPago = comboBox.getSelectedIndex();
				switch (selectionPago) {
				case 0:
					break;
				case 1:
					panelVisa.setVisible(true);
					lblNumerotarjeta.setText("Numero de tarjeta: ");
					lblTitular.setText("Titular: ");
					lblFechaCaducidad.setVisible(true);
					lblCV.setVisible(true);
					textCV.setVisible(true);
					textFechaCaducidad.setVisible(true);
					textCV.setText("");
					textFechaCaducidad.setText("");
					textNumeroTarjeta.setText("");
					textTitular.setText("");
					textDireccion.setText("");
					break;
				}
			}
		});

		list = new JList<Product>();
		list.setBounds(461, 46, 200, 294);
		contentPane.add(list);
		list.setModel(modelProducto);

		lblPrecio = new JLabel("Precio:");
		lblPrecio.setForeground(new Color(255, 255, 0));
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrecio.setBounds(461, 409, 59, 21);
		contentPane.add(lblPrecio);

		textPrecio = new JLabel();
		textPrecio.setBounds(524, 412, 137, 19);
		contentPane.add(textPrecio);

		JButton btnPagar = new JButton("PAGAR");
		btnPagar.setBackground(new Color(255, 255, 0));
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectionPago = comboBox.getSelectedIndex();
				if (selectionPago == 1 || selectionPago == 0) { // Índice correspondiente a Visa
					String numeroTarjeta = textNumeroTarjeta.getText();
					String titular = textTitular.getText();
					String fechaCaducidad = textFechaCaducidad.getText();
					String cv = textCV.getText();

					if (numeroTarjeta.isEmpty() || titular.isEmpty() || fechaCaducidad.isEmpty() || cv.isEmpty()) {
						// Mostrar un mensaje de error o realizar alguna acción indicando que faltan
						// datos
						// Aquí puedes mostrar un JOptionPane, un JLabel con un mensaje de error, etc.
						JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos de la tarjeta",
								"Error", JOptionPane.ERROR_MESSAGE);
						return; // Detener la ejecución del método si faltan datos
					}
				}

				generarFicheroFactura();
				dispose();
				JOptionPane.showMessageDialog(null, "Su compra se ha hecho de manera correcta", "Correcto",
						JOptionPane.INFORMATION_MESSAGE);
				VentanaLogin vt = new VentanaLogin();
				vt.setVisible(true);

			}
		});
		btnPagar.setBounds(298, 410, 85, 21);
		contentPane.add(btnPagar);

		panelVisa = new Panel();
		panelVisa.setBounds(56, 89, 344, 298);
		contentPane.add(panelVisa);
		panelVisa.setLayout(null);
		panelVisa.setVisible(false);

		lblNumerotarjeta = new JLabel("Numero de tarjeta:");
		lblNumerotarjeta.setForeground(new Color(255, 255, 0));
		lblNumerotarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumerotarjeta.setBounds(10, 38, 142, 21);
		panelVisa.add(lblNumerotarjeta);

		lblTitular = new JLabel("Titular: ");
		lblTitular.setForeground(new Color(255, 255, 0));
		lblTitular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitular.setBounds(10, 86, 137, 21);
		panelVisa.add(lblTitular);

		lblFechaCaducidad = new JLabel("Fecha caducidad: ");
		lblFechaCaducidad.setForeground(new Color(255, 255, 0));
		lblFechaCaducidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaCaducidad.setBounds(10, 133, 137, 21);
		panelVisa.add(lblFechaCaducidad);

		lblCV = new JLabel("CV: ");
		lblCV.setForeground(new Color(255, 255, 0));
		lblCV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCV.setBounds(10, 178, 137, 21);
		panelVisa.add(lblCV);

		textCV = new JTextField();
		textCV.setColumns(10);
		textCV.setBounds(162, 181, 137, 19);
		panelVisa.add(textCV);

		textFechaCaducidad = new JTextField();
		textFechaCaducidad.setColumns(10);
		textFechaCaducidad.setBounds(162, 136, 137, 19);
		panelVisa.add(textFechaCaducidad);

		textTitular = new JTextField();
		textTitular.setColumns(10);
		textTitular.setBounds(162, 89, 137, 19);
		panelVisa.add(textTitular);

		textNumeroTarjeta = new JTextField();
		textNumeroTarjeta.setColumns(10);
		textNumeroTarjeta.setBounds(162, 41, 137, 19);
		panelVisa.add(textNumeroTarjeta);

		JLabel lblDireccion = new JLabel("Dirección: ");
		lblDireccion.setForeground(new Color(255, 255, 0));
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(10, 230, 137, 21);
		panelVisa.add(lblDireccion);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(162, 233, 137, 19);
		panelVisa.add(textDireccion);

		JLabel lblCupon = new JLabel("Cupon:");
		lblCupon.setForeground(new Color(255, 255, 0));
		lblCupon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCupon.setBounds(461, 368, 59, 21);
		contentPane.add(lblCupon);

		textCupon = new JTextField();
		textCupon.setColumns(10);
		textCupon.setBounds(524, 371, 137, 19);
		contentPane.add(textCupon);

		textCupon.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarPrecioConCupon();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarPrecioConCupon();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				actualizarPrecioConCupon();
			}
		});

	}

	private void actualizarPrecioConCupon() {
		String cupon = textCupon.getText();
		double precio = Double.parseDouble(textPrecio.getText());

		if (cupon.equals("111") || cupon.equals("222") || cupon.equals("333")) {
			int confirm = JOptionPane.showConfirmDialog(null, "¿Desea canjear el cupón?", "Canjear cupón",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				double descuento = precio * 0.15;
				precio -= descuento;
				textCupon.setEditable(false);
			}
		}

		textPrecio.setText(String.valueOf(precio));

	}
		
	private void generarFicheroFactura() {
		   JFileChooser fileChooser = new JFileChooser();
		    int seleccion = fileChooser.showSaveDialog(this);
		    if (seleccion == JFileChooser.APPROVE_OPTION) {
		        File file = fileChooser.getSelectedFile();
		        try (PrintWriter writer = new PrintWriter(file)) {
		            for (int i = 0; i < modelProducto.getSize(); i++) {
		                Product producto = modelProducto.getElementAt(i);
		                writer.println(producto.toString());
		            }
		            writer.println("Precio: " + textPrecio.getText());
		            writer.println("Cupon: " + textCupon.getText());
		            writer.println("Datos de la tarjeta:");
		            writer.println("Número de tarjeta: " + textNumeroTarjeta.getText());
		            writer.println("Titular: " + textTitular.getText());
		            writer.println("Fecha de caducidad: " + textFechaCaducidad.getText());
		            writer.println("CV: " + textCV.getText());
		            writer.flush();
		            writer.close();
		            
		        } catch (FileNotFoundException e) {
		            LOGGER.severe(e.getMessage());
		        }
		    }
}
}


