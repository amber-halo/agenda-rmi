package modelo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		try {
			Registry registry =  LocateRegistry.getRegistry(8000);
			
			Remote stub = (Remote) registry.lookup("Imprimir");
			
			stub.imprimirMensaje();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
