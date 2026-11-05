package test;

import main.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testTransactionHistoryEmpty() {
        BankAccount account = new BankAccount();

        assertEquals(0, account.getTransactionHistory().size());
    }

    @Test
    public void testSingleTransaction() {
        BankAccount account = new BankAccount();

        account.deposit(100);

        assertEquals(1, account.getTransactionHistory().size());
        assertEquals("Deposit", account.getTransactionHistory().get(0).getType());
        assertEquals(100, account.getTransactionHistory().get(0).getAmount(), 0.01);
    }

    @Test
    public void testMultipleTransactions() {
        BankAccount account = new BankAccount();

        account.deposit(100);
        account.deposit(50);

        assertEquals(2, account.getTransactionHistory().size());

        assertEquals(100, account.getTransactionHistory().get(0).getAmount(), 0.01);

        assertEquals(50, account.getTransactionHistory().get(1).getAmount(), 0.01);
    }


    
}
