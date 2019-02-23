
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
    this.id = PRICEASSIGNMENT_STRING + (PriceAssignmentIdServer.instance()).getId();
  }

  public String getId() {
    return this.id;
  }

  public boolean equals(String id) {
    return this.id.equals(id);
  }

  public String toString() {
    return " ID => " + id + " MID => " + mid + " PID => " + pid + " Price => $" + price;
  }
}
