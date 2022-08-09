package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import static budget.Category.*;

public class App {
    private boolean running;
    private String pathToFileExpenses = ".\\purchases.txt";
    final UserInput userInput;
    final Account account;
    final Expenses purchases;
    final ExpenseAnalyzer analyzer;

    App() {
        this.userInput = new UserInput();
        this.account = new Account();
        this.purchases = new Expenses();
        this.analyzer = new ExpenseAnalyzer();
    }

    void start() {
        this.running = true;
        main();
    }

    void main() {
        do {
            System.out.println(Menu.mainMenu);
            int button = userInput.scanButton();

            switch (button) {
                case 1: addIncome(); break;
                case 2: purchase(); break;
                case 3: showPurchases(); break;
                case 4: displayBalance(); break;
                case 5: saveExpenses(); break;
                case 6: loadExpenses(); break;
                case 7: selectAnalyzer(); break;
                case 0:
                    System.out.println("\nBye!");
                    running = false;
                    break;
                default: break;
            }
        } while (running);
    }

    void addIncome() {
        System.out.println("\nEnter income:");
        String amount = userInput.scan();
        if (account.deposit(amount)) {
            System.out.println("Income was added!");
        }
    }

    void purchase() {
        do {
            System.out.println(Menu.purchaseMenu);
            int key = userInput.scanButton();
            Categories category = categories.get(key);
            if (categories.containsKey(key)) {
                purchase(category);
            }
        }  while (userInput.getButton() != numberOfCategories + 1);
    }

    void purchase(Categories category) {
        System.out.println("\nEnter purchase name:");
        String itemName = userInput.scan();
        System.out.println("Enter its price:");
        String price = userInput.scan();
        if (account.withdraw(price) && purchases.addExpense(category, itemName, price)) {
            System.out.println("Purchase was added!");
        }
    }

    void showPurchases() {
        do {
            System.out.println(Menu.showPurchasesMenu);

            int key = userInput.scanButton();

            if (categories.containsKey(key)) {
                Categories category = categories.get(key);
                System.out.printf("\n%s:\n", category.name);
                listExpenses(purchases.getProducts(category));
            } else if (key == numberOfCategories + 1) {
                System.out.println("\nAll:");
                listExpenses(purchases.getProducts());
            }
        }  while (userInput.getButton() != numberOfCategories + 2);
    }

    void listExpenses(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("The purchase list is empty!\n");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.printf("Total sum: %.2f\n\n", Expenses.getTotal(products));
        }
    }

    void selectAnalyzer() {
        System.out.println(Menu.analyzerMenu);
        int button = userInput.scanButton();

        do {
            switch (button) {
                case 1:
                    analyzer.setMethod(new SortAll());
                    analyzer.analyze(purchases);
                    break;
                case 2:
                case 3:
                default: break;
            }
        } while (userInput.getButton() != 4);
    }

    void displayBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", account.getBalance());
    }

    void saveExpenses() {
        String pathToFile = pathToFileExpenses;
        File file = new File(pathToFile);

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(account.getBalance());
            for (Integer key : categories.keySet()) {
                for (Product product : purchases.getProducts(categories.get(key))) {
                    writer.printf("%d\t%s\t%.2f\n", key, product.getName(), product.getPrice());
                }
            }
            System.out.printf("\nPurchases were saved! In -> %s!\n", file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void loadExpenses() {
        String pathToFile = pathToFileExpenses;
        File file = new File(pathToFile);
        String[] values;

        try (Scanner scan = new Scanner(file)) {
            String balance = scan.nextLine();
            account.deposit(balance);

            while (scan.hasNextLine()) {
                values = scan.nextLine().split("\t");

                int key = Integer.parseInt(values[0]);
                Categories category = categories.get(key);

                purchases.addExpense(category, values[1], values[2]);
            }
            System.out.println("\nPurchases were loaded!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
