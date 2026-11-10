package main;

import java.util.ArrayList;

public class Customer {

    private int customerID;

    private ArrayList<BankAccount> accounts;

    public Customer(int customerID){
        this.customerID = customerID;
        this.accounts = new ArrayList<>();
    }

    public int getCustomerID(){
        return customerID;
    }

    public void addAccount(BankAccount account){
        accounts.add(account);
    }


    public ArrayList<BankAccount> listAccounts(){
        return new ArrayList<>(accounts);
    }

    public double checkAccountBalance(BankAccount account){
        if(accounts.contains(account)){
            return account.getBalance();
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
