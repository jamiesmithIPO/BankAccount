public class SavingsAccount extends BankAccount {

    private double interestRate;
    private double minBalance;

    public SavingsAccount ( long accountId , double interestRate ) {
        super ( accountId );
        this.interestRate = interestRate / 100;
        this.minBalance = super.getBalance ( );
    }

    @Override
    public void withdraw ( double amount ) {
        super.withdraw ( amount );

        minBalance = super.getBalance ( );
    }

    @Override
    public void monthEnd () {
        super.deposit ( interestRate * minBalance );
        minBalance = super.getBalance ( );
    }

    @Override
    public String toString () {
        return super.toString ( ) + " with interestRate of " +
               interestRate + " and minimum balance of " + minBalance;
    }
}
