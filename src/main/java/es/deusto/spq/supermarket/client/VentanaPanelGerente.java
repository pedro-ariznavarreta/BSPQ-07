package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.util.logging.Logger;
import javax.swing.JButton;


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
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
/**
* VentanaPanelGerente sirve para seleccionar que tipo de tarea quieres hacer con el admin
* puedes registrar nuevos trabajador, tambien nuevos gerentes y tambien cambiar los diferentes porductos
* 
*  * @author JavierP
 * @version 1.0
 * @since 2023-05-20
*
*/
public class VentanaPanelGerente extends JFrame {

	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JPanel contentPane;
	private static Usuario usuario;
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/rest/resource");
	final WebTarget productAllTarget = appTarget.path("allP");
	// final WebTarget cestaTarget = appTarget.path("cesta");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario u = new Usuario();
					VentanaPanelGerente frame = new VentanaPanelGerente(u);
					frame.setVisible(true);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	public VentanaPanelGerente(Usuario usuarioValidado) {
		usuario = usuarioValidado;
		initialize();
	}


	
	private void initialize() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 576);
		setSize(800,200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));

		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.NORTH);
		panelCentral.setLayout(new GridLayout(2, 3, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(Color.BLACK);
		
		JLabel PanelGerente = new JLabel("PANEL DE CONTROL DEL GERENTE");
		PanelGerente.setForeground(Color.WHITE);
		panel_1.add(PanelGerente);
		panelCentral.add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panelCentral.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelRegistrar = new JPanel();
		panelRegistrar.setBackground(Color.BLACK);
		panel.add(panelRegistrar);
		
		JButton BotonRegTrab = new JButton("REGISTRAR TRABAJADOR");
		BotonRegTrab.setForeground(Color.BLACK);
		panelRegistrar.add(BotonRegTrab);
		BotonRegTrab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BotonRegTrab.setBackground(Color.YELLOW);
		BotonRegTrab.setBounds(301, 472, 137, 29);
		BotonRegTrab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCuentaTrabajador();
				
			}
		});
		
		JPanel panelRegistrarGerente = new JPanel();
		panelRegistrarGerente.setBackground(Color.BLACK);
		panel.add(panelRegistrarGerente);
		
		JButton BotonRegGer = new JButton("REGISTRAR GERENTE");
		panelRegistrarGerente.add(BotonRegGer);
		BotonRegGer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BotonRegGer.setBackground(Color.YELLOW);
		BotonRegGer.setBounds(301, 472, 137, 29);
		BotonRegGer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaCrearCuentaGerente();
			}
		});
		
		JPanel panelAjustes = new JPanel();
		panelAjustes.setBackground(Color.BLACK);
		panel.add(panelAjustes);
		
		JButton botonProductos = new JButton("AJUSTES DE PRODUCTOS");
		panelAjustes.add(botonProductos);
		botonProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonProductos.setBackground(Color.YELLOW);
		botonProductos.setBounds(301, 472, 137, 29);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_2.add(panel_3);
		
		JButton BotonCerrarSesion = new JButton("CERRAR SESION");
		BotonCerrarSesion.setForeground(Color.BLACK);
		BotonCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BotonCerrarSesion.setBackground(Color.YELLOW);
		BotonCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaLogin logout = new VentanaLogin();
				logout.setVisible(true);
				dispose();
			}
		});
		panel_3.add(BotonCerrarSesion);
		botonProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario();
				new VentanaGerente(u);
				dispose();
			}
		});
		
		
	

	}
}