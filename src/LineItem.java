import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LineItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String ORDER_STRING = "o";
	private int quantity;
	private boolean processed;
	ProductManufacturer productManufacturer;

	public LineItem(ProductManufacturer productManufacturer, int quantity) {
		this.productManufacturer = productManufacturer;
		this.quantity = quantity;
		this.processed = false;
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

	public void setProductManufacturer(ProductManufacturer productManufacturer) {
		this.productManufacturer = productManufacturer;
	}
	
	public double getTotal() {
		return quantity * productManufacturer.getPrice();
	}
		
	public boolean getProcessed() {
		return processed;
	}

	public void setProcessed(boolean isAvailable) {
		this.processed = isAvailable;
	}

	@Override
	public String toString() {
		return "Order: \nOrdered Quantity: " + quantity 
				+ "\n" + productManufacturer + "\n";
	}
}