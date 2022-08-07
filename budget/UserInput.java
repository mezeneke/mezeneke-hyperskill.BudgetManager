package budget;

import java.util.Scanner;

public class UserInput {
    final Scanner scan;
    private String value;

    UserInput() {
        this.scan = new Scanner(System.in);
    }

    public String getValue() {
        return value;
    }

    String scan() {
        this.value = scan.nextLine();
        return value;
    }
}
