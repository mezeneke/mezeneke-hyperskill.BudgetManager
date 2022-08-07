package budget;

public class Account {
    private double balance;

    Account() {
        this.balance = 0;
    }

    double getBalance() {
        return balance;
    }

    boolean deposit(String amount) {
        this.balance += Double.parseDouble(amount);
        return true;
    }

    boolean withdraw(String amount) {
        if (this.balance - Double.parseDouble(amount) < 0) {
            this.balance = 0;
        } else {
            this.balance -= Double.parseDouble(amount);
        }
        return true;
    }
}
