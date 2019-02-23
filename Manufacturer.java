
// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String id;
	private double cost;
	private int quantity;
	private static final String MANUFACTURER_STRING = "M";
	private List<Manufacturer> products = new LinkedList<Manufacturer>();

	public Manufacturer(String name, String address) {
		this.name = name;
		this.address = address;
		id = MANUFACTURER_STRING + (ManufacturerIdServer.instance()).getId();
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getId() {
		return id;
	}

	public double getCost() {
		return cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean equals(String id) {
		return this.id.equals(id);
	}

	public String toString() {
		return "Name: " + name + ", Address: " + address + ", ID: " + id;
	}
}
