
// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ManufacturerList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Manufacturer> manufacturers = new LinkedList<Manufacturer>();
	private static ManufacturerList ManufacturerList;

	private ManufacturerList() {

	}

	public static ManufacturerList instance() {
		if (ManufacturerList == null) {
			return (ManufacturerList = new ManufacturerList());
		} else {
			return ManufacturerList;
		}
	}

	public boolean insertManufacturer(Manufacturer product) {
		manufacturers.add(product);
		return true;
	}

	public Iterator<Manufacturer> getManufacturers() {
		return manufacturers.iterator();
	}

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(ManufacturerList);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (ManufacturerList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (ManufacturerList == null) {
					ManufacturerList = (ManufacturerList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			System.out.println("in ManufacturerList readObject \n" + ioe);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public Manufacturer searchManufacturer(String potentialManufacturerID) {
		Iterator<Manufacturer> productsIterator = manufacturers.iterator();
		while (productsIterator.hasNext()) {
			Manufacturer pManufacturer = (Manufacturer) productsIterator.next();
			if (pManufacturer.getId().equals(potentialManufacturerID)) {
				System.out.println("Manufacturer matched");
				return pManufacturer;
			}
		}
		return null;
	}

	public String toString() {
		return manufacturers.toString();
	}

}
