
// This is just a file to run test cases
import java.util.*;

public class PriceAssignmentTest {
        public static void main(String args[]) {
                // Create PriceAssignment object 1
                PriceAssignment newpriceAssignment = new PriceAssignment("m1", "p1", "4.3");
                System.out.println("newpriceAssignment 1 " + "mid: " + newpriceAssignment.getMid() + " pid :"
                                + newpriceAssignment.getPid() + " Price: " + newpriceAssignment.getPrice());

                // Create PriceAssignment object 2
                PriceAssignment newpriceAssignment2 = new PriceAssignment("m2", "p2", "5.6");
                System.out.println("newpriceAssignment 2 " + "mid: " + newpriceAssignment2.getMid() + " pid :"
                                + newpriceAssignment2.getPid() + " Price: " + newpriceAssignment2.getPrice());

                // Create PriceAssignment object 3
                PriceAssignment newpriceAssignment3 = new PriceAssignment("m3", "p3", "9");
                System.out.println("newpriceAssignment 3 " + "mid: " + newpriceAssignment3.getMid() + " pid :"
                                + newpriceAssignment3.getPid() + " Price: " + newpriceAssignment3.getPrice());

                // Create PriceAssignment object 4
                PriceAssignment newpriceAssignment4 = new PriceAssignment("m4", "p4", "6.3");
                System.out.println("newpriceAssignment 4 " + "mid: " + newpriceAssignment4.getMid() + " pid :"
                                + newpriceAssignment4.getPid() + " Price: " + newpriceAssignment4.getPrice());

                // Add the PriceAssignment objects to the PriceAssignment list class
                PriceAssignmentList thePriceAssignmentList = PriceAssignmentList.instance();
                thePriceAssignmentList.insertPriceAssignment(newpriceAssignment);
                thePriceAssignmentList.insertPriceAssignment(newpriceAssignment2);
                thePriceAssignmentList.insertPriceAssignment(newpriceAssignment3);
                thePriceAssignmentList.insertPriceAssignment(newpriceAssignment4);

                // Display the PriceAssignmentList as a String
                System.out.println("Before Delete: " + thePriceAssignmentList.toString());

                // testing getpriceassignmentlist method
                System.out.println(thePriceAssignmentList.getManufacturersForProduct("p3"));
                System.out.println(thePriceAssignmentList.getProductsForManufacturer("m2"));

                thePriceAssignmentList.deleteAssignmentbyId("m3", "p3");
                thePriceAssignmentList.deleteAssignmentbyId("m1", "p1");

                // Display the PriceAssignmentList as a String
                System.out.println("After Delete: " + thePriceAssignmentList.toString());
        }

}
