//Author: Subi Dangol

public class testerManufacturer {
    public static void main(String[] args) {
    	System.out.println("Two Manufacturers are created.");
        System.out.println("Manufacturer m1 = new Manufacturer (\"Apple\", \"California\");");
        System.out.println("Manufacturer m2 = new Manufacturer (\"PointerPark\", \"Arizona\");");
        Manufacturer m1 = new Manufacturer ("Apple", "California");
        Manufacturer m2 = new Manufacturer ("PointerPark", "Arizona");
        
        System.out.println("==========================================================");
        
        System.out.println("Getting manufacturer's name and address.");
        System.out.println("m1.getName();    -> " + m1.getName());
        System.out.println("m1.getAddress(); -> " + m1.getAddress());
        System.out.println("m1.getId();      -> " + m1.getId());
        System.out.println("m2.getName();    -> " + m2.getName());
        System.out.println("m2.getAddress(); -> " + m2.getAddress());
        System.out.println("m2.getId();      -> " + m2.getId());
        
        System.out.println("==========================================================");
        
        System.out.println("Creating an instance of ManufacturerList to add the manufacturers.");
        System.out.println("ManufacturerList manufacturerlist = ManufacturerList.instance();");
        System.out.println("manufacturerlist.insertManufacturer(m1);");
        System.out.println("manufacturerlist.insertManufacturer(m2);");
        ManufacturerList manufacturerlist = ManufacturerList.instance();
        manufacturerlist.insertManufacturer(m1);
        manufacturerlist.insertManufacturer(m2);
        
        System.out.println("==========================================================");
        
        System.out.println("Search for manufacturers by Id and print the details.");
        System.out.println(manufacturerlist.searchManufacturer("M1"));
        System.out.println(manufacturerlist.searchManufacturer("M2"));
        
        System.out.println("==========================================================");
       
    }     
}