// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String category;
	private String id;
	private static final String PRODUCT_STRING = "P";
	private List<OrderWithManufacturer> orders = new LinkedList<OrderWithManufacturer>();

	public Product(String name, String category) {
		this.name = name;
		this.category = category;
		id = PRODUCT_STRING + (ProductIdServer.instance()).getId();
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
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

	public String toString() {
		return "Product Info: \nName: " + name + "\nCategory: " + category + "\nID: " + id + "\n";
	}
}
