import java.io.*;

public class OrderWithManufacturerIdServer implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCounter;
	private static OrderWithManufacturerIdServer server;

	public static OrderWithManufacturerIdServer instance() {
		if (server == null) {
			return (server = new OrderWithManufacturerIdServer());
		} else {
			return server;
		}
	}
	
	private OrderWithManufacturerIdServer() {
		idCounter = 1;
	}

	public int getId() {
		return idCounter++;
	}

	public static void retrieve(ObjectInputStream input) {
		try {
			server = (OrderWithManufacturerIdServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	private void writeObject(java.io.ObjectOutputStream output) throws IOException {
		try {
			output.defaultWriteObject();
			output.writeObject(server);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void readObject(java.io.ObjectInputStream input) throws IOException, ClassNotFoundException {
		try {
			input.defaultReadObject();
			if (server == null) {
				server = (OrderWithManufacturerIdServer) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return ("IdServer" + idCounter);
	}
}

