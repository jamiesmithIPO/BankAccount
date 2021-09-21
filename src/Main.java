import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Strings for printing out a vaugely nice CLI menu
    private static final String GREEN = "\u001b[32;1m";
    private static final String RED = "\u001b[31;1m";
    private static final String RESET = "\u001b[0m";
    private static List<BankAccount> accounts = new ArrayList<> ( );
    private static String menuString = String.format ( "-= Menu =-%n%s %n%s %n%s %n%s %n%s%n: " ,
        ( GREEN + "D" + RESET + "eposit" ) ,
        ( GREEN + "W" + RESET + "ithdraw" ) ,
        ( GREEN + "M" + RESET + "onth" ) ,
        ( GREEN + "P" + RESET + "rint" ) ,
        ( RED + "Q" + RESET + "uit" )
                                                     );

    static {
        SavingsAccount savingsAccount1 = new SavingsAccount ( 1 , 5.0 );
        savingsAccount1.deposit ( 1000 );
        savingsAccount1.monthEnd ( );
        accounts.add ( savingsAccount1 );
        accounts.add ( new SavingsAccount ( 2 , 5.0 ) );
        accounts.add ( new SavingsAccount ( 3 , 5.0 ) );
        accounts.add ( new SavingsAccount ( 4 , 5.0 ) );
        accounts.add ( new CheckingAccount ( 5 ) );
        accounts.add ( new CheckingAccount ( 6 ) );
        accounts.add ( new CheckingAccount ( 7 ) );
        accounts.add ( new CheckingAccount ( 8 ) );
    }

    public static void deposit ( Scanner input ) {
        long id = getAccountId ( input );
        double amount = getAmount ( "deposit" , input );

        BankAccount account = getAccountWithId ( id );
        account.deposit ( amount );
        System.out.format ( "New Balance: £%,.02f%n" , account.getBalance ( ) );
    }

    public static void withdraw ( Scanner input ) {
        long id = getAccountId ( input );
        double amount = getAmount ( "withdraw" , input );

        BankAccount account = getAccountWithId ( id );
        account.withdraw ( amount );
        System.out.format ( "New Balance: £%,.02f%n" , account.getBalance ( ) );
    }

    public static void monthEnd ( Scanner input ) {
        for ( BankAccount account : accounts ) {
            account.monthEnd ( );
        }
        printAccounts ( );
    }

    public static void printAccounts () {
        for ( BankAccount account : accounts ) {
            System.out.println ( account );
        }
    }

    public static void main ( String[] args ) {
        Scanner input = new Scanner ( System.in );

        boolean exit = false;
        while ( !exit ) {
            System.out.print ( menuString );
            String line = input.nextLine ( );
            if ( line.isBlank ( ) ) {
                continue;
            }

            char c = line.strip ( ).toLowerCase ( ).charAt ( 0 );

            switch (c) {
                case 'd':
                    deposit ( input );
                    break;
                case 'w':
                    withdraw ( input );
                    break;
                case 'm':
                    monthEnd ( input );
                    break;
                case 'p':
                    printAccounts ( );
                    break;
                case 'q':
                    exit = true;
                    break;
                default:
                    System.out.println ( "Did not recognise command \"" + line + "\"" );
            }
        }
    }

    public static double getAmount ( String prompt , Scanner input ) {

        Double amount = null;

        while ( amount == null ) {
            System.out.println ( "How much would you like to " + prompt + "? " );
            String line = input.nextLine ( );

            try {
                amount = Double.parseDouble ( line );
            } catch (NumberFormatException e) {
                System.out.println ( "Invalid amount please try again." );
            }

        }
        return amount;
    }

    public static long getAccountId ( Scanner input ) {
        Long accountId = null;
        while ( accountId == null ) {
            System.out.print ( "Please enter the accountId: " );
            String line = input.nextLine ( );

            try {
                long temp = Long.parseLong ( line );
                if ( haveAccountWithId ( temp ) ) {
                    accountId = temp;
                }
                else {
                    System.out.println ( "No account with that id can be found, please try again." );
                }
            } catch (NumberFormatException e) {
                System.out.println ( "Invalid accound id, please try again." );
            }
        }

        return accountId;
    }

    private static boolean haveAccountWithId ( long id ) {
        for ( BankAccount account : accounts ) {
            if ( account.getAccountId ( ) == id ) {
                return true;
            }
        }
        return false;
    }

    private static BankAccount getAccountWithId ( long id ) {
        for ( BankAccount account : accounts ) {
            if ( account.getAccountId ( ) == id ) {
                return account;
            }
        }
        return null;
    }


}
