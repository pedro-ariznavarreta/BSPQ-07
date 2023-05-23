package es.deusto.spq.supermarket.client;
/** @package es.deusto.spq.supermarket.client
*/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class VentanaTrabajador extends JFrame {

	private JPanel contentPane;
	private JTextField Te;

	/**
	 * Create the frame.
	 */
	public VentanaTrabajador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Ventana trabajador");

		setContentPane(contentPane);
		
		Te = new JTextField();
		contentPane.add(Te);
		Te.setColumns(10);
		Te.setText("Esta es la ventana del trabajador");
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		setVisible(true);
	}

}
