import java.util.*;

// Product class
class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

// ShoppingCart
class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }
}

// Printer
class ShoppingCartPrinter {
    public void printInvoice(ShoppingCart cart) {
        System.out.println("Shopping Cart Invoice:");
        for (Product p : cart.getProducts()) {
            System.out.println(p.getName() + " - Rs " + p.getPrice());
        }
        System.out.println("Total: Rs " + cart.calculateTotal());
    }
}

// OCP Interface
interface Persistence {
    void save(ShoppingCart cart);
}

// SQL implementation
class SqlPersistence implements Persistence {
    public void save(ShoppingCart cart) {
        System.out.println("Saving to SQL...");
    }
}

// Mongo implementation
class MongoPersistence implements Persistence {
    public void save(ShoppingCart cart) {
        System.out.println("Saving to Mongo...");
    }
}

// Main
public class SRP {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        ShoppingCartPrinter printer = new ShoppingCartPrinter();
        printer.printInvoice(cart);

        Persistence db = new SqlPersistence();
        Persistence mongo = new MongoPersistence();

        db.save(cart);
        mongo.save(cart);
    }
}