import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String ORDER_STRING = "O";
	private List<LineItem> lineItems = new LinkedList();
	private double totalPrice;
	private String id;
	private Calendar date;
	private String clientReferenceId;
	private boolean canProcess;

	public Order(String clientId) {
		this.id = ORDER_STRING + (OrderIdServer.instance()).getId();
		this.clientReferenceId = clientId;
		date = new GregorianCalendar();
		date.setTimeInMillis(System.currentTimeMillis());
		this.totalPrice = 0;
		this.setCanProcess(true);
	}

	public String getId() {
		return id;
	}
	
	public double getTotalPrice() {
        return this.totalPrice;
    }
	
	public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
	
	public String getClientId() {
        return this.clientReferenceId;
    }
	
	public void setClientId(double totalPrice) {
        this.totalPrice = totalPrice;
    }
	
    public Iterator<LineItem> getLineItems() { 
    	return lineItems.iterator(); 
    }
		
    public void setLineItems(List<LineItem> lineItems) {
    	this.lineItems = lineItems;
    }
    
    public void addLineItem (ProductManufacturer productManufacturer, int quantity) {
        LineItem newItem = new LineItem(productManufacturer, quantity);
        lineItems.add(newItem);
    }
    
    public void addDollarAmt(ProductManufacturer productManufacturer) {
    	for(LineItem o: lineItems)
    		totalPrice += o.getTotal() ;
    }
    
	@Override
	public String toString() {
		return "Order: \nClient ID: " + clientReferenceId +", Order ID: " + id + ", Date: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date.getTimeInMillis()) 
				+ "\n";
	}

	public boolean canProcess() {
		return canProcess;
	}

	public void setCanProcess(boolean canProcess) {
		this.canProcess = canProcess;
	}
}