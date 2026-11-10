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


    public boolean removeAccount(int accountIndex){
        if (accountIndex < 0 || accountIndex >= accounts.size()){
            return false;
        }

        accounts.remove(accountIndex);
        return true;
    }


    public ArrayList<BankAccount> listAccounts(){
        return new ArrayList<>(accounts);
    }


}
