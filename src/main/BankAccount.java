package main;

public class BankAccount {

    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void collectFee(double amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException("Invalid fee amount");
        }
        balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }
}
