import java.util.*;
import java.lang.*;
import java.io.*;
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String id;
  private static final String PRODUCT_STRING = "P";
  private List<Manufacturer> products = new LinkedList<Manufacturer>();
  
  public Manufacturer(String name, String category) {
    this.name = name;
    this.address = category;
    id = PRODUCT_STRING + (ManufacturerIdServer.instance()).getId();
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
  
  public String toString() {
      return "Name: " + name + ", Address: " + address + ", ID: " + id;
  }
}
