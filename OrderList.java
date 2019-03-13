import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OrderList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();
	private static OrderList orderList;

    public static OrderList instance() {
        if (orderList == null) {
            return (orderList = new OrderList());
        } else {
            return orderList;
        }
    }

	public boolean insertOrder(Order order) {
        orders.add(order);
        return true;
    }

    public Iterator getOrders(){
        return orders.iterator();
    }

    public Order searchOrder(String orderID) {
		Iterator<Order> ordersIterator = orders.iterator();
		while (ordersIterator.hasNext()) {
			Order order = (Order) ordersIterator.next();
			if (order.getId().equals(orderID)) {
				return order;
			}
		}
		return null;
	}
    
    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(orderList);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (orderList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (orderList == null) {
                    orderList = (OrderList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }
    
    public String toString() {
        return orders.toString();
    }
}
