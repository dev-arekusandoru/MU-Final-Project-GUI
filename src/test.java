import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;


public class test implements ActionListener {

    boolean loggedIn = false;

    ////////---LOGIN FRAME VARS---////////
    JFrame loginFrame;
    JButton login;

    ////////---MAIN FRAME VARS---////////
    JFrame mainFrame;

    String username;
    String password;


    public test() {
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

        //sets the layout of the mainPanel
        mainLoginPanel.setLayout(new BoxLayout(mainLoginPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        Border emptyBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        mainLoginPanel.setBorder(emptyBorder);

        //
        //login welcome panel
        JPanel loginWelcomePanel = new JPanel();
        Border emptyWelcomeBorder = BorderFactory.createEmptyBorder(0, 0, 15, 0);
        loginWelcomePanel.setBorder(emptyWelcomeBorder);
        loginWelcomePanel.add(new JLabel("Please sign in to continue."));

        //
        //Assignment type selection
        JPanel loginInput = new JPanel();
        loginInput.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel loginULabel = new JLabel("Username: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        loginInput.add(loginULabel, c);

        JTextField loginUsername = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
        loginInput.add(loginUsername, c);

        JLabel loginPLabel = new JLabel("Password: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        loginInput.add(loginPLabel, c);


        JPasswordField loginPassword = new JPasswordField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        loginInput.add(loginPassword, c);

        //
        //Add grade panel
        JPanel loginButtonPanel = new JPanel();
        login = new JButton("Login");
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

        //exits the program when the window is closed
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gets the main content panel
        JPanel mainPanel = (JPanel)mainFrame.getContentPane();

        //sets the layout of the mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        mainPanel.setBorder(emptyBorder);

        //
        //user info panel
        JPanel userInfoPanel = new JPanel();
        Border userBorder = new TitledBorder("USERNAME");
        userInfoPanel.setBorder(userBorder);

        JButton testLogout = new JButton("Logout");
        testLogout.setBorderPainted(false);
        testLogout.setFocusPainted(false);
        testLogout.setContentAreaFilled(false);

        userInfoPanel.add(testLogout);

        //add panels to main panel
        mainPanel.add(userInfoPanel);

        mainFrame.pack();
        mainFrame.setVisible(false);

    }

    public void actionPerformed(ActionEvent e) {
        Object control = e.getSource();
        if (control == login) {
            loginFrame.setVisible(false);
            mainFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new test();
            }
        });

    }
}
