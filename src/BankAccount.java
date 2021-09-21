public abstract class BankAccount {

    private final long accountId;
    private double balance;

    public BankAccount ( long accountId ) {
        this.accountId = accountId;
        balance = 0.0;
    }

    public void deposit ( double amount ) {
        balance += amount;
    }

    public void withdraw ( double amount ) {
        balance -= amount;
    }

    public double getBalance () {
        return balance;
    }

    public long getAccountId () {
        return accountId;
    }

    @Override
    public String toString () {
        return String.format ( "%s with id %d and balance £%,.02f" ,
            getClass ( ).getName ( ) , accountId , balance
                             );
    }

    public abstract void monthEnd ();
}
