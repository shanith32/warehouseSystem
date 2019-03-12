import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String ORDER_STRING = "o";
	private int quantity;
	private String id;
	private Calendar date;
	private String clientReferenceId;
	ProductManufacturer productManufacturer;

	public Order(String clientId, ProductManufacturer productManufacturer, int quantity) {
		this.productManufacturer = productManufacturer;
		this.quantity = quantity;
		this.id = ORDER_STRING + (OrderIdServer.instance()).getId();
		this.clientReferenceId = clientId;
		date = new GregorianCalendar();
		date.setTimeInMillis(System.currentTimeMillis());
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductManufacturer getProductManufacturer() {
		return productManufacturer;
	}

	public void setProduct(ProductManufacturer productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

	public String getClientReferenceId() {
		return clientReferenceId;
	}

	public String getId() {
		return id;
	}
	
	public double getTotal() {
		return quantity * productManufacturer.getPrice();
	}
		
	@Override
	public String toString() {
		return "Order: \nClient ID: " + clientReferenceId + ", Ordered Quantity: " + quantity + 
				", Order ID: " + id + ", Date: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date.getTimeInMillis()) 
				+ "\n" + productManufacturer + "\n";
	}
}