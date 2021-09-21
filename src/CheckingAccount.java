public class CheckingAccount extends BankAccount {

    private static final int MAX_FREE_WITHDRAWALS = 3;
    private static final double TRANSACTION_FEE = 1.0;
    private int withdrawalsThisMonth = 0;

    public CheckingAccount ( long accountId ) {
        super ( accountId );
    }

    @Override
    public void withdraw ( double amount ) {
        super.withdraw ( amount );
        withdrawalsThisMonth++;

        if ( withdrawalsThisMonth > MAX_FREE_WITHDRAWALS ) {
            super.withdraw ( TRANSACTION_FEE );
        }
    }

    @Override
    public void monthEnd () {
        withdrawalsThisMonth = 0;
    }

    @Override
    public String toString () {
        return super.toString ( ) + " with " + withdrawalsThisMonth +
               " withdrawals this month";
    }

}
