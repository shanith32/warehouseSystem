
// This is just a file to run test cases
import java.util.*;

public class Main {
    public static void main(String args[]) {
        // Create PriceAssignment objects
        PriceAssignment newpriceAssignment = new PriceAssignment("m1", "p1", 4.3);
        String doc = newpriceAssignment.getId();
        System.out.println("This new ID is: " + doc);

        // Create PriceAssignment2 objects
        PriceAssignment newpriceAssignment2 = new PriceAssignment("m2", "p2", 5.6);
        String doc2 = newpriceAssignment2.getId();
        System.out.println("This new ID is: " + doc2);

        // Create PriceAssignment2 objects
        PriceAssignment newpriceAssignment3 = new PriceAssignment("m3", "p3", 9);
        String doc3 = newpriceAssignment3.getId();
        System.out.println("This new ID is: " + doc3);
        
        PriceAssignment newpriceAssignment4 = new PriceAssignment("m3", "p4", 6.3);
        String doc4 = newpriceAssignment4.getId();
        System.out.println("This new ID is: " + doc4);

        // Add the PriceAssignment objects to the PriceAssignment list class
        PriceAssignmentList thePriceAssignmentList = PriceAssignmentList.instance();
        thePriceAssignmentList.insertPriceAssignment(newpriceAssignment);
        thePriceAssignmentList.insertPriceAssignment(newpriceAssignment2);
        thePriceAssignmentList.insertPriceAssignment(newpriceAssignment3);
        thePriceAssignmentList.insertPriceAssignment(newpriceAssignment4);

        // Get as a String
        System.out.println("BString: " + thePriceAssignmentList.toString());

        //testing getpriceassignmentlist method
        System.out.println(thePriceAssignmentList.getManufacturersForProduct("p3"));
        System.out.println(thePriceAssignmentList.getProductsForManufacturer("m3"));
        
        //thePriceAssignmentList.deleteAssignmentbyId("PA1");
        //thePriceAssignmentList.deleteAssignmentbyId("PA2");

        // Get as a String
        System.out.println("AString: " + thePriceAssignmentList.toString());

        // // Get as an iterator
        // Iterator<PriceAssignment> itr = thePriceAssignmentList.getPriceAssignments();

        // // checking the next element availabilty
        // while (itr.hasNext()) {
        // // moving cursor to next element
        // PriceAssignment i = itr.next();

        // // getting even elements one by one
        // System.out.print("Itr: " + i + " ");
        // }
    }
}