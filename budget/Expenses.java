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

    boolean addExpense(Categories category, String itemName, String price) {
        expenses.get(category).add(new Product(itemName, Double.parseDouble(price)));
        return true;
    }

    List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (List<Product> list : expenses.values()) {
            products.addAll(list);
        }
        return products;
    }

    List<Product> getProducts(Categories category) {
        return expenses.get(category);
    }

    double getTotal(Categories category) {
        double sum = 0;
        for (Product product : getProducts(category)) {
            sum += product.getPrice();
        }
        return sum;
    }

    static double getTotal(List<Product> list) {
        double sum = 0;
        for (Product product : list) {
            sum += product.getPrice();
        }
        return sum;
    }
}

class ExpenseAnalyzer {

    private ExpenseAnalyzingMethod method;

    public void setMethod(ExpenseAnalyzingMethod method) {
        this.method = method;
    }

    public void analyze(Expenses expenses) {
        this.method.analyze(expenses);
    }
}

interface ExpenseAnalyzingMethod {
    void analyze(Expenses expenses);
}

class SortAll implements ExpenseAnalyzingMethod {
    public void analyze(Expenses expenses) {
        List<Product> products = expenses.getProducts();
        if (products.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            Collections.sort(products);
            System.out.println("\nAll:");
            for (int i = products.size() - 1; i >= 0; i--) {
                System.out.println(products.get(i).toString());
            }
            System.out.printf("Total sum: $%.2f\n", Expenses.getTotal(products));
        }
    }
}

class SortByType implements ExpenseAnalyzingMethod {
    public void analyze(Expenses expenses) {
        List<Category> categories = new ArrayList<>();
        Category category;
        double overallTotal = 0;

        System.out.println("\nTypes:");

        for (Categories value : Category.categories.values()) {
            category = new Category(value);
            category.setTotalValue(expenses.getTotal(value));
            categories.add(category);
        }
        Collections.sort(categories);
        for (int i = categories.size() - 1; i >= 0; i--) {
            System.out.printf("%s - $%.2f\n", categories.get(i).getName(), categories.get(i).getTotalValue());
            overallTotal += categories.get(i).getTotalValue();
        }
        System.out.printf("Total: $%.2f\n", overallTotal);
    }
}

class SortCertainType implements ExpenseAnalyzingMethod {
    Categories category;
    SortCertainType(Categories category) {
        this.category = category;
    }
    public void analyze(Expenses expenses) {
        List<Product> products = expenses.getProducts(category);
        if (products.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            Collections.sort(products);
            System.out.printf("\n%s:\n", category.name);
            for (int i = products.size() - 1; i >= 0; i--) {
                System.out.println(products.get(i).toString());
            }
            System.out.printf("Total sum: $%.2f\n", Expenses.getTotal(products));
        }
    }
}
