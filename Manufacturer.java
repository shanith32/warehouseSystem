// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String id;
	private static final String MANUFACTURER_STRING = "M";
	private List<OrderWithManufacturer> orders = new LinkedList<OrderWithManufacturer>();

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

	public boolean equals(String id) {
		return this.id.equals(id);
	}

	public void addOrderWithManufacturer(OrderWithManufacturer orderWithManufacturer) {
		orders.add(orderWithManufacturer);
	}
	
	public Iterator getOrders() {
		return orders.iterator();
	}
	
	@Override
	public String toString() {
		return "Manufacturer Info: \nName: " + name + "\nAddress: " + address + "\nID: " + id + "\n";
	}
}
