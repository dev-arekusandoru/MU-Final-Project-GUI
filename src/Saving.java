import java.text.SimpleDateFormat;
import java.util.Date;

public class Saving extends Checking {

    private int numOfTransactions = 0;
    private static final int maxNumOfTransaction = 6;

    private int lastMonthChecked;

    public Saving() {
        super();
        lastMonthChecked = Integer.parseInt(super.transactionHistory.substring(20, 22));
    }

    public void deposit(double amount) {
        resetNumOfTransactions();
        if (numOfTransactions < maxNumOfTransaction) {
            super.deposit(amount);
            numOfTransactions++;
        } else {
            StdOut.println("You have reached your transaction limit for the month.");
        }
    }

    public void transfer(Checking c, double amount) {
        resetNumOfTransactions();
        if (numOfTransactions < maxNumOfTransaction) {
            super.transfer(c, amount);
            numOfTransactions++;
        } else {
            StdOut.println("You have reached your transaction limit for the month.");
        }
    }


    public void resetNumOfTransactions() {
        int currentMonth = Integer.parseInt(new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(new Date()).substring(3, 5));

        if ((currentMonth - lastMonthChecked) >= 1) {
            lastMonthChecked = currentMonth;
            numOfTransactions = 0;
        }
    }
}
