
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

    @Test
    public void testWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        testAccount.withdraw(20);
        assertEquals(30, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        try {
            testAccount.withdraw(-20);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        BankAccount testAccount = new BankAccount();
        testAccount.withdraw(20);
        assertEquals(-20, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testEmptyWithdraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        testAccount.withdraw(0);
        assertEquals(50, testAccount.getBalance(), 0.1);
    }

    @Test
    public void testAddInterestPayment() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.addInterestPayment(20);

        assertEquals(120, account.getBalance(), 0.01);
    }

    @Test
    public void testAddInterestToEmptyAccount() {
        BankAccount account = new BankAccount();
        account.addInterestPayment(25);

        assertEquals(25, account.getBalance(), 0.01);
    }

    @Test
    public void testMultipleInterestPayments() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.addInterestPayment(10);
        account.addInterestPayment(15);

        assertEquals(125, account.getBalance(), 0.01);
    }

    @Test
    public void testNegativeInterestPayment() {
        BankAccount account = new BankAccount();

        assertThrows(IllegalArgumentException.class, () -> {
            account.addInterestPayment(-10);
        });

        assertEquals(0, account.getBalance(), 0.01);
    }

    @Test
    public void testZeroInterestPayment() {
        BankAccount account = new BankAccount();

        assertThrows(IllegalArgumentException.class, () -> {
            account.addInterestPayment(0);
        });

        assertEquals(0, account.getBalance(), 0.01);
    }

    @Test
    public void testInterestPaymentTransactionHistory() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.addInterestPayment(20);

        assertEquals(2, account.getTransactionHistory().size());
        assertEquals("Interest Payment",
            account.getTransactionHistory().get(1).getType());
        assertEquals(20,
            account.getTransactionHistory().get(1).getAmount(), 0.01);
    }
}
