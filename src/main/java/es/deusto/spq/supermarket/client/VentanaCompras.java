package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.jdo.Compra;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaCompras extends JFrame {

	/*
	 * Mediante esta venta se muestra el historial de compras del cliente
	 */

	private static final long serialVersionUID = 1L;

	  /**
	   * Lista de compras del usuario.
	   */
	  List<Compra> compras;

	  /**
	   * Cliente para consumir el servicio REST.
	   */
	  Client cliente = ClientBuilder.newClient();
	  
	  /**
	   * Endpoint principal del servicio REST.
	   */
	  final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");


	  /**
	 * Create the frame.
	 */
	public VentanaCompras(Usuario usuario) {
		//Usuario usua = new Usuario("Mikel", "Prueba", "Inigo@prueba", 0, 0);
		getContentPane().setBackground(new Color(0, 0, 0));
		initialice(usuario);
	

	}
	private void initialice(Usuario usuario) {
		setVisible(true);
		setBounds(100, 100, 800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JList<String> list = new JList<String>();
		
		
		WebTarget comprasTar = appTarget.path("obtenerCompras").queryParam("Usuario", usuario.getUsername());
		GenericType<List<Compra>> genericType7 = new GenericType<List<Compra>>() {
		};
		final List<Compra> compras = comprasTar.request(MediaType.APPLICATION_JSON).get(genericType7);
	     
		
		/*
		 * Muestra en un DefaultListModel, el usuario, 
		 * la fecha en la que se compra el producto y los productos comprados
		 */
		
		final DefaultListModel<String> DLM = new DefaultListModel<>();
		for (Compra c : compras) {
			Compra a = new Compra(c.getProductos(), c.getUsuario(), c.getFecha());
			String mostrarBien = "" +a.getUsuario()+" " + a.getFecha()+ "\t\t\t " + a.getProductos();
			DLM.addElement(mostrarBien);
		}
		list.setModel(DLM);

		JLabel lblCompras = new JLabel("COMPRAS ANTERIORES");
		lblCompras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompras.setForeground(new Color(255, 255, 0));
		lblCompras.setFont(new Font("Tahoma", Font.PLAIN, 18));

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
		
	

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCompras, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(427, Short.MAX_VALUE)
					.addComponent(volver)
					.addGap(290))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(lblCompras, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addComponent(volver)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	}

