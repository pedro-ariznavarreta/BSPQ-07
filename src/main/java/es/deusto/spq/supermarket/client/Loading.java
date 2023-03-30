package es.deusto.spq.supermarket.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class Loading extends JFrame {

	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private JProgressBar progressBar = new JProgressBar();
	private JLabel lblEnviado;
	private JLabel lblEnviando;
	private JButton btnAceptar;
	private VentanaVerificarCodigo cod = new VentanaVerificarCodigo();
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading window = new Loading();
					window.setVisible(true);
				} catch (Exception e) {
					LOGGER.severe(e.getMessage());
				}
			}
		});
	}

	
	public Loading() {
		getContentPane().setBackground(new Color(0, 0, 0));
		setTitle("ENVIANDO CÓDIGO VERIFICACION");
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		progressBar.setForeground(new Color(233, 217, 27));
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 10));
		// INICIALIZAMOS LA BARRA QUE SIMULARA EL ENVIO DEL CODIGO AL CORREO
		progressBar.setBounds(53, 134, 309, 14);
		getContentPane().add(progressBar);
		progressBar.setStringPainted(true);
		new Thread(new Hilo()).start();
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(72, 24, 275, 64);
		getContentPane().add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("img/COOKMASTER.png");
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		lblNewLabel.setIcon(scaledIcon);

		// HAREMOS MAS VISUAL LA VENTANA MEDIANTE ESTOS JLABEL
		lblEnviando = new JLabel("Enviando código...");
		lblEnviando.setForeground(new Color(233, 217, 27));
		lblEnviando.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEnviando.setBounds(155, 109, 143, 14);
		getContentPane().add(lblEnviando);

		lblEnviado = new JLabel("Código enviado ✓");
		lblEnviado.setForeground(new Color(233, 217, 27));
		lblEnviado.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEnviado.setBounds(155, 109, 110, 14);
		getContentPane().add(lblEnviado);
		lblEnviado.setVisible(false);

		/**
		 * BOTON QUE APARECERA UNA VEZ SE HAYA LLEGADO AL 100% DE LA BARRA Este boton
		 * muestra la siguiente ventana para poder introducir codigo
		 */
		btnAceptar = new JButton("Introducir codigo");
		btnAceptar.setBackground(new Color(233, 217, 27));
		btnAceptar.setBounds(123, 171, 156, 34);
		getContentPane().add(btnAceptar);
		
		
		btnAceptar.setVisible(false);
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				cod.setVisible(true);
				int codigoverificacion1 = VentanaCrearCuenta.returcodigo();
				JOptionPane.showMessageDialog(null, "Tu codigo es"+" "+codigoverificacion1 ,
						"codigo", JOptionPane.DEFAULT_OPTION);
			}
		});

	}

	/**
	 * Comprueba que el valor de la barra esta en 100 y si es asi muestra y esconde
	 * los diferentes jlabels
	 */
	public void valor100() {
		// Comprueba el valor de la progressbar para mostrar unos labels u otros
		if (progressBar.getValue() == 100) {
			lblEnviado.setVisible(true);
			lblEnviando.setVisible(false);
			btnAceptar.setVisible(true);

		}
	}

	/**
	 * Mediante un hilo incrementamos el valor de la progessbar y a su vez
	 * implementamos el metodo valor100 explicado anteriormente
	 */
	public class Hilo implements Runnable {
		public void run() {
			for (int i = 0; i <= 100; i++) {
				progressBar.setValue(i);
				progressBar.repaint();
				valor100();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					LOGGER.severe(e.getMessage());
					Thread.currentThread().interrupt();
				}

			}

		}
	}

	{

	}
}
