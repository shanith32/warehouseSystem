
// package com.warehouse;

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

    // Assign a new price assignment
    public boolean insertPriceAssignment(PriceAssignment PriceAssignment) {
        PriceAssignments.add(PriceAssignment);
        return true;
    }

    // Get a price assignment by id
    public PriceAssignment getAssignmentbyId(String id) {
        Iterator<PriceAssignment> PriceAssignmentIterator = PriceAssignments.iterator();
        while (PriceAssignmentIterator.hasNext()) {
            PriceAssignment pAssignment = (PriceAssignment) PriceAssignmentIterator.next();
            if (pAssignment.getId().equals(id)) {
                return pAssignment;
            }
        }
        return null;
    }

    // Unassign or delete a price assginment
    public boolean deleteAssignmentbyId(String id) {
        int counter = 0;

        Iterator<PriceAssignment> PriceAssignmentIterator = PriceAssignments.iterator();
        while (PriceAssignmentIterator.hasNext()) {
            PriceAssignment pAssignment = (PriceAssignment) PriceAssignmentIterator.next();
            if (pAssignment.getId().equals(id)) {
                PriceAssignments.remove(counter);
                return true;
            }
            counter += 1;
        }
        return false;
    }

    public String getManufacturersForProduct(String pid) {
        // Should return a list of mid's for the given pid
    }

    public String getProductsForManufacturer(String pid) {
        // Should return a list of pid's for the given mid
    }

    public Iterator<PriceAssignment> Assignments() {
        return PriceAssignments.iterator();
    }

    private void writeObject(java.io.ObjectOutputStream output) {
        try {
            output.defaultWriteObject();
            output.writeObject(PriceAssignmentList);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private void readObject(java.io.ObjectInputStream input) {
        try {
            if (PriceAssignmentList != null) {
                return;
            } else {
                input.defaultReadObject();
                if (PriceAssignmentList == null) {
                    PriceAssignmentList = (PriceAssignmentList) input.readObject();
                } else {
                    input.readObject();
                }
            }
        } catch (IOException ioe) {
            System.out.println("in PriceAssignmentList readObject \n" + ioe);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public String toString() {
        return PriceAssignments.toString();
    }
}
