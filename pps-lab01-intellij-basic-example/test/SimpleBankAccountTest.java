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
        final int userId = 1;
        accountHolder = new AccountHolder("Mario", "Rossi", userId);
        bankAccount = new SimpleBankAccount(accountHolder, EMPTY_BALANCE);
    }

    @Test
    void testWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 80;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - withdrawnAmount, bankAccount.getBalance());
    }

    @Test
    void testDisallowedWithdraw() {
        final int depositedAmount = 100;
        final int withdrawnAmount = 120;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(INVALID_USER_ID, withdrawnAmount);
        Assertions.assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
