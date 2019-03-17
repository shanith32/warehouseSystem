import java.util.Iterator;

public class OrderTester {
	
	public static void main(String[] args) {
		OrderList orderList = OrderList.instance();
		
		Client c1 = new Client ("Heberton Barros", "Minnesota", "7634868845");
		Client c2 = new Client ("Subi Dangol", "Nepal", "1389123893");
		
		Order myOrder1 = new Order (c1.getClientID());
		Order myOrder2 = new Order (c2.getClientID());
		orderList.insertOrder(myOrder1);
		orderList.insertOrder(myOrder2);
		
		Product myProduct = new Product ("Apple Watch", "Electronics");
		Manufacturer myManufacturer = new Manufacturer ("Apple", "New York");
		
		ProductManufacturer pm = new ProductManufacturer(myManufacturer.getId(), myProduct.getId(), 400.00);
		myOrder1.addLineItem(pm, 10);
		
		Iterator myItr = orderList.getOrders();
		while(myItr.hasNext()) {
			Order order = (Order) myItr.next();
			System.out.println(order.toString());
		}
	}
}
