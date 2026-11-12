package test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testCheckAccountBalanceOneAccount(){
        Customer customer = new Customer(1);
        BankAccount account = new BankAccount();
        account.deposit(10);
        customer.addAccount(account);
        
        assertEquals(10, customer.checkAccountBalance(account));
    }

    @Test 
    public void testCheckAccountBalanceMultipleAccounts(){
        Customer customer = new Customer(1);
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();
        account1.deposit(10);
        account2.deposit(20);
        customer.addAccount(account1);
        customer.addAccount(account2);
        
        assertEquals(20, customer.checkAccountBalance(account2));
    }

    @Test 
    public void testInvalidCheckAccountBalance(){
        Customer customer = new Customer(1);
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount();
        account1.deposit(10);
        account2.deposit(20);
        customer.addAccount(account1);
        try {
            customer.checkAccountBalance(account2);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }
    
    @Test 
    public void testCloseAcct(){
        Customer customer = new Customer(1);
        BankAccount account = new BankAccount();

        customer.addAccount(account);
        customer.removeAccount(0);

        assertEquals(0, customer.listAccounts().size());
    }

    @Test
    public void testRemoveAccountDoesNotExist() {
        Customer customer = new Customer(1);

        boolean result = customer.removeAccount(0);

        assertFalse(result);
    }
}
