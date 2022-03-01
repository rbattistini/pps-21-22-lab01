import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccountWithAtm implementation
 */
public class SimpleBankAccountWithAtmTest {

    private static final int INVALID_USER_ID = 2;
    private static final double EMPTY_BALANCE = 0.0;
    private static final double DUMMY_DEPOSIT = 100.0;
    private static final double DUMMY_WITHDRAW = 70.0;
    private static final double FEE = 1.0;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        int userId = 1;
        accountHolder = new AccountHolder("Mario", "Rossi", userId);
        bankAccount = new SimpleBankAccountWithAtm(accountHolder, EMPTY_BALANCE);
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
        bankAccount.deposit(accountHolder.getId(), -DUMMY_DEPOSIT);
        assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWrongIdDeposit() {
        bankAccount.deposit(INVALID_USER_ID, DUMMY_DEPOSIT);
        assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        double depositedAmount = 100.0;
        double withdrawnAmount = 80.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - withdrawnAmount - FEE, bankAccount.getBalance());
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
        final double depositedAmount = 100.0;
        final double withdrawnAmount = 120.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(INVALID_USER_ID, withdrawnAmount);
        assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
