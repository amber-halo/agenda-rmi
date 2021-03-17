package vista;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import modelo.Remote;
import modelo.RemoteObject;

public class Servidor extends RemoteObject {
	public Servidor() {}

	public static void main(String[] args) {
		try {
			RemoteObject obj = new RemoteObject();
			
			Remote stub = (Remote) UnicastRemoteObject.exportObject(obj, 0);
			
			Registry registry = LocateRegistry.createRegistry(8000);
			
			registry.bind("Agenda", stub);
			System.err.println("Servidor listo");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
