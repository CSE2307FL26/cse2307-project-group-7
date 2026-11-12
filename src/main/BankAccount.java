package main;
import java.util.ArrayList;

public class BankAccount {

    private double balance;
    private ArrayList<Transaction> transactionHistory;
    private static int nextAccountID = 1;
    private int accountID;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
        this.accountID = nextAccountID++;
    }
    public int getAccountID() {
        return accountID;
    }

    public void deposit(double amount) {
        if (amount >= 0) {
            this.balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount) {
        if (amount >= 0) {
            this.balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void collectFee(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid fee amount");
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Fee", amount));
    }

   public void addInterestPayment(double amount) {
    if (!Double.isFinite(amount) || amount <= 0) {
        throw new IllegalArgumentException(
            "Invalid interest payment amount"
        );
    }

    balance += amount;
    transactionHistory.add(new Transaction("Interest Payment", amount));
}

    public double getBalance() {
        return this.balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }


    public void transfer(BankAccount target, double amount) {
        if (target == null || target == this) {
            throw new IllegalArgumentException();
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException();
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException();
        }

        this.balance -= amount;
        target.balance += amount;

        this.transactionHistory.add(
            new Transaction("Transfer Out", amount)
        );

        target.transactionHistory.add(
            new Transaction("Transfer In", amount)
        );
    }

}

