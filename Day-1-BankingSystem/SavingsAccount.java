public class SavingsAccount extends BankAccount {
    private double interestRate; // in percent

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > getBalance()) {
            System.out.println("Withdrawal denied: insufficient funds in savings account.");
            return false;
        }
        return super.withdraw(amount);
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100.0;
        if (interest > 0) {
            deposit(interest);
            System.out.printf("Applied interest %.2f (rate: %.2f%%) to account %s.\n", interest, interestRate, getAccountNumber());
        }
    }
}
