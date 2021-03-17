package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import modelo.Remote;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class AgregarContacto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCompania;
	private JTextField txtTitulo;
	private JTextField txtMovil;
	private JTextField txtTrabajo;
	
	static Remote stub;
	static String usuario = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarContacto frame = new AgregarContacto(null, "");
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
	public AgregarContacto(Remote stub, String usuario) {
		this.stub = stub;
		this.usuario = usuario;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 36, 170, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCompaia = new JLabel("Compa\u00F1ia");
		lblCompaia.setBounds(10, 67, 86, 14);
		panel.add(lblCompaia);
		
		txtCompania = new JTextField();
		txtCompania.setBounds(10, 92, 170, 20);
		panel.add(txtCompania);
		txtCompania.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 123, 86, 14);
		panel.add(lblTtulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 148, 170, 20);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblMvil = new JLabel("M\u00F3vil");
		lblMvil.setBounds(213, 11, 46, 14);
		panel.add(lblMvil);
		
		txtMovil = new JTextField();
		txtMovil.setBounds(213, 36, 201, 20);
		panel.add(txtMovil);
		txtMovil.setColumns(10);
		
		JLabel lblTrabajo = new JLabel("Trabajo");
		lblTrabajo.setBounds(213, 67, 46, 14);
		panel.add(lblTrabajo);
		
		txtTrabajo = new JTextField();
		txtTrabajo.setBounds(213, 92, 201, 20);
		panel.add(txtTrabajo);
		txtTrabajo.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Document doc = new Document("nombre", txtNombre.getText())
						.append("compania", txtCompania.getText())
						.append("titulo", txtTitulo.getText())
						.append("movil", txtMovil.getText())
						.append("trabajo", txtTrabajo.getText())
						.append("usuario", usuario);
				try {
					stub.agregar(doc);
					setVisible(false);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(170, 217, 89, 23);
		panel.add(btnAgregar);
	}

}
