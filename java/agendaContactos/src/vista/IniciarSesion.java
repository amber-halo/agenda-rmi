package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IniciarSesion extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtContrasena;
	private JLabel labelError;
	
//	static IniciarSesion frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
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
	public IniciarSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar sesi\u00F3n");
		panel.add(lblIniciarSesin);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 11, 46, 14);
		panel_1.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(10, 40, 404, 20);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 71, 127, 14);
		panel_1.add(lblContrasea);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(10, 96, 404, 20);
		panel_1.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		JButton btnIniciarSesin = new JButton("Iniciar sesi\u00F3n");
		btnIniciarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtUsuario.getText().equals("") && !txtContrasena.getText().equals("")) {
					Cliente cliente = new Cliente();
					
					cliente.autenticar(txtUsuario.getText(), txtContrasena.getText());
					
					cliente.setVisible(true);
					cliente.ocultarInicio();
				} else {
					labelError.setText("Datos incorrectos");
				}
			}
		});
		btnIniciarSesin.setBounds(152, 182, 127, 23);
		panel_1.add(btnIniciarSesin);
		
		labelError = new JLabel("");
		labelError.setBounds(10, 127, 404, 14);
		panel_1.add(labelError);
	}
}
