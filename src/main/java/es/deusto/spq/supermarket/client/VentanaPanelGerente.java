package es.deusto.spq.supermarket.client;

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
import es.deusto.spq.supermarket.server.jdo.Producto;
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

public class VentanaPanelGerente extends JFrame {
	/**
	 * 
	 */
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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));

		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.NORTH);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1);
		
		JLabel PanelGerente = new JLabel("Panel de control del gerente");
		panel_1.add(PanelGerente);
		
		JPanel panel = new JPanel();
		panelCentral.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton BotonRegTrab = new JButton("Registrar Trabajador");
		BotonRegTrab.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(BotonRegTrab);
		
		JButton BotonRegGer = new JButton("Regitrar Gerente");
		BotonRegGer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(BotonRegGer);
		
		JButton botonProductos = new JButton("Ajustes de productos");
		botonProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario();
				new VentanaGerente(u);
				dispose();
			}
		});
		panel.add(botonProductos);
		
		
	

	}
}