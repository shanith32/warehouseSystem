import java.util.*;

public class Main {
    public static void main(String args[]) {
        // Create supply objects
        Supply newSupply = new Supply(UUID.fromString("38600000-8cf0-11bd-b23e-10b96e4ef00d"),
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), 4.3);

        Supply newSupply2 = new Supply(UUID.fromString("38600000-8cf0-11bd-b23e-10b96e4ef00d"),
                UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), 4.3);

        String doc = newSupply.toString();
        System.out.print(doc);

        // Add the supply objects to the supply list class
        SupplyList theSupplyList = SupplyList.instance();
        theSupplyList.insertSupply(newSupply);
        theSupplyList.insertSupply(newSupply2);

        // // Get as a String
        // String info = theSupplyList.toString();
        // System.out.print("String: " + info + " ");

        // Get as an iterator
        Iterator<Supply> itr = theSupplyList.getSupplys();

        // checking the next element availabilty
        while (itr.hasNext()) {
            // moving cursor to next element
            Supply i = itr.next();

            // getting even elements one by one
            System.out.print("Itr: " + i + " ");
        }
    }
}