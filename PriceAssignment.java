
import java.util.*;
import java.lang.*;
import java.io.*;

public class PriceAssignment implements Serializable {
  private static final long serialVersionUID = 1L;
  private UUID mid;
  private UUID pid;
  private Double price;
  private UUID id;

  public PriceAssignment(UUID mid, UUID pid, Double price) {
    this.mid = mid;
    this.pid = pid;
    this.price = price;
    this.id = UUID.randomUUID();
  }

  // public String getcategory() {
  // return category;
  // }

  // public String getPriceAssignmentName() {
  // return PriceAssignmentName;
  // }

  public UUID getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Manufacturer with id " + mid + " supplies the product with id " + pid + " for $" + price;
  }
}
