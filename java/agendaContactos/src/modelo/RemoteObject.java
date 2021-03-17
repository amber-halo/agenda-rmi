package modelo;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RemoteObject implements Remote {
//	static MongoDatabase database;
	static MongoCollection<Document> agenda;
	static MongoCollection<Document> usuarios;
	
	public RemoteObject() {
		MongoClient mongoClient = MongoClients.create(
			    "mongodb+srv://saul-rw:aBqPZS4ZuMkxPGc3@cluster0.0aw1j.mongodb.net/agenda?retryWrites=true&w=majority");
		
		MongoDatabase database = mongoClient.getDatabase("agenda");
		agenda = database.getCollection("agendas");
		usuarios = database.getCollection("usuarios");
	}

	@Override
	public void imprimirMensaje() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Ejemplo de RMI");
	}

	@Override
	public boolean iniciarSesion(String usuario, String contrasena) throws RemoteException {
		BasicDBObject query = new BasicDBObject();
		query.put("nombre", usuario);
		query.put("contrasena", contrasena);
		Document doc = usuarios.find(query).first();
//		System.out.println(doc.toJson());
		return doc.isEmpty();
	}

	@Override
	public String mostrar(String usuario) throws RemoteException {
		BasicDBObject query = new BasicDBObject();
		query.put("usuario", usuario);
		String salida = "";
		for (Document cur : agenda.find(query)) {
//			System.out.println(cur.toJson());
//			salida += cur.toJson();
			salida += cur.getString("nombre") + " - " + cur.getString("compania") + " - " + cur.getString("titulo") + " - "
					+ cur.getString("movil") + " - " + cur.getString("trabajo") + "\n";
		}
		return salida;
		
	}

	@Override
	public void agregar(Document doc) throws RemoteException {
		agenda.insertOne(doc);
//		JOptionPane.showMessageDialog(null, "Contacto agregado con éxito.");
	}
	
}
