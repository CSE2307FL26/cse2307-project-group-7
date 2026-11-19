package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import main.Customer;
import main.CustomerDirectory;


public class CustomerDirectoryTest {
    
    @Test 
    public void testRegisterCustomerValidUsername(){
        CustomerDirectory directory = new CustomerDirectory();
        Customer customer = new Customer(1, "testuser", "testPassword");
        directory.registerCustomer(customer);
        assertTrue(directory.getCustomers().containsKey(customer.getUsername()));
    }

    @Test
    public void testRegisterCustomerDuplicateUsername(){
        CustomerDirectory directory = new CustomerDirectory();
        Customer customer1 = new Customer(1, "testuser", "testPassword1");
        Customer customer2 = new Customer(2, "testuser", "testPassword2");
        directory.registerCustomer(customer1);
        try {
            directory.registerCustomer(customer2);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test 
    public void testValidLogin(){
        CustomerDirectory directory = new CustomerDirectory();
        Customer customer1 = new Customer(1, "testuser", "testPassword1");
        String username = customer1.getUsername();
        String password = customer1.getPassword();
        directory.registerCustomer(customer1);
        assertEquals(directory.login(username,password), customer1);
    }

    @Test
    public void testInvalidLoginWrongUsername(){
        CustomerDirectory directory = new CustomerDirectory();
        Customer customer1 = new Customer(1, "testuser", "testPassword1");
        String password = customer1.getPassword();
        directory.registerCustomer(customer1);
        try {
            directory.login("hello",password);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test 
    public void testInvalidLoginWrongPassword(){
        CustomerDirectory directory = new CustomerDirectory();
        Customer customer1 = new Customer(1, "testuser", "testPassword1");
        String username = customer1.getUsername();
        directory.registerCustomer(customer1);
        try {
            directory.login(username,"hello");
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }
}
