//Author: Subi Dangol

public class testerProduct {
    public static void main(String[] args) {
    	System.out.println("Two Products are created.");
        System.out.println("Product p1 = new Product (\"Smartphone\", \"Electronics\");");
        System.out.println("Product p2 = new Product (\"Pen\", \"School\");");
        Product p1 = new Product ("Smartphone", "Electronics");
        Product p2 = new Product ("Pen", "School");
        
        System.out.println("==========================================================");
        
        System.out.println("Getting product's name and category.");
        System.out.println("p1.getName();     -> " + p1.getName());
        System.out.println("p1.getCategory(); -> " + p1.getCategory());
        System.out.println("p1.getId();       -> " + p1.getId());
        System.out.println("p2.getName();     -> " + p2.getName());
        System.out.println("p2.getCategory(); -> " + p2.getCategory());
        System.out.println("p2.getId();       -> " + p2.getId());
        
        System.out.println("==========================================================");
        
        System.out.println("Creating an instance of ProductList to add the products.");
        System.out.println("ProductList productlist = ProductList.instance();");
        System.out.println("productlist.insertProduct(p1);");
        System.out.println("productlist.insertProduct(p2);");
        ProductList productlist = ProductList.instance();
        productlist.insertProduct(p1);
        productlist.insertProduct(p2);
        
        System.out.println("==========================================================");
        
        System.out.println("Search for products by Id and print the details.");
        System.out.println(productlist.searchProduct("P1"));
        System.out.println(productlist.searchProduct("P2"));
        
        System.out.println("==========================================================");
        
        

        
        
        
        
    }     
}