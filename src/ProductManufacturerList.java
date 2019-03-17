
// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ProductManufacturerList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<ProductManufacturer> productManufacturers = new LinkedList<ProductManufacturer>();
	private static ProductManufacturerList productManufacturerList;

	private ProductManufacturerList() {
	}

	public static ProductManufacturerList instance() {
		if (productManufacturerList == null) {
			return (productManufacturerList = new ProductManufacturerList());
		} else {
			return productManufacturerList;
		}
	}

	// Assign a new price assignment
	public boolean insertProductManufacturer(ProductManufacturer pa) {
		productManufacturers.add(pa);
		return true;
	}

	public ProductManufacturer searchProductManufacturer(String pid, String mid) {
		Iterator<ProductManufacturer> productManufacturerIterator = productManufacturers.iterator();
		while (productManufacturerIterator.hasNext()) {
			ProductManufacturer productManufacturer = (ProductManufacturer) productManufacturerIterator.next();
			if (productManufacturer.getMid().equals(mid) && productManufacturer.getPid().equals(pid)) {
				return productManufacturer;
			}
		}
		return null;
	}
/*
	// Get a price assignment by id
	public ProductManufacturer getAssignmentbyId(String id) {
		Iterator<ProductManufacturer> PriceAssignmentIterator = productManufacturers.iterator();
		while (PriceAssignmentIterator.hasNext()) {
			ProductManufacturer pAssignment = (ProductManufacturer) PriceAssignmentIterator.next();
			if (pAssignment.getId().equals(id)) {
				return pAssignment;
			}
		}
		return null;
	}
*/
	// Unassign or delete a price assignment
	public boolean deleteAssignmentbyId(String mid, String pid) {
		int counter = 0;

		Iterator<ProductManufacturer> PriceAssignmentIterator = productManufacturers.iterator();
		while (PriceAssignmentIterator.hasNext()) {
			ProductManufacturer pAssignment = (ProductManufacturer) PriceAssignmentIterator.next();
			if (pAssignment.getMid().equals(mid) && pAssignment.getPid().equals(pid)) {
				productManufacturers.remove(counter);
				return true;
			}
			counter += 1;
		}
		return false;
	}

	// Should return a list of mid's for the given pid
	public List<String> getManufacturersForProduct(String pid) {
		List<String> newList = new LinkedList<>();
		for (ProductManufacturer tempPrice : productManufacturers) {
			if (tempPrice.getPid().equals(pid)) {
				newList.add("Manufacturer ID: " + tempPrice.getMid() + " Price: $" + tempPrice.getPrice());
			}
		}
		return newList;
	}
	
	public boolean isEmpty() {
		return productManufacturers.isEmpty();
	}

	// Should return a list of pid's for the given mid
	public List<String> getProductsForManufacturer(String mid) {
		List<String> newList = new LinkedList<>();
		for (ProductManufacturer tempPrice : productManufacturers) {
			if (tempPrice.getMid().equals(mid)) {
				newList.add("Product ID: " + tempPrice.getPid() + " Price: $" + tempPrice.getPrice());
			}
		}
		return newList;
	}

	public Iterator<ProductManufacturer> Assignments() {
		return productManufacturers.iterator();
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(productManufacturerList);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (productManufacturerList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (productManufacturerList == null) {
					productManufacturerList = (ProductManufacturerList) input.readObject();
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
		return productManufacturers.toString();
	}
}
