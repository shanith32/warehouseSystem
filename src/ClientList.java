
// package com.warehouse;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class ClientList implements Serializable {

	private static ClientList clientList;
	private static final long serialVersionUID = 1L;
	private List<Client> clients = new LinkedList<Client>();

	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} else {
			return clientList;
		}
	}

	public Client searchClient(String potentialClientID) {
		Iterator<Client> clientsIterator = clients.iterator();
		while (clientsIterator.hasNext()) {
			Client pClient = (Client) clientsIterator.next();
			if (pClient.getClientID().equals(potentialClientID)) {
				return pClient;
			}
		}
		return null;
	}

	public boolean findClient(String potentialClientID) {
		boolean found = false;
		Iterator<Client> clientsIterator = clients.iterator();
		while (clientsIterator.hasNext()) {
			Client pClient = (Client) clientsIterator.next();
			if (pClient.getClientID().equals(potentialClientID)) {
				found = true;
				return found;
			}
		}
		return found;
	}

	public int getSize() {
		return clients.size();
	}

	public boolean addClient(Client c) {
		clients.add(c);
		return true;
	}

	public boolean removeClient(Client c) {
		clients.remove(c);
		return true;
	}

	public Iterator getClients() {
		return clients.iterator();
	}

	public boolean isEmpty() {
		if (clients.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		String listOfClients = "List of Clients\n";
		Iterator<Client> clientsIterator = clients.iterator();
		while (clientsIterator.hasNext()) {
			Client client = (Client) clientsIterator.next();
			listOfClients += client.toString() + "\n";
		}
		return listOfClients;
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(clientList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (clientList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (clientList == null) {
					clientList = (ClientList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

}