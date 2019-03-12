import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WaitList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();
	private static WaitList waitlist;

	public static WaitList instance() {
		if (waitlist == null) {
			return (waitlist = new WaitList());
		} else {
			return waitlist;
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
			if (waitlist != null) {
				return;
			} 
			else{
				input.defaultReadObject();
				if (waitlist == null) {
					waitlist = (WaitList) input.readObject();
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
			output.writeObject(waitlist);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Order List \n" + orders;
	}
}