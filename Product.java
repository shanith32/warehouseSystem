
import java.util.*;
import java.lang.*;
import java.io.*;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String productName;
  private String category;
  private UUID id;

  public Product(String productName, String category) {
    this.productName = productName;
    this.category = category;
    this.id = UUID.randomUUID();
  }

  public String getcategory() {
    return category;
  }

  public String getproductName() {
    return productName;
  }

  public UUID getId() {
    return id;
  }

  public String toString() {
    return "productName " + productName + " category " + category + " id " + id;
  }
}
