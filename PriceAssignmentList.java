import java.util.*;
import java.lang.*;
import java.io.*;

public class PriceAssignmentList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<PriceAssignment> PriceAssignments = new LinkedList<>();
    private static PriceAssignmentList PriceAssignmentList;

    private PriceAssignmentList() {
    }

    public static PriceAssignmentList instance() {
        if (PriceAssignmentList == null) {
            return (PriceAssignmentList = new PriceAssignmentList());
        } else {
            return PriceAssignmentList;
        }
    }

    public boolean insertPriceAssignment(PriceAssignment PriceAssignment) {
        PriceAssignments.add(PriceAssignment);
        return true;
    }

    public Iterator getPriceAssignments() {
        return PriceAssignments.iterator();
    }

    // private void writeObject(java.io.ObjectOutputStream output) {
    // try {
    // output.defaultWriteObject();
    // output.writeObject(PriceAssignmentList);
    // } catch (IOException ioe) {
    // System.out.println(ioe);
    // }
    // }

    // private void readObject(java.io.ObjectInputStream input) {
    // try {
    // if (PriceAssignmentList != null) {
    // return;
    // } else {
    // input.defaultReadObject();
    // if (PriceAssignmentList == null) {
    // PriceAssignmentList = (PriceAssignmentList) input.readObject();
    // } else {
    // input.readObject();
    // }
    // }
    // } catch (IOException ioe) {
    // System.out.println("in PriceAssignmentList readObject \n" + ioe);
    // } catch (ClassNotFoundException cnfe) {
    // cnfe.printStackTrace();
    // }
    // }

    public String toString() {
        return PriceAssignments.toString();
    }
}
