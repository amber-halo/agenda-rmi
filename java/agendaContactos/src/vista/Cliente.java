package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import modelo.Remote;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	
	static IniciarSesion iniciarSesion;
	
	private boolean logged = false;
	
	static Remote stub;
	private JTextArea textAreaContactos;
	
//	static Remote stub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registry registry =  LocateRegistry.getRegistry(8000);
					
					stub = (Remote) registry.lookup("Agenda");
					
//					stub.imprimirMensaje();
					
					iniciarSesion = new IniciarSesion();
					iniciarSesion.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (logged) {
					try {
						String resultado = stub.mostrar("saul");
						
						textAreaContactos.setText(resultado);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		panel.add(btnMostrar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgregarContacto agregarContacto = new AgregarContacto(stub, "saul");
				agregarContacto.setVisible(true);
			}
		});
		panel.add(btnAgregar);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		textAreaContactos = new JTextArea();
		textAreaContactos.setBounds(0, 11, 424, 196);
		panel_1.add(textAreaContactos);
	}
	
	public void autenticar(String usuario, String contrasena) {
		try {
			stub.iniciarSesion(usuario, contrasena);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ocultarInicio() {
		iniciarSesion.setVisible(false);
		logged = true;
	}
}
