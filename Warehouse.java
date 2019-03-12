
// package com.warehouse;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Warehouse warehouse;
	private ClientList clientList;
	private ProductList productList;
	private ManufacturerList manufacturerList;
	private ProductManufacturerList productManufacturerList;
	private ProcessedOrderList processedOrderList;
	private WaitList waitList;

	private Warehouse() {
		clientList = clientList.instance();
		productList = ProductList.instance();
		manufacturerList = ManufacturerList.instance();
		productManufacturerList = ProductManufacturerList.instance();
		processedOrderList = ProcessedOrderList.instance();
		waitList = WaitList.instance();
	}

	public static Warehouse instance() {
		if (warehouse == null) {
			ClientIDServer.instance(); // instantiate all singletons
			ManufacturerIdServer.instance();
			ProductIdServer.instance();
			//ProductManufacturerIdServer.instance();
			return (warehouse = new Warehouse());
		} else {
			return warehouse;
		}
	}

	public Client addClient(String name, String address, String phone) {
		Client tempClient = new Client(name, address, phone);
		if (clientList.addClient(tempClient)) {
			return (tempClient);
		}
		return null;
	}

	public Product addProduct(String name, String category) {
		Product product = new Product(name, category);
		if (productList.insertProduct(product)) {
			return (product);
		}
		return null;
	}

	public Manufacturer addManufacturer(String name, String address) {
		Manufacturer manufacturer = new Manufacturer(name, address);
		if (manufacturerList.insertManufacturer(manufacturer)) {
			return (manufacturer);
		}
		return null;
	}

	// Assign product to a manufacturer
	public boolean assignProductToManufacturer(String mid, String pid, Double price) {
		ProductManufacturer priceAssignment = new ProductManufacturer(mid, pid, price);
		return productManufacturerList.insertProductManufacturer(priceAssignment);
	}

	// Unassign product from a manufacturer
	public boolean unassignProductfromManufacturer(String mid, String pid) {
		return productManufacturerList.deleteAssignmentbyId(mid, pid);
	}

	// Get a list of suppliers for a product with price
	public java.util.List<String> getSupplierbyProduct(String pid) {
		return productManufacturerList.getManufacturersForProduct(pid);
	}

	// Get a list of products for a supplier
	public java.util.List<String> getProductbySupplier(String mid) {
		return productManufacturerList.getProductsForManufacturer(mid);
	}

	public Iterator getClients() {
		return clientList.getClients();
	}

	public Iterator getProducts() {
		return productList.getProducts();
	}

	public Iterator getManufacturers() {
		return manufacturerList.getManufacturers();
	}
	
	public Iterator getWaitlistedOrders() {
		return waitList.getOrders();
	}

	public void processOrder(Client client, ProductManufacturer productManufacturer, int quantity, Order order) {
		productManufacturer.setQuantity(productManufacturer.getQuantity() - quantity);
		client.setBalance(client.getBalance() + order.getTotal());
	}
	
	public int addOrder(String clientID, String productID, String manufacturerID, int quantity) {
		ProductManufacturer productManufacturer = productManufacturerList.searchProductManufacturer(productID, manufacturerID);
		Client client = clientList.searchClient(clientID);
		if (productManufacturer != null && client != null) {
			Order order = new Order(clientID, productManufacturer, quantity);
			if (productManufacturer.getQuantity() >= quantity) {
				processedOrderList.insertOrder(order);
				processOrder(client, productManufacturer, quantity, order);
				return 0;
			} else {
				waitList.insertOrder(order);
				return 1;
			}
		}
		return 2;
	}
	
	public int makePayment(String clientID, double amount) {
		Client client = clientList.searchClient(clientID);
		if(client == null) {
			return 0;
		} else if(client.getBalance() < amount) {
			return 1;
		}
		client.setBalance(client.getBalance() - amount);
		return 2;
	}
	
	public ProductManufacturer acceptShipment(String productId, String manufacturerId, int quantity) {
		ProductManufacturer productManufacturer = productManufacturerList.searchProductManufacturer(productId, manufacturerId);
		if (productManufacturer != null) {
			productManufacturer.setQuantity(productManufacturer.getQuantity() + quantity);
			return productManufacturer;
		}
		return null;
	}
	
	public OrderWithManufacturer addManufacturerOrder(String orderId, String productId, String manufacturerId, int quantity)	{
		ProductManufacturer productManufacturer = productManufacturerList.searchProductManufacturer(productId, manufacturerId);
		Product product = productList.searchProduct(productId);
		Manufacturer manufacturer = manufacturerList.searchManufacturer(manufacturerId);
		if (productManufacturer != null && product != null) {
			OrderWithManufacturer orderWithManufacturer = new OrderWithManufacturer(orderId, productManufacturer, quantity);
			product.addOrderWithManufacturer(orderWithManufacturer);
			manufacturer.addOrderWithManufacturer(orderWithManufacturer);
			return orderWithManufacturer;
		}
		return null;
	}
	
	public Iterator OrdersWithAManufacturer(String manufacturerId) {
		Manufacturer manufacturer = manufacturerList.searchManufacturer(manufacturerId);
		
		if (manufacturer != null) {
			//System.out.println(manufacturer);
			//System.out.println(manufacturer.getOrders().toString());
			return manufacturer.getOrders();
		}
		return null;
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
