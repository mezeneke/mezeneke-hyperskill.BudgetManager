package budget;

import java.util.*;

import static budget.Category.*;

public class Expenses {
    private Map<Categories, List<Product>> expenses;

    Expenses() {
        initializeExpenses();
    }

    void initializeExpenses() {
        expenses = new HashMap<>();
        for (Categories value : Categories.values()) {
            expenses.put(value, new ArrayList<>());
        }
    }

    void addExpense(Categories category, String item, String price) {
        expenses.get(category).add(new Product(item, Double.parseDouble(price)));
        System.out.println("Purchase was added!");
    }

    void listExpenses() {
        List<Product> products = new ArrayList<>();
        for (List<Product> list : expenses.values()) {
            products.addAll(list);
        }
        listExpenses(products);
    }

    void listExpenses(Categories category) {
        listExpenses(expenses.get(category));
    }

    void listExpenses(Collection<Product> list) {
        double sum = 0;
        for (Product product : list) {
            sum += product.price;
            System.out.printf("%s $%f\n", product.name, product.price);
        }
        System.out.printf("Total sum: %f\n", sum);
    }


}
