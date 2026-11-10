package main;

import java.util.Scanner;

import java.util.ArrayList;

public class MainMenu {

    private static final int EXIT_SELECTION = 2;
    private static final int MAX_SELECTION = 8;

	private Customer currentCustomer;
    private Scanner keyboardInput;

    public MainMenu() {
        this.currentCustomer = new Customer(1);
        this.currentCustomer.addAccount(new BankAccount());
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
    System.out.println("Welcome to the 237 Bank App!");

    System.out.println("1. Make a deposit");
    System.out.println("2. Exit the app");
    System.out.println("3. Collect an account fee (admin)");
    System.out.println("4. Add an interest payment (admin)");
    System.out.println("5. View transaction history");
    System.out.println("8. Transfer between accounts");
}

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
    switch (selection) {
        case 1:
            performDeposit();
            break;
        case 2:
            break;
        case 3:
            performCollectFee();
            break;
        case 4:
            performAddInterestPayment();
            break;
        case 5:
            performViewTransactionHistory();
            break;
        case 8:
            performTransfer();
            break;
        default:
            System.out.println("Invalid selection.");
    }
}


    public BankAccount selectAccount() {
        ArrayList<BankAccount> accounts = currentCustomer.listAccounts();
    
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return null;
        }
    
        System.out.println("Your accounts:");
    
        for (BankAccount account : accounts) {
            System.out.println("Account ID: " + account.getAccountID() + " | Balance: $" + account.getBalance());
        }
    
        System.out.print("Enter your account ID: ");
        int accountID = keyboardInput.nextInt();
    
        for (BankAccount account : accounts) {
            if (account.getAccountID() == accountID) {
                return account;
            }
        }
    
        System.out.println("Account not found.");
        return null;
    }
    

    public void performViewTransactionHistory() {
    BankAccount account = selectAccount();

    if (account == null) {
        return;
    }

    ArrayList<Transaction> history = account.getTransactionHistory();

    if (history.isEmpty()) {
        System.out.println("No transactions found.");
        return;
    }

    for (Transaction transaction : history) {
        System.out.println(transaction);
    }
}


public void performDeposit() {
    BankAccount account = selectAccount();

    if (account == null) {
        return;
    }

    System.out.print("How much do you want to deposit: ");
    double amount = keyboardInput.nextDouble();

    try {
        account.deposit(amount);
        System.out.println("Deposit successful.");
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid deposit amount.");
    }
}

public void performTransfer() {
    if (currentCustomer.listAccounts().size() < 2) {
        System.out.println(
            "You need at least two accounts to transfer money."
        );
        return;
    }

    System.out.println("Select source account:");
    BankAccount source = selectAccount();

    if (source == null) {
        return;
    }

    System.out.println("Select target account:");
    BankAccount target = selectAccount();

    if (target == null) {
        return;
    }

    System.out.print("Enter transfer amount: ");
    double amount = keyboardInput.nextDouble();

    try {
        source.transfer(target, amount);
        System.out.println("Transfer successful.");
    } catch (IllegalArgumentException e) {
        System.out.println("Transfer failed.");
    }
}
    
public void performCollectFee() {
    BankAccount account = selectAccount();

    if (account == null) {
        return;
    }

    System.out.print("Enter the fee amount: ");
    double feeAmount = keyboardInput.nextDouble();

    try {
        account.collectFee(feeAmount);
        System.out.println("Fee collected successfully.");
    } catch (IllegalArgumentException e) {
        System.out.println("Unable to collect fee, invalid amount.");
    }
}

public void performAddInterestPayment() {
    BankAccount account = selectAccount();

    if (account == null) {
        return;
    }

    System.out.print("Enter the interest payment amount: ");
    double interestAmount = keyboardInput.nextDouble();

    try {
        account.addInterestPayment(interestAmount);
        System.out.println("Interest payment added successfully.");
    } catch (IllegalArgumentException e) {
        System.out.println("Unable to add interest payment: invalid amount.");
    }
}


    public void run() {
        int selection = -1;
        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
}
