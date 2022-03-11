package lab01.example.model;

/**
 * This class represents a particular instance of a BankAccount without
 * a fee associated to each withdrawal.
 */
public class SimpleBankAccount extends AbstractSimpleBankAccount {

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    protected double getFee() {
        return 0;
    }

}
