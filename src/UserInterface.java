// package com.warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class UserInterface {
	private static UserInterface userInterface;
	static Warehouse warehouse;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int ADD_MANUFACTURER = 2;
	private static final int ADD_PRODUCTS = 3;
	private static final int DISPLAY_CLIENTS = 4;
	private static final int SHOW_MANUFACTURERS = 5;
	private static final int SHOW_PRODUCTS = 6;
	private static final int ASSIGN_PRICE = 7;
	private static final int UNASSIGN_PRICE = 8;
	private static final int GET_PRODUCT_BY_SUPPLIER = 9;
	private static final int GET_SUPPLIER_BY_PRODUCT = 10;
	private static final int PLACE_ORDER = 11;
	private static final int MAKE_PAYMENT = 12;
	private static final int GET_OUTSTANDING_CLIENTS = 13;
	private static final int ACCEPT_SHIPMENT = 14;
	private static final int GET_CLIENT_WAITLIST = 15;
	private static final int GET_PRODUCT_WAITLIST = 16;
	private static final int PLACE_ORDER_WITH_MANUFACTURER = 17;
	private static final int DISPLAY_ORDERS_WITH_A_MANUFACTURER = 18;
	private static final int SAVE = 19;
	private static final int RETRIEVE = 20;
	private static final int HELP = 21;

	
	private UserInterface() {
		if (yesOrNo("Look for saved data and use it?")) {
			retrieve();
		} else {
			warehouse = Warehouse.instance();
		}
	}

	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	private void retrieve() {
		try {
			Warehouse tempWarehouse = Warehouse.retrieve();
			if (tempWarehouse != null) {
				System.out.println("The warehouse has been successfully retrieved from the file WarehouseData \n");
				warehouse = tempWarehouse;
			} else {
				System.out.println("File doesnt exist; creating new warehouse");
				warehouse = Warehouse.instance();
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command: " + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number: ");
			}
		} while (true);
	}

	public void help() {
		System.out.println(" Enter a choice from 0 through 9 as shown below:");
		System.out.println("|=======================================================|");
		System.out.println("| " + EXIT                    + ".Exit out                                            |");
		System.out.println("| " + ADD_CLIENT              + ".Add a client                                        |");
		System.out.println("| " + ADD_MANUFACTURER        + ".Add a manufacturer                                  |");
		System.out.println("| " + ADD_PRODUCTS            + ".Add products                                        |");
		System.out.println("| " + DISPLAY_CLIENTS         + ".Display list of clients                             |");
		System.out.println("| " + SHOW_MANUFACTURERS      + ".Display list of manufacturers                       |");
		System.out.println("| " + SHOW_PRODUCTS           + ".Display list of products                            |");
		System.out.println("| " + ASSIGN_PRICE            + ".Assign a product to a manufacturer with a price     |");
		System.out.println("| " + UNASSIGN_PRICE          + ".Unassign a product from a manufacturer              |");
		System.out.println("| " + GET_PRODUCT_BY_SUPPLIER + ".Display a list of products for a manufacturer       |");
		System.out.println("| " + GET_SUPPLIER_BY_PRODUCT + ".Display a list of manufacturers for a product      |");
		System.out.println("| " + PLACE_ORDER             + ".Place an order                                     |");
		System.out.println("| " + MAKE_PAYMENT            + ".Make payment on behalf of a client                 |");
		System.out.println("| " + GET_OUTSTANDING_CLIENTS + ".Display a list of clients with outstanding balance |");
		System.out.println("| " + ACCEPT_SHIPMENT         + ".Accept shipment                                    |");
		System.out.println("| " + GET_CLIENT_WAITLIST     + ".Display a list of waitlisted orders for a client   |");
		System.out.println("| " + GET_PRODUCT_WAITLIST    + ".Display a list of waitlisted orders for a product  |");
		System.out.println("| " + PLACE_ORDER_WITH_MANUFACTURER + ".Order with a manufacturer                          |");
		System.out.println("| " + DISPLAY_ORDERS_WITH_A_MANUFACTURER    + ".Display a list of orders with a manufacturer       |");
		System.out.println("| " + SAVE                    + ".Save the data                                      |");
		System.out.println("| " + RETRIEVE                + ".Retrieve                                           |");
		System.out.println("| " + HELP                    + ".Help                                               |");
		System.out.println("|=======================================================|");
	}

	public void addClient() {
		String name = getToken("Enter client name: ");
		String address = getToken("Enter address: ");
		String phoneNumber = getToken("Enter phone: ");
		Client result = warehouse.addClient(name, address, phoneNumber);
		if (result == null) {
			System.out.println("Client could not be added to the system.");
		} else {
			System.out.println(result);
		}
		
	}

	public void addManufacturer() {
		String name = getToken("Enter manufacturer name: ");
		String address = getToken("Enter address: ");
		Manufacturer result;
		result = warehouse.addManufacturer(name, address);
		if (result == null) {
			System.out.println("Could not add manufacturer");
		} else {
		System.out.println(result);
		}
	}

	public void addProducts() {
		Product result;
		do {
			String name = getToken("Enter product name: ");
			String category = getToken("Enter category: ");
			result = warehouse.addProduct(name, category);
			if (result != null) {
				System.out.println(result);
			} else {
				System.out.println("Product could not be added");
			}
			if (!yesOrNo("Add more products?")) {
				break;
			}
		} while (true);
	}

	public void displayClients() {
		Iterator listOfClients = warehouse.getClients();
		while (listOfClients.hasNext()) {
			Client client = (Client) (listOfClients.next());
			System.out.println(client);
		}
	}

	public void showProducts() {
		Iterator allProducts = warehouse.getProducts();
		while (allProducts.hasNext()) {
			Product product = (Product) (allProducts.next());
			System.out.println(product.toString());
		}
	}

	public void showManufacturers() {
		Iterator allManufacturers = warehouse.getManufacturers();
		while (allManufacturers.hasNext()) {
			Manufacturer manufacturer = (Manufacturer) (allManufacturers.next());
			System.out.println(manufacturer.toString());
		}
	}

	public void assignPrice() {
		String mid = getToken("Enter the manufacturer's ID");
		String pid = getToken("Enter the product's ID");
		String p = getToken("Enter the price");
		double price = Double.valueOf(p);
		if (warehouse.assignProductToManufacturer(mid, pid, price)) {
			System.out.println("Assignment successful");
		} else {
			System.out.println("Assignment cannot be done");
		}
	}

	public void unassignPrice() {
		String mid = getToken("Enter the manufacturer's ID");
		String pid = getToken("Enter the product's ID");
		if (warehouse.unassignProductfromManufacturer(mid, pid)) {
			System.out.println("UnAssignment successful");
		} else {
			System.out.println("Assignment cannot be done");
		}
	}

	public void getProductBySupplier() {
		String mid = getToken("Enter the manufacturer's ID");
		List<String> myList = warehouse.getProductbySupplier(mid);
		if(myList.isEmpty()) {
			System.out.println("There is no products assigned to manufacturer with ID " + mid);
		}
		else {
			System.out.println(warehouse.getProductbySupplier(mid));
			System.out.println();
		}	
	}

	public void getSupplierByProduct() {
		String pid = getToken("Enter the product's ID");
		List<String> myList = warehouse.getSupplierbyProduct(pid);
		if(myList.isEmpty()) {
			System.out.println("There is no manufacturers assigned to product with ID " + pid);
		}
		else {
			System.out.println(warehouse.getSupplierbyProduct(pid));
			System.out.println();
		}
	}

	private void addOrder() {
		Order newOrder;
		boolean isAdded;
		boolean isRunning = true;
		String clientID = getToken("Enter client's ID: ");
		newOrder = warehouse.addOrder(clientID);
		while(newOrder != null && isRunning) {
			String manufacturerID = getToken("Enter Manufacturer's ID: ");
			String productID = getToken("Enter Product's ID: ");
			String q = getToken("Enter quantity: ");
			int quantity = Integer.valueOf(q);
			isAdded = warehouse.addLineItem(newOrder, productID, manufacturerID, quantity);
			if (isAdded == false) {
				System.out.println("Invalid ID");
			} else {
				System.out.println("Added the item to the order");
			}
			String response = getToken("Would you like to add another item? y/n");
			if(response.equals("n")) {
				System.out.println("Adding items completed");
				isRunning = false;
			}
		}
		if (warehouse.checkOrder(newOrder)) {
			System.out.println("All items in the order has been processed.");
		} else {
			System.out.println("Some items were added to the waitlist");
		}
	}
	
	public void displayOutstandingClients() {
		Iterator listOfClients = warehouse.getClients();
		while (listOfClients.hasNext()) {
			Client client = (Client) (listOfClients.next());
			if(client.isOutstanding())
				System.out.println(client);
		}
	}
	
	public void makePayment() {
		int result;
		String clientID = getToken("Enter client's ID: ");
		String a = getToken("Enter amount: ");
		int amount = Integer.valueOf(a);
		result = warehouse.makePayment(clientID, amount);
		if (result == 0) {
			System.out.println("Client not found.");
		} else if (result == 1) {
			System.out.println("Amount greater than the balance.");
		} else {
			System.out.println("The payment was received.");
		}
	}
	
	public void acceptShipment() {
		ProductManufacturer result;
		String manufacturerID = getToken("Enter manufacturer's ID: ");
		String productID = getToken("Enter product's ID: ");
		String q = getToken("Enter quantity: ");
		int quantity = Integer.valueOf(q);
		result = warehouse.acceptShipment(productID, manufacturerID, quantity);
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("Wrong product's ID or manufacturer's ID.");
		}
	}
	
	public void displayClientWaitList() {
		String clientID = getToken("Enter client's ID: ");
		Client client = warehouse.searchForClient(clientID);
		if (client == null)
    		System.out.println("Client not found");
		else {
			Iterator itr = client.getWaitListOrderIDs();
			while(itr.hasNext()) {
				String orderID = (String) (itr.next());
				Order order = warehouse.searchForOrder(orderID);
                System.out.println(order.toString());
			}
		}
	}
	
	public void displayProductWaitList() {
		String productID = getToken("Enter product's ID: ");
		Product product = warehouse.searchForProduct(productID);
		if (product == null)
    		System.out.println("Product not found");
		else {
			Iterator itr = product.getWaitListOrderIDs();
			while(itr.hasNext()) {
				String orderID = (String) (itr.next());
				Order order = warehouse.searchForOrder(orderID);
                System.out.println(order.toString());
			}
		}
	}
	
	private void addOrderWithManufacturer() {
		OrderWithManufacturer result;
		String manufacturerID = getToken("Enter Manufacturer's ID: ");
		String productID = getToken("Enter Product's ID: ");
		String orderID = getToken("Enter order's ID: ");
		String q = getToken("Enter quantity: ");
		int quantity = Integer.valueOf(q);
		result = warehouse.addManufacturerOrder(orderID, productID, manufacturerID, quantity);
		if (result != null) {
			System.out.println(result);
		} else {
			System.out.println("Invalid ID.");
		}
	}
	
	public void displayOrdersWithAManufacturer() {
		String manufacturerID = getToken("Enter manufacturer's ID: ");
		Iterator listOfOrders = warehouse.OrdersWithAManufacturer(manufacturerID);
		while (listOfOrders.hasNext()) {
			OrderWithManufacturer orderWithManufacturer = (OrderWithManufacturer) (listOfOrders.next());
		    System.out.println(orderWithManufacturer); 
		}
	}
	
	public void save() {
		if (warehouse.save()) {
			System.out.println("The warehouse has been successfully saved in the file WarehouseData \n");
		} else {
			System.out.println("An error has occurred while trying to save \n");
		}
	}

	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_CLIENT:
				addClient();
				break;
			case DISPLAY_CLIENTS:
				displayClients();
				break;
			case ADD_MANUFACTURER:
				addManufacturer();
				break;
			case ADD_PRODUCTS:
				addProducts();
				break;
			case SHOW_MANUFACTURERS:
				showManufacturers();
				break;
			case SHOW_PRODUCTS:
				showProducts();
				break;
			case ASSIGN_PRICE:
				assignPrice();
				break;
			case UNASSIGN_PRICE:
				unassignPrice();
				break;
			case GET_PRODUCT_BY_SUPPLIER:
				getProductBySupplier();
				break;
			case GET_SUPPLIER_BY_PRODUCT:
				getSupplierByProduct();
				break;
			case PLACE_ORDER:
				addOrder();
				break;
			case MAKE_PAYMENT:
				makePayment();
				break;
			case GET_OUTSTANDING_CLIENTS:
				displayOutstandingClients();
				break;
			case ACCEPT_SHIPMENT:
				acceptShipment();
				break;
			case GET_CLIENT_WAITLIST:
				displayClientWaitList();
				break;
			case GET_PRODUCT_WAITLIST:
				displayProductWaitList();
				break;
			case PLACE_ORDER_WITH_MANUFACTURER:
				addOrderWithManufacturer();
				break;
			case DISPLAY_ORDERS_WITH_A_MANUFACTURER:
				displayOrdersWithAManufacturer();
				break;
			case SAVE:
				save();
				break;
			case RETRIEVE:
				retrieve();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	public static void main(String[] args) {
		UserInterface.instance().process();
	}

}
