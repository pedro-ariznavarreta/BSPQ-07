package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

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

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import java.awt.Color;
/**
 * En la VentanaCesta salen todos los productos seleccionados de la ventana anteriror, con la posiblidad de pagar
 * o de vaciar la cesta, tambien puedes visulizar los cupones disponibles que tiene el usuario
 * 
 *  * @author JavierP
 * @version 1.0
 * @since 2023-05-20
 */
public class VentanaCesta extends JFrame {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static List<Product> productos;
	private static Usuario usuario;

	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");

	/**
	 * Se crea la aplicacion
	 */
	public VentanaCesta(Usuario usuarioVerificado) {
		getContentPane().setBackground(new Color(0, 0, 0));
		usuario = usuarioVerificado;
		initialize();
	}
	
	/**
	 * Este método sirve para buscar los prodcutos
	 * @param el porducto para hacer la busqueda
	 * @return devuelve los productos encotrados
	 */

	public List<Product> busquedaProd(String producto) {
		List<Product> productos = null;
		GenericType<List<Product>> genericType = new GenericType<List<Product>>() {
		};
		if (producto.equals("")) {
			productos = productAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		} else {
			
			WebTarget productNomTarget = appTarget.path("nom").queryParam("nombre", producto);
			productos = productNomTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		}

		return productos;

	}

	/**
	 * Se iniciliza la vetana
	 */
	private void initialize() {

		setBounds(100, 100, 670, 475);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JList list = new JList();

		WebTarget buscarTarget = appTarget.path("buscar").queryParam("Usuario", usuario.getUsername());
		GenericType<List<Product>> genericType7 = new GenericType<List<Product>>() {
		};
		final List<Product> product = buscarTarget.request(MediaType.APPLICATION_JSON).get(genericType7);
		
		final DefaultListModel<Product> DLM = new DefaultListModel<>();
		for (Product p : product) {
			DLM.addElement(p);
		}
		list.setModel(DLM);

		JLabel lblCesta = new JLabel("CESTA");
		lblCesta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCesta.setForeground(new Color(255, 255, 0));
		lblCesta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		/**
		 * Este boton te lleva a la siguente ventana para pagar los productos seleccionados en la cesta
		 */

		JButton btnComprar = new JButton("PAGAR");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usenv = usuario;
				VentanaPago ventana = new VentanaPago(usenv, product);
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
		/**
		 * Este boton este boton elimina todos los productos de la cesta
		 */
		JButton btnVaciarCesta = new JButton("VACIAR CESTA");
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
		/**
		 * Este boton visuliza todos los cupones disponibles para el usuario
		 */
		JButton btnNewButton = new JButton("Cupones");
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cupones = "Cupones:\n111\n222\n333";
				 JOptionPane.showMessageDialog(btnNewButton, cupones);

			}
		});
		
		JButton btnEliminarProducto = new JButton("ELIMINAR PRODUCTO");
		btnEliminarProducto.setBackground(Color.YELLOW);
		btnEliminarProducto.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		            int indiceSeleccionado = list.getSelectedIndex();
		            if (indiceSeleccionado != -1) {
		                DefaultListModel<Product> modeloLista = (DefaultListModel<Product>) list.getModel();
		                Product productoSeleccionado = modeloLista.getElementAt(indiceSeleccionado);

		                // Envía una solicitud de eliminación al servidor
		                WebTarget eliminarTarget = appTarget.path("eliminar");
		                eliminarTarget.request().post(Entity.entity(productoSeleccionado.getCodigo(), MediaType.APPLICATION_JSON));

		                // Elimina el producto de la lista
		                modeloLista.remove(indiceSeleccionado);
		            }
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
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 562, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(volver)
									.addPreferredGap(ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
									.addComponent(btnNewButton)
									.addGap(65)))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(120)
					.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(btnEliminarProducto, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnVaciarCesta, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(127))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(lblCesta, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVaciarCesta, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(volver)
								.addComponent(btnNewButton)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnEliminarProducto, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
