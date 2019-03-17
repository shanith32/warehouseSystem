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
	private OrderList orderList;
	//private WaitList waitList;

	private Warehouse() {
		clientList = clientList.instance();
		productList = ProductList.instance();
		manufacturerList = ManufacturerList.instance();
		productManufacturerList = ProductManufacturerList.instance();
		orderList = OrderList.instance();
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

	public Client searchForClient(String clientID) {
		Client client = clientList.searchClient(clientID);
		return client;
	}

	public Product searchForProduct(String productID) {
		Product product = productList.searchProduct(productID);
		return product;
	}

	//added and checked
	public boolean addLineItem(Order order, String productID, String manufacturerID, int quantity) {
		ProductManufacturer productManufacturer = productManufacturerList.searchProductManufacturer(productID, manufacturerID);
		Client client = clientList.searchClient(order.getClientId());
		LineItem lineItem = order.addLineItem(productManufacturer, quantity);
//		productManufacturer.setQuantity(quantity - 1);
		if (productManufacturer != null && client != null) {
			if (productManufacturer.getQuantity() < quantity) {
				order.setCanProcess(false);
			} else {
				productManufacturer.deductQuantity(quantity);;
				client.charge(lineItem.getTotal());
				lineItem.setProcessed(true);
			}
			return true;	
		} else {
			return false;
		}
	}

	public boolean checkOrder(Order order) {
		if(order.canProcess() == true) {
			return true;
		} else {
			Client client = clientList.searchClient(order.getClientId());
			client.addWaitListOrderID(order.getId());
			Iterator lineItems = order.getLineItems();
			while(lineItems.hasNext()) {
				LineItem lineItem = (LineItem) lineItems.next();
				if(lineItem.getProcessed() == false) {
					ProductManufacturer productManufactuer = lineItem.getProductManufacturer();
					Product product = productList.searchProduct(productManufactuer.getPid());
					product.addWaitListOrderID(order.getId());
				}
			}
			return false;
		}
	}


	public Order addOrder(String clientID) {
		Client client = clientList.searchClient(clientID);
		if (client != null) {
			Order order = new Order(clientID);
			orderList.insertOrder(order);
			return order;
		}
		return null;
	}

	
	public Order searchForOrder(String orderID) {
		Order order = orderList.searchOrder(orderID);
		return order;
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
