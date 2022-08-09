package budget;

import static budget.Category.*;

public class Menu {

    static final String mainMenu =
            "\nChoose your action\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "5) Save\n" +
            "6) Load\n" +
            "7) Analyze\n" +
            "0) Exit";

    static final String purchaseMenu =
            "\nChoose the type of purchase\n" +
            buildCategorySelection() +                              // buttons for all categories
            String.format("%d) Back", numberOfCategories + 1);      // button Back

    static final String showPurchasesMenu =
            "\nChoose the type of purchases\n" +
            buildCategorySelection() +                              // buttons for all categories
            String.format("%d) All\n", numberOfCategories + 1) +    // button All
            String.format("%d) Back", numberOfCategories + 2);      // button Back

    static final String analyzerMenu =
            "\nHow do you want to sort?\n" +
                    "1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back";



    private static String buildCategorySelection() {
        StringBuilder strB = new StringBuilder();
        for (Categories value : Categories.values()) {
            strB.append(String.format("%d) %s\n", value.ordinal() + 1, value.name));
        }
        return strB.toString();
    }

}
