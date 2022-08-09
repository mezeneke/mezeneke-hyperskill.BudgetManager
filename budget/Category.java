package budget;

import java.util.HashMap;
import java.util.Map;

public class Category implements Comparable<Category> {
    enum Categories {
        FOOD("Food"),
        CLOTHES("Clothes"),
        ENTERTAINMENT("Entertainment"),
        OTHER("Other");

        final String name;
        Categories(String category) {
            this.name = category;
        }
    }
    static final Map<Integer, Categories> categories = buildCategoryMap();
    static final int numberOfCategories = Categories.values().length;
    private String name;
    private double totalValue;

    Category(Categories category) {
        this.name = category.name;
        this.totalValue = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public int compareTo(Category other) {
        if (this.totalValue < other.totalValue) {
            return -1;
        } else if (this.totalValue == other.totalValue) {
            return this.getName().compareToIgnoreCase(other.getName());
        } else {
            return 1;
        }
    }

    static Map<Integer, Categories> buildCategoryMap() {
        Map<Integer, Categories> map = new HashMap<>();
        Categories[] values = Categories.values();
        for (int i = 0; i < values.length; i++) {
            map.put(i + 1, values[i]);
        }
        return map;
    }
}
