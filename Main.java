public class Main {
    public static void main(String args[]) {
        Product newProduct = new Product("xname", "xcat");
        String name = newProduct.toString();

        System.out.print(name);
    }
}