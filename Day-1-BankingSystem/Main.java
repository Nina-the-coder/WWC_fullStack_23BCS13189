public class Main {
    public static void main(String[] args) {
        BankAccount acct = new BankAccount("BA1001", "Alice", 500.00);
        SavingsAccount sav = new SavingsAccount("SA2001", "Bob", 1000.00, 2.5);

        System.out.println("Initial account details:");
        acct.printDetails();
        sav.printDetails();

        acct.deposit(200);
        acct.withdraw(100);
        sav.deposit(300);
        System.out.println();

        // ------- Should be denied due to insufficient funds -------
        System.out.println("Attempting to withdraw $2000 from savings account:");
        sav.withdraw(2000); 
        
        // ------- This is a Valid withdrawal -------
        sav.withdraw(50); 
        
        // ------- Applying the interest -------
        sav.applyInterest();
        
        System.out.println();
        System.out.println("Final account details:");
        acct.printDetails();
        sav.printDetails();
    }
}
