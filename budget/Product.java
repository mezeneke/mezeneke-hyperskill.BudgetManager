package budget;

import java.util.Comparator;

public class Product implements Comparable<Product> {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", name, price);
    }

    @Override
    public int compareTo(Product other) {
        if (this.price < other.price) {
            return -1;
        } else if (this.price == other.price) {
            return this.getName().compareToIgnoreCase(other.getName());
        } else {
            return 1;
        }
    }
}
