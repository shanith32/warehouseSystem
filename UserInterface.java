import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_MANUFACTURER = 1;
  private static final int ADD_PRODUCTS = 2;
  private static final int SHOW_MANUFACTURERS = 11;
  private static final int SHOW_PRODUCTS = 12;
  private static final int SAVE = 13;
  private static final int RETRIEVE = 14;
  private static final int HELP = 15;
  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }
  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }
  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken(HELP + " for help. Enter command:"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + "  to Exit\n");
    System.out.println(ADD_MANUFACTURER + "  to add a manufacturer");
    System.out.println(ADD_PRODUCTS + "  to  add products");
    System.out.println(SHOW_MANUFACTURERS + " to  print manufacturers");
    System.out.println(SHOW_PRODUCTS + " to  print products");
    System.out.println(SAVE + " to  save data");
    System.out.println(RETRIEVE + " to  retrieve");
    System.out.println(HELP + " for help");
  }

  public void addManufacturer() {
    String name = getToken("Enter manufacturer name: ");
    String address = getToken("Enter address: ");
    String phone = getToken("Enter phone: ");
    Manufacturer result;
    result = warehouse.addManufacturer(name, address);
    if (result == null) {
      System.out.println("Could not add manufacturer");
    }
    System.out.println(result);
  }

  public void addProducts() {
    Product result;
    do {
      String name = getToken("Enter name: ");
      String productID = getToken("Enter id: ");
      String category = getToken("Enter category: ");
      result = warehouse.addProduct(name, category);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Product could not be added");
      }
      if (!yesOrNo("Add more products?")) {
        break;
      }
    } while (true);
  }

  public void showProducts() {
      Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
	  Product product = (Product)(allProducts.next());
          System.out.println(product.toString());
      }
  }

  public void showManufacturers() {
      Iterator allManufacturers = warehouse.getManufacturers();
      while (allManufacturers.hasNext()){
	  Manufacturer manufacturer = (Manufacturer)(allManufacturers.next());
          System.out.println(manufacturer.toString());
      }
  }

  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
    } else {
      System.out.println(" There has been an error in saving \n" );
    }
  }
  
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_MANUFACTURER:  addManufacturer();
                                break;
        case ADD_PRODUCTS:      addProducts();
                                break;
        case SAVE:              save();
                                break;
        case RETRIEVE:          retrieve();
                                break;
        case SHOW_MANUFACTURERS:showManufacturers();
                                break; 		
        case SHOW_PRODUCTS:	    showProducts();
                                break; 		
        case HELP:              help();
                                break;
      }
    }
  }
  
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
