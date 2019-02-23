
// package com.warehouse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Warehouse warehouse;
	private ClientList clientList;
	private ProductList productlist;
	private ManufacturerList manufacturerlist;

	private Warehouse() {
		clientList = clientList.instance();
		productlist = ProductList.instance();
		manufacturerlist = ManufacturerList.instance();
	}

	public static Warehouse instance() {
		if (warehouse == null) {
			ClientIDServer.instance(); // instantiate all singletons
			ManufacturerIdServer.instance();
			ProductIdServer.instance();
			return (warehouse = new Warehouse());
		} else {
			return warehouse;
		}
	}

	public boolean addClient(String name, String address, String phone) {
		Client tempClient = new Client(name, address, phone);
		return clientList.addClient(tempClient);
	}

	public Product addProduct(String name, String category) {
		Product product = new Product(name, category);
		if (productlist.insertProduct(product)) {
			return (product);
		}
		return null;
	}

	public Manufacturer addManufacturer(String name, String address) {
		Manufacturer manufacturer = new Manufacturer(name, address);
		if (manufacturerlist.insertManufacturer(manufacturer)) {
			return (manufacturer);
		}
		return null;
	}

	public Iterator getClients() {
		return clientList.getClients();
	}

	public Iterator getProducts() {
		return productlist.getProducts();
	}

	public Iterator getManufacturers() {
		return manufacturerlist.getManufacturers();
	}

	public static Warehouse retrieve() {
		try {
			FileInputStream file = new FileInputStream("WarehouseData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			ClientIDServer.retrieve(input);
			return warehouse;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("WarehouseData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(warehouse);
			output.writeObject(ClientIDServer.instance());
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(warehouse);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			input.defaultReadObject();
			if (warehouse == null) {
				warehouse = (Warehouse) input.readObject();
			} else {
				input.readObject();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
