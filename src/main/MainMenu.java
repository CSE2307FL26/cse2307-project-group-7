package main;

import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 4;
    private static final int MAX_SELECTION = 4;

    private BankAccount userAccount;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccount = new BankAccount();
        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");

        System.out.println("1. Make a deposit");
        System.out.println("2. Collect an account fee (admin)");
        System.out.println("3. Add an interest payment (admin)");
        System.out.println("4. Exit the app");
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
                performCollectFee();
                break;
            case 3:
                performAddInterestPayment();
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while (depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount.deposit(depositAmount);
    }

    public void performCollectFee() {
        System.out.print("Enter the fee amount: ");
        double feeAmount = keyboardInput.nextDouble();

        try {
            userAccount.collectFee(feeAmount);
            System.out.println("Fee collected successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to collect fee: amount must be positive and cannot exceed the balance.");
        }
    }

    public void performAddInterestPayment() {
        System.out.print("Enter the interest payment amount: ");
        double interestAmount = keyboardInput.nextDouble();

        try {
            userAccount.addInterestPayment(interestAmount);
            System.out.println("Interest payment added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to add interest payment: amount must be positive.");
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
