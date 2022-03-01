import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int INVALID_USER_ID = 2;
    public static final int EMPTY_BALANCE = 0;
    public static final int DUMMY_DEPOSIT = 100;
    public static final int DUMMY_WITHDRAW = 70;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        int userId = 1;
        accountHolder = new AccountHolder("Mario", "Rossi", userId);
        bankAccount = new SimpleBankAccount(accountHolder, EMPTY_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeDeposit() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        bankAccount.deposit(accountHolder.getId(), -DUMMY_DEPOSIT);
        assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongIdDeposit() {
        bankAccount.deposit(INVALID_USER_ID, DUMMY_DEPOSIT);
        assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        int depositedAmount = 100;
        int withdrawnAmount = 80;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - withdrawnAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongIdWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        bankAccount.withdraw(INVALID_USER_ID, DUMMY_WITHDRAW);
        assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        bankAccount.withdraw(accountHolder.getId(), -DUMMY_WITHDRAW);
        assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testDisallowedWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 120;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(INVALID_USER_ID, withdrawnAmount);
        assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
