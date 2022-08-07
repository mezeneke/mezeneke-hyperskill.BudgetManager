package budget;

import static budget.Category.*;

public class UI {
    static final String mainMenu = """
                Choose your action
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                0) Exit
                """;

    static final String purchaseCategorySelectionMenu = """
        Choose the type of purchase:
        %s
        %d) Back
        """.formatted(buildCategorySelection(), numberOfCategories + 1);

    static final String showCategorySelectionMenu = """
            Choose the type of purchases
            %s
            %d) All
            %d) Back
            """.formatted(buildCategorySelection(), numberOfCategories + 1, numberOfCategories + 2);

    private static String buildCategorySelection() {
        StringBuilder strB = new StringBuilder();
        for (Categories value : Categories.values()) {
            strB.append(String.format("%d) %s\n", value.ordinal() + 1, value.categoryName));
        }
        return strB.toString();
    }

}
