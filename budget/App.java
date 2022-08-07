package budget;

import static budget.Category.*;

public class App {
    final UserInput userInput;
    final Account account;
    final Expenses purchases;

    App() {
        this.userInput = new UserInput();
        this.account = new Account();
        this.purchases = new Expenses();
    }

    void start() {
        main();
    }

    void main() {
        do {
            System.out.println(UI.mainMenu);
            userInput.scan();

            switch (userInput.getValue()) {
                case "1" -> addIncome();
                case "2" -> purchaseInCategory();
                case "3" -> showExpenses();
                case "4" -> displayBalance();
                case "0" -> System.out.println("\nBye!");
            }
        } while (!userInput.getValue().equals("0"));
    }

    void addIncome() {
        System.out.println("\nEnter income:");
        userInput.scan();
        if (account.deposit(userInput.getValue())) {
            System.out.println("Income was added!\n");
        }
    }

    void purchaseInCategory() {
        do {
            System.out.println(UI.purchaseCategorySelectionMenu);
            if (categories.containsKey(Integer.parseInt(userInput.scan()))) {
                Categories category = categories.get(Integer.parseInt(userInput.getValue()));
                addExpense(category);
            }
        }  while (!userInput.getValue().equals(String.valueOf(numberOfCategories + 1)));
    }

    void addExpense(Categories category) {
        System.out.println("\nEnter purchase name:");
        String itemName = userInput.scan();
        System.out.println("Enter its price:");
        String price = userInput.scan();
        if (account.withdraw(price)) {
            purchases.addExpense(category, itemName, price);
        }
    }

    void showExpenses() {
        do {
            System.out.println(UI.showCategorySelectionMenu);
            if (categories.containsKey(Integer.parseInt(userInput.scan()))) {
                Categories category = categories.get(Integer.parseInt(userInput.getValue()));
                purchases.listExpenses(category);
            } else if (userInput.getValue().equals(String.valueOf(numberOfCategories + 1))) {
                purchases.listExpenses();
            }
        }  while (!userInput.getValue().equals(String.valueOf(numberOfCategories + 2)));
    }

    void displayBalance() {
        System.out.printf("\nBalance: $%.2f\n\n", account.getBalance());
    }
}
