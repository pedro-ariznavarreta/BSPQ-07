package es.deusto.spq.supermarket.client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.supermarket.server.jdo.Compra;
import es.deusto.spq.supermarket.server.jdo.Product;
import es.deusto.spq.supermarket.server.jdo.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaCompras extends JFrame {

	private JPanel contentPane;


	  /**
	   * Logger global para registrar información de la aplicación.
	   */
	  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	  
	  /**
	   * Lista de compras del usuario.
	   */
	  List<Compra> compras;

	  /**
	   * Usuario actualmente verificado en la aplicación.
	   */
	  private static Usuario usuario;

	  /**
	   * Cliente para consumir el servicio REST.
	   */
	  Client cliente = ClientBuilder.newClient();
	  
	  /**
	   * Endpoint principal del servicio REST.
	   */
	  final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");

	  /**
	   * Endpoint para recuperar todos los productos.
	   */
	  final WebTarget productAllTarget = appTarget.path("allP");

	  /**
	   * Inicia la aplicación.
	   * 
	   * @param args Argumentos de la línea de comandos.

	/**
	 * Launch the application.
	 */
	
	  
	
	private Compra toStringCasero(Compra a) {
		Compra buena = new Compra(a.getProductos(), a.getUsuario(), a.getFecha());
		return buena;
		
	}

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
		setBounds(100, 100, 670, 475);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JList list = new JList();
		
		//usuario.setUsername("Mikel");
		
		
		WebTarget comprasTar = appTarget.path("obtenerCompras").queryParam("Usuario", usuario.getUsername());
		GenericType<List<Compra>> genericType7 = new GenericType<List<Compra>>() {
		};
		String nombreUsu = usuario.getUsername();
		final List<Compra> compras = comprasTar.request(MediaType.APPLICATION_JSON).get(genericType7);
		
		for (int i = 0; i < compras.size(); i++) {
			System.out.println(compras.get(i).getProductos());
		}
		
		
		final DefaultListModel<String> DLM = new DefaultListModel<>();
		for (Compra c : compras) {
			Compra a = new Compra(c.getProductos(), c.getUsuario(), c.getFecha());
			Date fechaBuena = new Date(Long.parseLong(c.getFecha()));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");  
			String daterString = dateFormat.format(fechaBuena);  
			a.setFecha(daterString);
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCompras, GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 562, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(297, Short.MAX_VALUE)
					.addComponent(volver)
					.addGap(290))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(lblCompras, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(volver)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	}

