// package com.warehouse;

import java.util.LinkedList;
import java.util.List;

public class Product {
	private static final long serialVersionUID = 1L;
	private String name;
	private String category;
	private String id;
	private static final String PRODUCT_STRING = "P";

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

	public String toString() {
		return "Name: " + name + ", Category: " + category + ", ID: " + id;
	}
}
