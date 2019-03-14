
// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class ProductManufacturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String PRICEASSIGNMENT_STRING = "PA";
	private String mid;
	private String pid;
	private double price;
	private int quantity;

	public ProductManufacturer(String mid, String pid, double price) {
		this.mid = mid;
		this.pid = pid;
		this.price = price;
		this.setQuantity(0);
	}

	public Double getPrice() {
		return price;
	}

	public String getMid() {
		return mid;
	}

	public String getPid() {
		return pid;
	}

	public String toString() {
		return "ProductManufacturer: \nManufacturer's ID: " + mid + "\nProduct's ID: " + pid + "\nPrice: $" + price
				+ "\nQuantity: " + quantity + "\n";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void deductQuantity(int amountAdded) {
		quantity -= amountAdded;
	}

	public void addQuantity(int amountAdded) {
		quantity += amountAdded;
	}
}
