
package test;

import main.Transaction;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    public void testTransactionType() {
        Transaction transaction = new Transaction("Deposit", 100);

        assertEquals("Deposit", transaction.getType());
    }

    @Test
    public void testTransactionAmount() {
        Transaction transaction = new Transaction("Withdrawal", 50);

        assertEquals(50, transaction.getAmount(), 0.01);
    }

    @Test
    public void testTransactionTimestamp() {
        Transaction transaction = new Transaction("Deposit", 100);

        assertNotNull(transaction.getTimestamp());
    }

    @Test
    public void testTransactionToString() {
        Transaction transaction = new Transaction("Deposit", 100);

        assertTrue(transaction.toString().contains("Deposit"));
        assertTrue(transaction.toString().contains("100"));
    }
}
