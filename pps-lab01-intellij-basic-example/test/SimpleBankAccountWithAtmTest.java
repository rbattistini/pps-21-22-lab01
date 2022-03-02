import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccountWithAtm implementation
 */
public class SimpleBankAccountWithAtmTest extends BasicSimpleBankAccountTest {

    private static final double FEE = 1.0;

    @Override
    @BeforeEach
    public void beforeEach(){
        int userId = 1;
        accountHolder = new AccountHolder("Mario", "Rossi", userId);
        bankAccount = new SimpleBankAccountWithAtm(accountHolder, EMPTY_BALANCE);
    }

    @Test
    void testWithdrawWithAtm() {
        double depositedAmount = 100.0;
        double withdrawnAmount = 80.0;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(accountHolder.getId(), withdrawnAmount);
        assertEquals(depositedAmount - withdrawnAmount - FEE, bankAccount.getBalance());
    }

    @Test
    void testDisallowedWithdrawWithAtm() {
        final double depositedAmount = 100.0;
        final double withdrawnAmount = 99.1;
        bankAccount.deposit(accountHolder.getId(), depositedAmount);
        bankAccount.withdraw(INVALID_USER_ID, withdrawnAmount);
        Assertions.assertEquals(depositedAmount, bankAccount.getBalance());
    }
}
