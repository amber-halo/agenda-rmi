package modelo;

import java.rmi.RemoteException;

import org.bson.Document;

public interface Remote extends java.rmi.Remote {
	boolean iniciarSesion(String usuario, String contrasena) throws RemoteException;
	String mostrar(String usuario) throws RemoteException;
	void agregar(Document doc) throws RemoteException;
	
	void imprimirMensaje() throws RemoteException;
}
