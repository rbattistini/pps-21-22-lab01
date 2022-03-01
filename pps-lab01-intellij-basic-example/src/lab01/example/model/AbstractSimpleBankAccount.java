package lab01.example.model;

/**
 * This class represents a particular instance of a BankAccount.
 * In particular, a Simple Bank Account always allows the deposit
 * of a positive amount while the withdrawal is allowed only if
 * the amount of the withdrawal is positive and the balance is
 * greater or equal to the withdrawal amount.
 */
public abstract class AbstractSimpleBankAccount implements BankAccount {
    protected final AccountHolder holder;
    protected double balance;

    public AbstractSimpleBankAccount(final double balance, final AccountHolder holder) {
        this.balance = balance;
        this.holder = holder;
    }

    @Override
    public AccountHolder getHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID) && checkNonNegativeAmount(amount)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (checkUser(userID) && checkNonNegativeAmount(amount) &&
                isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount - getFee();
        }
    }

    protected abstract double getFee();

    private boolean checkNonNegativeAmount(final double amount) {
        return amount >= 0;
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= amount;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
