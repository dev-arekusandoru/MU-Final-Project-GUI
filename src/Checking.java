import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checking {

    private final int accountNumber;

    private double accountBalance = 0.0;

    protected String transactionHistory = "";

    // used when creating a brand new account
    public Checking() {
        int randNum = (int) (Math.random() * 9999) + 1;
        while (!checkAccountNumber(randNum)) {
            randNum = (int) (Math.random() * 9999) + 1;
        }
        accountNumber = randNum;
        transactionHistory += new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(new Date()) + " - Account created";
        ATM.checkingAccounts.add(this);
    }

    // used when populating the List
    public Checking(int accountNum, double amount, String history) {
        accountNumber = accountNum;
        accountBalance = amount;
        transactionHistory = history;
        ATM.checkingAccounts.add(this);
    }

    private boolean checkAccountNumber(int num) {
        for (int i = 0; i < ATM.checkingAccounts.size(); i++) {
            if (ATM.checkingAccounts.get(i).getAccountNumber() == num) {
                return false;
            }
        }
        return true;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;

        transactionHistory += ("@" + new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(new Date()) + " - deposited $" + amount);
    }

    public void withdraw(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;

            transactionHistory += ("@" + new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(new Date()) + " - withdrew $" + amount);
        } else {
            JOptionPane.showMessageDialog(null,
                    "You do not have enough money.",
                    "Not Enough Money",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void transfer(Checking c, double amount) {
        withdraw(amount);
        c.deposit(amount);
        c.transactionHistory += ("@" + new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(new Date()) + " - received $" + amount + " from " + getAccountNumber());
        transactionHistory += ("@" + new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(new Date()) + " - transferred $" + amount + " to " + c.getAccountNumber());
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }

}
