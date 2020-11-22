import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.FontUIResource;

/**
 * @author: Alexandru Muresan
 **/

public class ATM implements ActionListener {

    public static ArrayList<Checking> checkingAccounts = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();

    public static DefaultListModel<String> accountsModel = new DefaultListModel<>();
    public static DefaultListModel<String> historyModel = new DefaultListModel<>();

    public static User currentUser = null;
    public static Checking currentAccount = null;

    ////////---LOGIN FRAME VARS---////////
    JFrame loginFrame;
    JButton login;
    JButton newUser;

    JTextField loginUsername;
    JPasswordField loginPassword;

    ////////---MAIN FRAME VARS---////////
    JFrame mainFrame;

    JLabel userInfoLabel;

    public static String username;
    public static String password;

    JButton logout;

    JList accountViewer;

    JButton view;
    JButton create;
    JButton delete;

    ////////---MAIN FRAME VARS---////////
    JFrame accountFrame;

    JList historyViewer;

    JButton deposit;
    JButton withdraw;
    JButton transfer;

    public ATM() {
        //set font to arial
        setUIFont(new FontUIResource(new Font("Arial", 0, 15)));
////////////////////////////////////---Login Window---////////////////////////////////////
        loginFrame = new JFrame();
        loginFrame.setTitle("Login");

        //sets the location of the initial window
        loginFrame.setLocation(400, 150);
        //mainFrame.setPreferredSize(new Dimension(250, 175));
        loginFrame.setResizable(false);

        //exits the program when the window is closed
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gets the main content panel
        JPanel mainLoginPanel = (JPanel) loginFrame.getContentPane();
        mainLoginPanel.setBackground(Color.white);

        //sets the layout of the mainPanel
        mainLoginPanel.setLayout(new BoxLayout(mainLoginPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        Border emptyBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        mainLoginPanel.setBorder(emptyBorder);

        //
        //login welcome panel
        JPanel loginWelcomePanel = new JPanel();
        loginWelcomePanel.setBackground(Color.white);
        Border emptyWelcomeBorder = BorderFactory.createEmptyBorder(0, 0, 15, 0);
        loginWelcomePanel.setBorder(emptyWelcomeBorder);
        loginWelcomePanel.add(new JLabel("Please sign in or create a new account to continue."));

        //
        //Assignment type selection
        JPanel loginInput = new JPanel();
        loginInput.setBackground(Color.white);
        loginInput.setLayout(new GridLayout(2, 1));

        loginUsername = new JTextField(15);
        loginUsername.setBorder(new TitledBorder("Username"));
        loginPassword = new JPasswordField(15);
        loginPassword.setBorder(new TitledBorder("Password"));

        loginInput.add(loginUsername);
        loginInput.add(loginPassword);
        //
        //login button panel
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        loginButtonPanel.setBackground(Color.white);
        login = new JButton("Login");
        styleButton(login, 55, 30);
        login.addActionListener(this);

        newUser = new JButton("New User");
        styleButton(newUser, 80, 30);
        newUser.addActionListener(this);

        loginButtonPanel.add(newUser);
        loginButtonPanel.add(login);

        //add all the panels in order
        mainLoginPanel.add(loginWelcomePanel);
        mainLoginPanel.add(loginInput);
        mainLoginPanel.add(loginButtonPanel);

        //finish frame
        loginFrame.pack();
        loginFrame.setVisible(true);
//////////////////////////////////---Main User Window---//////////////////////////////////
        mainFrame = new JFrame();
        mainFrame.setTitle("Bank Application");

        //sets the location of the initial window
        mainFrame.setLocation(400, 150);
        mainFrame.setPreferredSize(new Dimension(400, 450));
        mainFrame.setResizable(false);

        //exits the program when the window is closed
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gets the main content panel
        JPanel mainPanel = (JPanel) mainFrame.getContentPane();
        mainPanel.setBackground(Color.white);
        //sets the layout of the mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        mainPanel.setBorder(emptyBorder);

        //
        //user info panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setBackground(Color.white);
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.X_AXIS));
        userInfoPanel.setBorder(new EmptyBorder(0, 0, 15, 0));


        userInfoLabel = new JLabel("Welcome");

        // icon scaling from user 'tirz'
        // via https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/16345968
        logout = new JButton("Logout");
        fixMouseOver(logout);
        logout.setIcon(new ImageIcon(new ImageIcon("icons/logout_icon.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        logout.setHorizontalTextPosition(AbstractButton.LEADING);
        logout.setPreferredSize(new Dimension(82, 40));
        logout.setBorder(new EmptyBorder(0, 0, 0, 0));

        logout.addActionListener(this);

        userInfoPanel.add(userInfoLabel);
        userInfoPanel.add(Box.createHorizontalGlue());
        userInfoPanel.add(logout);

        //
        //user account list
        JPanel userAccountsPanel = new JPanel();
        userAccountsPanel.setBackground(Color.white);
        Border accountBorder = new TitledBorder("Accounts");

        accountViewer = new JList<String>((ListModel<String>) accountsModel);

        accountViewer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        accountViewer.setPreferredSize(new Dimension(325, 200));

        JScrollPane accountScroll = new JScrollPane(accountViewer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (accountsModel.size() <= 8) {
            accountScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        }

        accountScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        accountScroll.setBorder(accountBorder);
        userAccountsPanel.add(accountScroll);


        //
        // button panel for main frame
        JPanel mainButtons = new JPanel();
        mainButtons.setBackground(Color.white);
        mainButtons.setLayout(new GridBagLayout());

        view = new JButton("View Account");
        styleButton(view, 55, 28);

        create = new JButton("Create");
        styleButton(create, 55, 28);

        delete = new JButton("Delete");
        styleButton(delete, 55, 28);

        GridBagConstraints c = new GridBagConstraints();

        view.addActionListener(this);
        create.addActionListener(this);
        delete.addActionListener(this);

        c.insets = new Insets(5, 8, 0, 8);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        mainButtons.add(view, c);

        c.gridy = 1;
        c.gridwidth = 1;
        mainButtons.add(create, c);

        c.gridx = 1;
        mainButtons.add(delete, c);


        //add panels to main panel
        mainPanel.add(userInfoPanel);
        mainPanel.add(userAccountsPanel);
        mainPanel.add(mainButtons);

        mainFrame.pack();
        mainFrame.setVisible(false);

//////////////////////////////////---Account View Window---//////////////////////////////////
        accountFrame = new JFrame();
        accountFrame.setTitle("Bank Application");

        //sets the location of the initial window
        accountFrame.setLocation(400, 150);
        accountFrame.setPreferredSize(new Dimension(400, 450));
        accountFrame.setResizable(false);

        //exits the program when the window is closed
        accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gets the main content panel
        JPanel mainAccountPanel = (JPanel) accountFrame.getContentPane();
        mainAccountPanel.setBackground(Color.white);
        //sets the layout of the mainPanel
        mainAccountPanel.setLayout(new BoxLayout(mainAccountPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        mainAccountPanel.setBorder(emptyBorder);

        //
        //Account information panel
        JPanel accountInfoPanel = new JPanel();
        accountInfoPanel.setLayout(new BoxLayout(accountInfoPanel, BoxLayout.X_AXIS));
        accountInfoPanel.setBackground(Color.white);
        JLabel accountInfoLabel = new JLabel("Account Id: ");

        accountInfoPanel.add(accountInfoLabel);
        accountInfoPanel.add(Box.createHorizontalGlue());

        //transaction history default list model
        JPanel transactionHistoryPanel = new JPanel();
        transactionHistoryPanel.setBackground(Color.white);
        Border historyBorder = new TitledBorder("Transaction History");

        historyViewer = new JList<String>((ListModel<String>) historyModel);

        historyViewer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        historyViewer.setPreferredSize(new Dimension(325, 200));

        JScrollPane historyScroll = new JScrollPane(historyViewer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        if (historyModel.size() <= 8) {
            historyScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        }
        historyScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        historyScroll.setBorder(historyBorder);

        transactionHistoryPanel.add(historyScroll);

        //
        // account interaction buttons
        JPanel accountInteractionPanel = new JPanel();
        accountInteractionPanel.setBackground(Color.white);
        deposit = new JButton("Deposit");
        styleButton(deposit, 70, 28);

        withdraw = new JButton("Withdraw");
        styleButton(withdraw, 70, 28);

        transfer = new JButton("Transfer");
        styleButton(transfer, 70, 28);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        transfer.addActionListener(this);

        accountInteractionPanel.add(deposit);
        accountInteractionPanel.add(withdraw);
        accountInteractionPanel.add(transfer);


        //add panels to main content panel
        mainAccountPanel.add(accountInfoPanel);
        mainAccountPanel.add(transactionHistoryPanel);
        mainAccountPanel.add(accountInteractionPanel);

        accountFrame.pack();
        accountFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {

        Object control = e.getSource();
        if (control == login) {
            boolean couldLog = loginCheck();
            if(couldLog) {
                loginFrame.setVisible(false);
                userInfoLabel.setText("Welcome, " + username);

                mainFrame.setVisible(true);
            } else {
                loginUsername.setText("");
                loginPassword.setText("");
                JOptionPane.showMessageDialog(null,
                        "The username and/or password you entered were incorrect.",
                        "User Not Found",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        if (control == logout) {
            mainFrame.setVisible(false);
            currentUser = null;
            loginFrame.setVisible(true);
        }

        //update data after every action, just for good measure
        writeToDataFile();
    }

    public boolean loginCheck() {
        username = loginUsername.getText();
        char[] passChars = loginPassword.getPassword();
        String[] passStrings = new String[passChars.length];
        for(int i = 0; i < passStrings.length; i++) {
            passStrings[i] = String.valueOf(passChars[i]);
        }
        password = String.join("", passStrings);

        StdOut.println(username + " " + password);

        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                if(password.equals(users.get(i).getPassword())) {
                    currentUser = users.get(i);
                    return true;
                }
            }

        }
        return false;
    }

    public static void writeToDataFile() {
        Out out = new Out("accountData.txt");
        for (int i = 0; i < checkingAccounts.size(); i++) {
            Checking c = checkingAccounts.get(i);
            String toWrite = c.getAccountNumber() + "," + c.getAccountBalance() + "," + c.getTransactionHistory();
            out.println(toWrite);
        }
        Out userOut = new Out("userData.txt");
        for (int i = 0; i < users.size(); i++) {
            String toWriteUser = users.get(i).getUsername() + "," + users.get(i).getPassword() + "," + users.get(i).accountIdsToStore;
            userOut.println(toWriteUser);
        }
    }



    public static void setUIFont(FontUIResource f) {
        // font change method from Kumar Mitra
        // via: https://stackoverflow.com/questions/12730230/set-the-same-font-for-all-component-java
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    public void fixMouseOver(JButton j) {
        j.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                j.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                j.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public void styleButton(JButton j, int w, int h) {
        fixMouseOver(j);
        j.setPreferredSize(new Dimension(w, h));
        j.setOpaque(true);
        j.setBackground(new Color(0, 80, 157));
        j.setForeground(Color.white);
        j.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public static void main(String[] args) {

        In accountsIn = new In("accountData.txt");
        String[] storedAccountData = accountsIn.readAllLines();
        for (int i = 0; i < storedAccountData.length; i++) {
            String[] tempNewAccount = storedAccountData[i].split(",");
            // store to accounts List
            new Checking(Integer.parseInt(tempNewAccount[0]), Double.parseDouble(tempNewAccount[1]), tempNewAccount[2]);
        }

        // populate userData and users ArrayLists from userData.txt
        In userIn = new In("userData.txt");
        String[] storedUserData = userIn.readAllLines();
        for (int i = 0; i < storedUserData.length; i++) {
            String[] tempNewUser = storedUserData[i].split(",");
            // store to userData
            new User(tempNewUser[0], tempNewUser[1], tempNewUser[2]);
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATM();
            }
        });

    }
}
