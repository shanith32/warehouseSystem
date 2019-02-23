
// This is just a file to run test cases
import java.util.*;

public class Main {
    public static void main(String args[]) {
        // Create PriceAssignment objects
        PriceAssignment newpriceAssignment = new PriceAssignment("m1", "p1", 4.3);
        String doc = newpriceAssignment.getId();
        System.out.println("This new ID is: " + doc);

        // Create PriceAssignment2 objects
        PriceAssignment newpriceAssignment2 = new PriceAssignment("m2", "p2", 4.3);
        String doc2 = newpriceAssignment2.getId();
        System.out.println("This new ID is: " + doc2);

        // Add the PriceAssignment objects to the PriceAssignment list class
        PriceAssignmentList thePriceAssignmentList = PriceAssignmentList.instance();
        thePriceAssignmentList.insertPriceAssignment(newpriceAssignment);
        thePriceAssignmentList.insertPriceAssignment(newpriceAssignment2);

        // Get as a String
        String info = thePriceAssignmentList.toString();
        System.out.println("String: " + info + " ");

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