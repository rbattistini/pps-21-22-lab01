import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends BasicSimpleBankAccountTest {

    @Override
    @BeforeEach
    public void beforeEach(){
        int userId = 1;
        accountHolder = new AccountHolder("Mario", "Rossi", userId);
        bankAccount = new SimpleBankAccount(accountHolder, EMPTY_BALANCE);
    }

    @Test
    void testWithdraw() {
        double depositedAmount = 100.0;
        double withdrawnAmount = 80.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - withdrawnAmount, bankAccount.getBalance());
    }

    @Test
    void testDisallowedWithdraw() {
        final double depositedAmount = 100.0;
        final double withdrawnAmount = 120.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(INVALID_USER_ID, withdrawnAmount);
        Assertions.assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
