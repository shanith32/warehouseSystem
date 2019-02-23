
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

    public boolean insertPriceAssignment(PriceAssignment PriceAssignment) {
        PriceAssignments.add(PriceAssignment);
        return true;
    }

    public PriceAssignment getAssignmentbyId(String id) {
        // Should return the PriceAssignment object from the list for the given id
    }

    public boolean deleteAssignmentbyId(String id) {
        // Should delete the PriceAssignment object from the list for the given id
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

    // public PriceAssignment searchPriceAssignment(String
    // potentialPriceAssignmentID) {
    // Iterator<PriceAssignment> priceAssignmentsIterator =
    // PriceAssignments.iterator();
    // while (priceAssignmentsIterator.hasNext()) {
    // PriceAssignment pProduct = (Product) priceAssignmentsIterator.next();
    // if (pProduct.getId().equals(potentialPriceAssignmentID)) {
    // return pProduct;
    // }
    // }
    // return null;
    // }

    public String toString() {
        return PriceAssignments.toString();
    }
}
