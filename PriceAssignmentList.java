
// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class PriceAssignmentList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<PriceAssignment> priceAssignments = new LinkedList<PriceAssignment>();
	private static PriceAssignmentList priceAssignmentList;

	private PriceAssignmentList() {
	}

	public static PriceAssignmentList instance() {
		if (priceAssignmentList == null) {
			return (priceAssignmentList = new PriceAssignmentList());
		} else {
			return priceAssignmentList;
		}
	}

	// Assign a new price assignment
	public boolean insertPriceAssignment(PriceAssignment pa) {
		priceAssignments.add(pa);
		return true;
	}

	// Get a price assignment by id
	public PriceAssignment getAssignmentbyId(String id) {
		Iterator<PriceAssignment> PriceAssignmentIterator = priceAssignments.iterator();
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

		Iterator<PriceAssignment> PriceAssignmentIterator = priceAssignments.iterator();
		while (PriceAssignmentIterator.hasNext()) {
			PriceAssignment pAssignment = (PriceAssignment) PriceAssignmentIterator.next();
			if (pAssignment.getMid().equals(mid) && pAssignment.getPid().equals(pid)) {
				priceAssignments.remove(counter);
				return true;
			}
			counter += 1;
		}
		return false;
	}

	// Should return a list of mid's for the given pid
	public List<String> getManufacturersForProduct(String pid) {
		List<String> newList = new LinkedList<>();
		for (PriceAssignment tempPrice : priceAssignments) {
			if (tempPrice.getPid().equals(pid)) {
				newList.add("Manufacturer ID: " + tempPrice.getMid() + " Price: $" + tempPrice.getPrice());
			}
		}
		return newList;
	}
	
	public boolean isEmpty() {
		return priceAssignments.isEmpty();
	}

	// Should return a list of pid's for the given mid
	public List<String> getProductsForManufacturer(String mid) {
		List<String> newList = new LinkedList<>();
		for (PriceAssignment tempPrice : priceAssignments) {
			if (tempPrice.getMid().equals(mid)) {
				newList.add("Product ID: " + tempPrice.getPid() + " Price: $" + tempPrice.getPrice());
			}
		}
		return newList;
	}

	public Iterator<PriceAssignment> Assignments() {
		return priceAssignments.iterator();
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(priceAssignmentList);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (priceAssignmentList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (priceAssignmentList == null) {
					priceAssignmentList = (PriceAssignmentList) input.readObject();
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
		return priceAssignments.toString();
	}
}
