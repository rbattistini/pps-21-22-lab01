package lab01.example.model;

/**
 * This class represents a particular instance of a BankAccount with
 * a fee associated to each withdrawal.
 */
public class SimpleBankAccountWithAtm extends AbstractSimpleBankAccount {

    public SimpleBankAccountWithAtm(final AccountHolder holder, final double balance) {
        super(balance, holder);
    }

    @Override
    protected double getFee() {
        return 1.0;
    }

}
