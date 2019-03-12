import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ProcessedOrderList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();
	private static ProcessedOrderList orderlist;

	public static ProcessedOrderList instance() {
		if (orderlist == null) {
			return (orderlist = new ProcessedOrderList());
		} else {
			return orderlist;
		}
	}

	public Iterator getOrders() {
		return orders.iterator();
	}

	public boolean insertOrder(Order order) {
		return orders.add(order);
	}

	//Maybe do a remove order as well
	//getOrdersByClient
	//
	
	public Order findOrder (String potentialId) {
		Iterator <Order> myItr= orders.iterator();
		while (myItr.hasNext()) {
			Order order = (Order) myItr.next();
			if (order.getId() == potentialId) {
				return order;
			}
		}
		return null;
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (orderlist != null) {
				return;
			} 
			else{
				input.defaultReadObject();
				if (orderlist == null) {
					orderlist = (ProcessedOrderList) input.readObject();
				} 
				else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(orderlist);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Order List \n" + orders;
	}
}