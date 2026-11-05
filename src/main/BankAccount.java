package main;
import java.util.ArrayList;


public class BankAccount {

    private double balance;
    private ArrayList<Transaction> transactionHistory;
    private int accountID;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if(amount >= 0) {
            this.balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount){
        if(amount>=0){
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
}

