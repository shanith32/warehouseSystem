
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
    public boolean deleteAssignmentbyId(String mid, String pid) {
        int counter = 0;

        Iterator<PriceAssignment> PriceAssignmentIterator = PriceAssignments.iterator();
        while (PriceAssignmentIterator.hasNext()) {
            PriceAssignment pAssignment = (PriceAssignment) PriceAssignmentIterator.next();
            if (pAssignment.getMid().equals(mid) && pAssignment.getPid().equals(pid)) {
                PriceAssignments.remove(counter);
                return true;
            }
            counter += 1;
        }
        return false;
    }

    // Should return a list of mid's for the given pid
    public List<String> getManufacturersForProduct(String pid) {
        List<String> newList = new LinkedList<>();
        for (PriceAssignment tempPrice : PriceAssignments) {
            if (tempPrice.getPid() == pid) {
                newList.add("Manufacturer ID: " + tempPrice.getMid() + " Price: $" + tempPrice.getPrice());
            }
        }
        return newList;
    }

    // Should return a list of pid's for the given mid
    public List<String> getProductsForManufacturer(String mid) {
        List<String> newList = new LinkedList<>();
        for (PriceAssignment tempPrice : PriceAssignments) {
            if (tempPrice.getMid() == mid) {
                newList.add("Product ID: " + tempPrice.getPid() + " Price: $" + tempPrice.getPrice());
            }
        }
        return newList;
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
