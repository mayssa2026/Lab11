
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.AccountService;
import services.CustomerService;
import messaging.NotificationService;

package customers;


@SpringBootTest
class BankingSystemTest {

	@Mock
	private Account account;

	@Mock
	private AccountEntry accountEntry;

	@Mock
	private Customer customer;

	@Mock
	private AccountService accountService;

	@Mock
	private CustomerService customerService;

	@Mock
	private NotificationService notificationService;

	@InjectMocks
	private BankingSystemTest bankingSystemTest;

	public BankingSystemTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testAccountGetBalance() {
		when(account.getBalance()).thenReturn(1000.0);
		assertEquals(1000.0, account.getBalance());
	}

	@Test
	void testAccountEntryGetAmount() {
		when(accountEntry.getAmount()).thenReturn(200.0);
		assertEquals(200.0, accountEntry.getAmount());
	}

	@Test
	void testCustomerGetName() {
		when(customer.getName()).thenReturn("John Doe");
		assertEquals("John Doe", customer.getName());
	}

	@Test
	void testCustomerGetAccounts() {
		List<Account> accounts = Arrays.asList(new Account(), new Account());
		when(customer.getAccounts()).thenReturn(accounts);
		assertEquals(2, customer.getAccounts().size());
	}

	@Test
	void testAccountServiceCreateAccount() {
		Account newAccount = new Account();
		when(accountService.createAccount("12345")).thenReturn(newAccount);
		assertEquals(newAccount, accountService.createAccount("12345"));
	}

	@Test
	void testCustomerServiceAddCustomer() {
		Customer newCustomer = new Customer();
		when(customerService.addCustomer("Jane Doe")).thenReturn(newCustomer);
		assertEquals(newCustomer, customerService.addCustomer("Jane Doe"));
	}

	@Test
	void testNotificationServiceSendNotification() {
		String message = "Account created successfully";
		when(notificationService.sendNotification("12345", message)).thenReturn(true);
		assertEquals(true, notificationService.sendNotification("12345", message));
	}

	@Test
	void testAccountServiceGetAccountDetails() {
		Account accountDetails = new Account();
		when(accountService.getAccountDetails("12345")).thenReturn(accountDetails);
		assertEquals(accountDetails, accountService.getAccountDetails("12345"));
	}

	@Test
	void testCustomerServiceGetCustomerDetails() {
		Customer customerDetails = new Customer();
		when(customerService.getCustomerDetails("Jane Doe")).thenReturn(customerDetails);
		assertEquals(customerDetails, customerService.getCustomerDetails("Jane Doe"));
	}

	@Test
	void testNotificationServiceSendAlert() {
		String alert = "Low balance alert";
		when(notificationService.sendAlert("12345", alert)).thenReturn(true);
		assertEquals(true, notificationService.sendAlert("12345", alert));
	}
}