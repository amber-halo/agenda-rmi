package modelo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends RemoteObject {
	public Server() {}
	public static void main(String[] args) {
		try {
			RemoteObject obj = new RemoteObject();
			
			Remote stub = (Remote) UnicastRemoteObject.exportObject(obj, 0);
			
			Registry registry = LocateRegistry.createRegistry(8000);
			
			registry.bind("Imprimir", stub);
			System.err.println("Servidor listo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
