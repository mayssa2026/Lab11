package customers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BankingSystemTest {

    private Account account1;
    private Account account2;
    private Customer customer;
    private Customer customer2;
    
    
        @BeforeEach
        void setUp() {
            customer = new Customer("John Doe");
            account1 = new Account(1);
            account1.setCustomer(customer);
            account1.deposit(1000.0);
            customer2 = new Customer("John Doe2");
            account2 = new Account(2);
            account2.deposit(1000.0);
            account2.setCustomer(customer2);
    }

    @Test
    void testAccountCreation() {
        assertNotNull(account1);
        assertEquals(1000.0, account1.getBalance());
    }

    @Test
    void testDepositFunctionality() {
        account1.deposit(500.0);
        assertEquals(1500.0, account1.getBalance());
    }

    @Test
    void testWithdrawalFunctionality() {
        account1.withdraw(200.0);
        assertEquals(800.0, account1.getBalance());
    }

    @Test
    void testInsufficientFunds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account1.withdraw(2000.0);
        });
        assertEquals("Insufficient funds", exception.getMessage());
    }

    @Test
    void testTransactionHistory() {
        account1.deposit(500.0);
        account1.withdraw(200.0);
        List<AccountEntry> transactions = (List<AccountEntry>) account1.getEntryList();
        assertEquals(3, transactions.size());
    }

    @Test
    void testCustomerCreation() {
        assertNotNull(customer);
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testTransferBetweenAccounts() {
        account1.transferFunds(account2, 300, null);
        assertEquals(700.0, account1.getBalance());
        assertEquals(1300.0, account2.getBalance());
    }
}