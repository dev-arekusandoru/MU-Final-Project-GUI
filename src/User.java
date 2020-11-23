import javax.swing.*;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;

    public ArrayList<Checking> userCheckingAccounts = new ArrayList<>();
    public String accountIdsToStore = " ";

    public Checking currentAccount = null;

    public User(String u, String p) {
        username = u;
        password = p;
        ATM.users.add(this);
    }

    public User(String u, String p, String s) {
        username = u;
        password = p;
        accountIdsToStore = s;
        if (accountIdsToStore.length() >= 4) {
            populateUserAccounts();
        }
        ATM.users.add(this);
    }

    private void populateUserAccounts() {
        if (accountIdsToStore.charAt(0) == ' ') {
            accountIdsToStore = accountIdsToStore.substring(1);
        }
        if (accountIdsToStore.length() > 1) {
            String[] ids = accountIdsToStore.split("l");
            for (int k = 0; k < ids.length; k++) {
                for (int i = 0; i < ATM.checkingAccounts.size(); i++) {
                    if (ids[k].equals(String.valueOf(ATM.checkingAccounts.get(i).getAccountNumber()))) {
                        userCheckingAccounts.add(ATM.checkingAccounts.get(i));
                    }
                }
            }
        }
        if (userCheckingAccounts.size() >= 1) {
            currentAccount = userCheckingAccounts.get(0);
        } else {
            StdOut.println("No matching accounts were found... erasing log");
            accountIdsToStore = " ";
        }
    }



    public String getUsername() {
        return username;
    }

    /**public void changeUsername(String nU) {
        boolean canUse = ATM.checkCanUseUsername(nU);
        String input = "";
        while (!canUse) {
            StdOut.println("That username is already taken.\nPlease enter another username.");
            input = StdIn.readLine();
            canUse = ATM.checkCanUseUsername(input);
        }
        username = nU;
    }**/

    public String getPassword() {
        return password;
    }

    public void changePassword(String newPass) {
        password = newPass;
    }

    public void addAccount(Checking c) {
        userCheckingAccounts.add(c);
        accountIdsToStore += String.valueOf(c.getAccountNumber()) + "l";
        if (userCheckingAccounts.size() == 1) {
            currentAccount = userCheckingAccounts.get(0);
        }
    }

    public void removeAccount(int id) {
        Checking c;
        String[] ids = accountIdsToStore.split("l");

        for (int i = 0; i < userCheckingAccounts.size(); i++) {
            c = userCheckingAccounts.get(i);
            if ((c.getAccountNumber() == id)) {
                if (c.getAccountBalance() == 0) {
                    for (int j = 0; j < ATM.checkingAccounts.size(); j++) {
                        if (ATM.checkingAccounts.get(j).getAccountNumber() == id) {
                            ATM.checkingAccounts.remove(j);
                        }
                    }
                    userCheckingAccounts.remove(i);
                    ids[i] = "";
                    String newIds = String.join("l", ids);
                    accountIdsToStore = newIds;
                    return;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Transfer or withdraw all money before closing account.",
                            "Account Balance Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

}
