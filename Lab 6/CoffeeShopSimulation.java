import java.util.LinkedList;
import java.util.Queue;

class CoffeeCounter {
    private final int MAX_CAPACITY = 3;
    private final Queue<String> counter = new LinkedList<>();

    public synchronized void addCoffee(String baristaName) throws InterruptedException {
        while (counter.size() == MAX_CAPACITY) {
            System.out.println(baristaName + " is waiting. Counter is full.");
            wait();
        }
        counter.add("Coffee");
        System.out.println(baristaName + " prepared a coffee. Counter: " + counter.size());
        notifyAll();
    }

    public synchronized void pickCoffee(String customerName) throws InterruptedException {
        while (counter.isEmpty()) {
            System.out.println(customerName + " is waiting. Counter is empty.");
            wait();
        }
        counter.poll();
        System.out.println(customerName + " picked up a coffee. Counter: " + counter.size());
        notifyAll();
    }

    public synchronized void reviewCoffee() throws InterruptedException {
        while (counter.isEmpty()) {
            System.out.println("Reviewer is waiting. Counter is empty.");
            wait();
        }
        counter.poll();
        System.out.println("Reviewer sampled a coffee. Counter: " + counter.size());
        notifyAll();
    }
}

class Barista extends Thread {
    private final CoffeeCounter counter;
    private final int coffeeToPrepare;

    public Barista(CoffeeCounter counter, int coffeeToPrepare, String name) {
        super(name);
        this.counter = counter;
        this.coffeeToPrepare = coffeeToPrepare;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeeToPrepare; i++) {
                counter.addCoffee(getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer extends Thread {
    private final CoffeeCounter counter;
    private final int coffeeToPick;

    public Customer(CoffeeCounter counter, int coffeeToPick, String name) {
        super(name);
        this.counter = counter;
        this.coffeeToPick = coffeeToPick;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < coffeeToPick; i++) {
                counter.pickCoffee(getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class CoffeeReviewer extends Thread {
    private final CoffeeCounter counter;

    public CoffeeReviewer(CoffeeCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            counter.reviewCoffee();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class CoffeeShopSimulation {
    public static void main(String[] args) {
        CoffeeCounter counter = new CoffeeCounter();

        // Baristas
        Barista barista1 = new Barista(counter, 2, "Barista 1");
        Barista barista2 = new Barista(counter, 3, "Barista 2");

        // Customers
        Customer customer1 = new Customer(counter, 1, "Customer 1");
        Customer customer2 = new Customer(counter, 2, "Customer 2");
        Customer customer3 = new Customer(counter, 1, "Customer 3");

        // Coffee Reviewer
        CoffeeReviewer reviewer = new CoffeeReviewer(counter);

        // Start all threads
        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();
        customer3.start();
        reviewer.start();

        // Wait for all threads to finish
        try {
            barista1.join();
            barista2.join();
            customer1.join();
            customer2.join();
            customer3.join();
            reviewer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}