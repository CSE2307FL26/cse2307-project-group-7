package main;

import java.util.Scanner;

public class LoginMenu {
    private static final int LOGIN_SELECTION = 1;
    private static final int CREATE_USER_SELECTION = 2;
    private static final int EXIT_SELECTION = 3;
    private static final int MAX_SELECTION = 3;
    private Scanner keyboardInput;
    private CustomerDirectory directory;
    private Customer loggedInCustomer;

    public LoginMenu(CustomerDirectory directory, Scanner keyboardInput){
        this.directory = directory;
        this.keyboardInput = keyboardInput;
    }

    public void displayLoginPrompt(){
        System.out.println("Welcome to the 237 Bank App!");

        System.out.println("1. Login to user account");
        System.out.println("2. Create new user account");
        System.out.println("3. Exit the app");
    }

    public Customer performLogin(){
        System.out.println("Enter username: ");
        String username = keyboardInput.next();
        System.out.println("Enter password: ");
        String password = keyboardInput.next();

        try{
            Customer customer = directory.login(username, password);
            System.out.println("Successful login. Welcome " + username);
            return customer;
        } catch(IllegalArgumentException e){
            System.out.println("Invalid username or password");
            return null;
        }
    }

    public void performCreateUser(){
        System.out.println("Choose a username: ");
        String username = keyboardInput.next();
        System.out.println("Choose a password: ");
        String password = keyboardInput.next();

        int id = directory.getCustomers().size() + 1;
        Customer newCustomer = new Customer(id, username, password);
        try{
            directory.registerCustomer(newCustomer);
            System.out.println("User account created successfully.");
        } catch(IllegalArgumentException e){
            System.out.println("Username already taken.");
        }
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }
    public void processInput(int selection){
        switch (selection) {
            case LOGIN_SELECTION:
                loggedInCustomer = performLogin();
                break;
            case CREATE_USER_SELECTION:
                performCreateUser();
                break;
            case EXIT_SELECTION:
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    public Customer run() {
        loggedInCustomer = null;

        int selection = -1;
        while (selection != EXIT_SELECTION && loggedInCustomer == null) {
            displayLoginPrompt();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
        return loggedInCustomer;
    }
}
