import java.io.Serializable;

public class OrderWithManufacturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String ORDER_MANUFACRTURER_STRING = "OM";
	private int quantity;
	private String id;
	ProductManufacturer productManufacturer;

	public OrderWithManufacturer(ProductManufacturer productManufacturer, int quantity) {
		this.productManufacturer = productManufacturer;
		this.quantity = quantity;
		this.id = ORDER_MANUFACRTURER_STRING + (OrderWithManufacturerIdServer.instance()).getId();
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

	public String getId() {
		return id;
	}
		
	@Override
	public String toString() {
		return "OrderWithManufacturer: \nOrdered Quantity: " + quantity + 
				", OrderWithManufacturer ID: " + id + "\n" + productManufacturer + "\n";
	}
}