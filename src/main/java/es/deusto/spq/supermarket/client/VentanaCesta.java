package es.deusto.spq.supermarket.client;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;


import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import java.awt.Color;

public class VentanaCesta extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static List<Product> productos;
	private static Usuario usuario;

	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCesta window = new VentanaCesta(usuario);
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
	public VentanaCesta(Usuario usuarioVerificado) {
		getContentPane().setBackground(new Color(0, 0, 0));
		usuario = usuarioVerificado;
		initialize();
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 670, 475);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JList list = new JList();

		WebTarget buscarTarget = appTarget.path("buscar").queryParam("Usuario", usuario.getUsername());
		System.out.println(usuario.getUsername());
		GenericType<List<Product>> genericType7 = new GenericType<List<Product>>() {
		};
		final List<Product> product = buscarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
		System.out.println(product);
		final DefaultListModel<Product> DLM = new DefaultListModel<>();
		for (Product p : product) {
			DLM.addElement(p);
		}
		list.setModel(DLM);

		JLabel lblCesta = new JLabel("CESTA");
		lblCesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCesta.setForeground(new Color(255, 255, 0));
		lblCesta.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton btnComprar = new JButton("PAGAR");
		btnComprar.setForeground(new Color(0, 0, 0));
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usenv = usuario;
				VentanaPago ventana = new VentanaPago(usenv, productos);
				double precio = 0.0;
				for (Product p : product) {
					ventana.modelProducto.addElement(p);
					precio += p.getPrecio();
				}
				ventana.textPrecio.setText(String.valueOf(precio));
				ventana.setVisible(true);
				dispose();
			}
		});
		btnComprar.setBackground(new Color(255, 255, 0));

		JButton btnVaciarCesta = new JButton("VACIAR CESTA");
		btnVaciarCesta.setForeground(new Color(0, 0, 0));
		btnVaciarCesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				WebTarget borrarTarget = appTarget.path("borrar");
				borrarTarget.request().post(Entity.entity(usuario, MediaType.APPLICATION_JSON));

				WebTarget buscarTarget = appTarget.path("buscar").queryParam("Usuario", usuario.getUsername());
				GenericType<List<Product>> genericType7 = new GenericType<List<Product>>() {
				};
				final List<Product> product = buscarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
				final DefaultListModel<Product> DLM = new DefaultListModel<>();
				for (Product p : product) {
					DLM.addElement(p);
				}
				list.setModel(DLM);
			}
		});
		btnVaciarCesta.setBackground(new Color(255, 255, 0));

		JButton volver = new JButton("VOLVER");
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario us = usuario;
				VentanaBusqueda vb = new VentanaBusqueda(us);
				vb.setVisible(true);
				setVisible(false);
			}
		});
		volver.setBackground(new Color(255, 255, 0));

		JButton btnNewButton = new JButton("Cupones");
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "Cupones disponibles:\n\n";
		        mensaje += "111\n";
		        mensaje += "222\n";
		        mensaje += "333\n";
		        
		        // Mostrar el JOptionPane con los cupones
		        JOptionPane.showMessageDialog(null, mensaje, "Cupones", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCesta, GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(216)
							.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnVaciarCesta, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 562, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(volver)
									.addPreferredGap(ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
									.addComponent(btnNewButton)
									.addGap(65)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(lblCesta, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnVaciarCesta, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(volver)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
