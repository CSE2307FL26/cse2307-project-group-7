package main;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerDirectory {
    private HashMap<String, Customer> customers;

    public CustomerDirectory(){
        this.customers = new HashMap<>();
    }

    public void registerCustomer(Customer customer){
        String username = customer.getUsername();
        if(customer == null || username == null){
            throw new IllegalArgumentException();
        }
        if(customers.containsKey(username)){
            throw new IllegalArgumentException("Username already taken");
        }
        else {
            customers.put(username, customer);
        }
    }

    public Customer login(String username, String password){
        Customer customer = customers.get(username);
        if(customer == null){
            throw new IllegalArgumentException("Username not found");
        }
        else if(!customer.getPassword().equals(password)){
            throw new IllegalArgumentException("Incorrect password");
        }
        else{
            return customer;
        }
    }

    public HashMap<String, Customer> getCustomers(){
        return customers;
    }
}
