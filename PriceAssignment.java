
// package com.warehouse;

import java.util.*;
import java.lang.*;
import java.io.*;

public class PriceAssignment implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String PRICEASSIGNMENT_STRING = "PA";
  private String mid;
  private String pid;
  private double price;
  private String id;

  public PriceAssignment(String mid, String pid, double price) {
    this.mid = mid;
    this.pid = pid;
    this.price = price;
    id = PRICEASSIGNMENT_STRING + (PriceAssignmentIdServer.instance()).getId();
  }

  public String getId() {
    return id;
  }

  public boolean equals(String id) {
    return this.id.equals(id);
  }

  @Override
  public String toString() {
    return "Manufacturer with id " + mid + " supplies the product with id " + pid + " for $" + price;
  }
}
