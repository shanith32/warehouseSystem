// package com.warehouse;

import java.io.Serializable;

public class Client implements Serializable {

	private String name;
	private String address;
	private String phoneNumber;
	private String clientID;
	private double balance;
	private static final long serialVersionUID = 1L;
	private static final String CLIENT_STRING = "C";
	
	public Client(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phone;
		clientID = CLIENT_STRING + (ClientIDServer.instance()).getId();
	}

	public String getClientName() {
		return name;
	}

	public void setClientName(String name) {
		this.name = name;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void makePayment(double amount) {
		if(balance > 0.0) {
			balance = balance - amount;
		}
		else {
			System.out.println("There is no invoice amount to be paid.");
		}
	}
	
	public void charge(double amount) {
		balance = balance + amount;
	}

	boolean isEqual(String clientID) {
		boolean isEqual;
		if(this.clientID.equals(clientID)) {
			isEqual = true;
		}
		else {
			isEqual = false;
		}
		return isEqual;
	}

	@Override
	public String toString() {
		return "Client: " + name +  "\nClientID: " + clientID + "\nAddress: " + address + "\nPhone number: " + phoneNumber + "\nBalance: " + balance + "\n";
	}

}
