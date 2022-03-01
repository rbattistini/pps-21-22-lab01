import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class BasicSimpleBankAccountTest {

    protected static final double EMPTY_BALANCE = 0.0;
    protected static final int INVALID_USER_ID = 2;
    private static final double DUMMY_DEPOSIT = 100.0;
    private static final double DUMMY_WITHDRAW = 70.0;
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    public abstract void beforeEach();

    @Test
    void testInitialBalance() {
        Assertions.assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        Assertions.assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeDeposit() {
        bankAccount.deposit(accountHolder.getId(), -DUMMY_DEPOSIT);
        Assertions.assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWrongIdDeposit() {
        bankAccount.deposit(INVALID_USER_ID, DUMMY_DEPOSIT);
        Assertions.assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWrongIdWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        bankAccount.withdraw(INVALID_USER_ID, DUMMY_WITHDRAW);
        Assertions.assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testNegativeWithdraw() {
        bankAccount.deposit(accountHolder.getId(), DUMMY_DEPOSIT);
        bankAccount.withdraw(accountHolder.getId(), -DUMMY_WITHDRAW);
        Assertions.assertEquals(DUMMY_DEPOSIT, bankAccount.getBalance());
    }
}
