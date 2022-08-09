package budget;

import java.util.Scanner;

public class UserInput {
    private final Scanner scan;
    private String value;

    private int button;

    UserInput() {
        this.scan = new Scanner(System.in);
    }

    public String getValue() {
        return value;
    }

    public int getButton() {
        return button;
    }

    String scan() {
        this.value = scan.nextLine();
        return value;
    }

    int scanButton() {
        this.button = scan.nextInt();
        scan.nextLine();
        return button;
    }

    int scanButton(boolean store) {
        int button = scan.nextInt();
        scan.nextLine();
        if (store) {
            this.button = button;
        }
        return button;
    }
}
