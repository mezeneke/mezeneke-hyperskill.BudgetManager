package budget;

import java.util.HashMap;
import java.util.Map;

public class Category {

    static final Map<Integer, Categories> categories = buildCategoryMap();
    static final int numberOfCategories = Categories.values().length;

    static Map<Integer, Categories> buildCategoryMap() {
        Map<Integer, Categories> map = new HashMap<>();
        Categories[] values = Categories.values();

        for (int i = 1; i <= values.length; i++) {
            map.put(i, values[i]);
        }
        return map;
    }

    enum Categories {
        FOOD("Food"),
        CLOTHES("Clothes"),
        ENTERTAINMENT("Entertainment"),
        OTHER("Other");

        final String categoryName;

        Categories(String category) {
            this.categoryName = category;
        }
    }
}


