
// package com.warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
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
	private static final int SAVE = 11;
	private static final int RETRIEVE = 12;
	private static final int HELP = 13;

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
				System.out.println("The warehouse has been successfully retrieved from the file LibraryData \n");
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
				System.out.println("Enter a number");
			}
		} while (true);
	}

	public void help() {
		System.out.println(" Enter a choice from 0 through 9 as shown below:");
		System.out.println("|================================================|");
		System.out.println("|" + EXIT + ".Exit out\t\t\t\t\t " + "|");
		System.out.println("|" + ADD_CLIENT + ".Add a client\t\t\t\t\t " + "|");
		System.out.println("|" + ADD_MANUFACTURER + ".Add a manufacturer\t\t\t\t" + " |");
		System.out.println("|" + ADD_PRODUCTS + ".Add products\t\t\t\t\t" + " |");
		System.out.println("|" + DISPLAY_CLIENTS + ".Display list of clients \t\t\t " + "|");
		System.out.println("|" + SHOW_MANUFACTURERS + ".Display list of manufacturers \t\t" + " |");
		System.out.println("|" + SHOW_PRODUCTS + ".Display list of products\t\t\t " + "|");
		System.out.println("|" + ASSIGN_PRICE + ".Assign a product to a manufacturer with a price\t\t\t " + "|");
		System.out.println("|" + UNASSIGN_PRICE + ".Unassign a product from a manufacturer\t\t\t " + "|");
		System.out
				.println("|" + GET_PRODUCT_BY_SUPPLIER + ".Display a list of products for a manufacturer\t\t\t " + "|");
		System.out
				.println("|" + GET_SUPPLIER_BY_PRODUCT + ".Display a list of manufacturers for a product\t\t\t " + "|");
		System.out.println("|" + SAVE + ".Save the data \t\t\t\t " + "|");
		System.out.println("|" + RETRIEVE + ".Retrieve \t\t\t\t\t " + "|");
		System.out.println("|" + HELP + ".Help \t\t\t\t\t\t " + "|");
		System.out.println("=================================================|");
	}

	public void addClient() {
		String name = getToken("Enter client name");
		String address = getToken("Enter address");
		String phoneNumber = getToken("Enter phone");
		if (warehouse.addClient(name, address, phoneNumber)) {
			System.out.println("Client has been successfully added.");
		} else {
			System.out.println("Client could not be added to the system.");
		}
	}

	public void addManufacturer() {
		String name = getToken("Enter manufacturer name: ");
		String address = getToken("Enter address: ");
		String phone = getToken("Enter phone: ");
		Manufacturer result;
		result = warehouse.addManufacturer(name, address);
		if (result == null) {
			System.out.println("Could not add manufacturer");
		}
		System.out.println(result);
	}

	public void addProducts() {
		Product result;
		do {
			String name = getToken("Enter name: ");
			String productID = getToken("Enter id: ");
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
		String price = getToken("Enter the price");
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
		System.out.println(warehouse.getProductbySupplier(mid));

	}

	public void getSupplierByProduct() {
		String pid = getToken("Enter the product's ID");
		System.out.println(warehouse.getSupplierbyProduct(pid));

	}

	public void save() {
		if (warehouse.save()) {
			System.out.println("The warehouse has been successfully saved in the file LibraryData \n");
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
