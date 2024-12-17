import java.util.*;

class Customer {
    private int id;
    private String name;
    private int loyaltyPoints;

    public Customer(int id, String name, int loyaltyPoints) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", loyaltyPoints=" + loyaltyPoints + "]";
    }
}

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}

class Order {
    private int id;
    private Customer customer;
    private Set<Product> products;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.products = new HashSet<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", customer=" + customer + ", products=" + products + "]";
    }
}

public class AmazonMenuApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Customer> customers = new ArrayList<>();
        Map<Integer, Product> productCatalog = new HashMap<>();

        // Adding initial customers and products
        customers.add(new Customer(1, "Pratik", 120));
        customers.add(new Customer(2, "Kon", 300));

        productCatalog.put(101, new Product(101, "Laptop", 1000.00));
        productCatalog.put(102, new Product(102, "Smartphone", 500.00));

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. View Customers Sorted by Loyalty Points");
            System.out.println("4. View Products Sorted by Price");
            System.out.println("5. Place Order");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: {
                    System.out.print("Enter Customer ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Loyalty Points: ");
                    int loyaltyPoints = scanner.nextInt();
                    customers.add(new Customer(id, name, loyaltyPoints));
                    System.out.println("Customer added successfully.");
                    break;
                }
                case 2: {
                    System.out.print("Enter Product ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    productCatalog.put(id, new Product(id, name, price));
                    System.out.println("Product added successfully.");
                    break;
                }
                case 3: {
                    TreeSet<Customer> sortedCustomers = new TreeSet<>(Comparator.comparingInt(Customer::getLoyaltyPoints).reversed());
                    sortedCustomers.addAll(customers);
                    System.out.println("\nCustomers sorted by Loyalty Points:");
                    sortedCustomers.forEach(System.out::println);
                    break;
                }
                case 4: {
                    TreeSet<Product> sortedProducts = new TreeSet<>(Comparator.comparingDouble(Product::getPrice));
                    sortedProducts.addAll(productCatalog.values());
                    System.out.println("\nProducts sorted by Price:");
                    sortedProducts.forEach(System.out::println);
                    break;
                }
                case 5: {
                    System.out.print("Enter Customer ID for Order: ");
                    int customerId = scanner.nextInt();
                    Customer customer = customers.stream().filter(c -> c.getId() == customerId).findFirst().orElse(null);

                    if (customer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    Order order = new Order(new Random().nextInt(1000), customer);

                    while (true) {
                        System.out.print("Enter Product ID to add to Order (or -1 to finish): ");
                        int productId = scanner.nextInt();

                        if (productId == -1) break;

                        Product product = productCatalog.get(productId);
                        if (product != null) {
                            order.addProduct(product);
                        } else {
                            System.out.println("Product not found.");
                        }
                    }

                    System.out.println("Order placed: " + order);
                    break;
                }
                case 6: {
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
