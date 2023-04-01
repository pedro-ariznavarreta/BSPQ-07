package es.deusto.spq.supermarket.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;

import javax.swing.JTextField;

public class VentanaGerente extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGerente frame = new VentanaGerente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaGerente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana del gerente");

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText("Esta es la ventana del gerente");
		setVisible(true);
	}

}
