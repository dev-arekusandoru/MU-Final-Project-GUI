import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.FontUIResource;
/** 
 * 
 * @author: Alexandru Muresan
 *
**/

public class test implements ActionListener {

    boolean loggedIn = false;

    static DefaultListModel<String> testList = new DefaultListModel<>();

    ////////---LOGIN FRAME VARS---////////
    JFrame loginFrame;
    JButton login;

    JTextField loginUsername;
    JPasswordField loginPassword;

    ////////---MAIN FRAME VARS---////////
    JFrame mainFrame;

    JLabel userInfoLabel;

    String username;
    String password;

    JButton testLogout;

    JList accountViewer;

    public test() {
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
        loginWelcomePanel.add(new JLabel("Please sign in to continue."));

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
        login.setPreferredSize(new Dimension(55, 30));
        login.setOpaque(true);
        login.setBackground(new Color(0, 80, 157));
        login.setForeground(Color.white);
        login.setBorder(new EmptyBorder(0,0,0,0));
        login.addActionListener(this);

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
        testLogout = new JButton("Logout");
        testLogout.setIcon(new ImageIcon(new ImageIcon("icons/logout_icon.png").getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        testLogout.setHorizontalTextPosition(AbstractButton.LEADING);
        testLogout.setPreferredSize(new Dimension(82, 40));
        testLogout.setBorder(new EmptyBorder(0, 0, 0, 0));
        testLogout.addActionListener(this);

        userInfoPanel.add(userInfoLabel);
        userInfoPanel.add(Box.createHorizontalGlue());
        userInfoPanel.add(testLogout);

        //
        //user account list
        JPanel userAccounts = new JPanel();
        userAccounts.setBackground(Color.white);
        Border accountBorder = new TitledBorder("Accounts");

        accountViewer = new JList<String>((ListModel<String>) testList);
        accountViewer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        accountViewer.setPreferredSize(new Dimension(325, 200));

        JScrollPane accountScroll = new JScrollPane(accountViewer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        accountScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        accountScroll.setBorder(accountBorder);
        userAccounts.add(accountScroll);

        //add panels to main panel
        mainPanel.add(userInfoPanel);
        mainPanel.add(userAccounts);

        mainFrame.pack();
        mainFrame.setVisible(false);


    }

    public void actionPerformed(ActionEvent e) {

        Object control = e.getSource();
        if (control == login) {
            loginFrame.setVisible(false);
            username = loginUsername.getText();
            userInfoLabel.setText("Welcome, " + username);

            mainFrame.setVisible(true);
        }

        if (control == testLogout) {
            System.out.println("success");
        }
    }

    // font change method from Kumar Mitra
    // via: https://stackoverflow.com/questions/12730230/set-the-same-font-for-all-component-java
    public static void setUIFont(FontUIResource f) {
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


    public static void main(String[] args) {
        testList.add(0, "hello");
        testList.addElement("goodbye");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test();
            }
        });

    }
}
