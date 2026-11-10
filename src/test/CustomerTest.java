package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import main.BankAccount;
import main.Customer;

public class CustomerTest {

    @Test 
    public void testCustomerID(){
        Customer customer = new Customer(1);

        int id = customer.getCustomerID();

        assertEquals(1, id);
    }

    @Test 
    public void testAddAcct(){
        Customer customer = new Customer(1);
        BankAccount account = new BankAccount();

        customer.addAccount(account);

        assertEquals(1, customer.listAccounts().size());
    }

    @Test 
    public void testCloseAcct(){
        Customer customer = new Customer(1);
        BankAccount account = new BankAccount();

        customer.addAccount(account);
        customer.removeAccount(0);

        assertEquals(0, customer.listAccounts().size());
    }
}
