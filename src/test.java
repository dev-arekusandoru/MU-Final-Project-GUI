import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class test {

    String[] courses = {"Calculus", "Debate", "Gov & Econ", "Spanish III"};
    static DefaultListModel<String> Calc_hw_grades = new DefaultListModel<>();

    public test() {
////////////////////////////////////---Login Window---////////////////////////////////////
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Grade Book");

        //sets the location of the initial window
        mainFrame.setLocation(400, 150);
        mainFrame.setSize(new Dimension(400, 450));
        mainFrame.setResizable(false);

        //exits the program when the window is closed
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //gets the main content panel
        JPanel mainPanel = (JPanel)mainFrame.getContentPane();

        //sets the layout of the mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        Border emptyBorder = BorderFactory.createEmptyBorder(15,15,15,15);
        mainPanel.setBorder(emptyBorder);

        //
        //login welcome panel
        JPanel loginWelcomePanel = new JPanel();
        loginWelcomePanel.add(new JLabel("Please sign in to continue."));

        //
        //Assignment type selection
        JPanel loginInput = new JPanel();

        JTextField loginUsername = new JTextField();
        JTextField loginPassword = new JTextField();

        loginInput.add(loginUsername);
        loginInput.add(loginPassword);

        //
        //Homework viewer
        JPanel viewHw = new JPanel();

        JList gradeViewer = new JList<String>(Calc_hw_grades);
        gradeViewer.setPreferredSize(new Dimension(300, 200));
        gradeViewer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane gradeScroll = new JScrollPane(gradeViewer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        viewHw.add(gradeScroll);

        //
        //Add grade panel
        JPanel addGrade = new JPanel();
        JButton add = new JButton("Add Grade");
        JButton edit = new JButton("Edit Grade");
        JButton delete = new JButton("Delete Grade");

        addGrade.add(add);
        addGrade.add(edit);
        addGrade.add(delete);

        //add all the panels in order
        mainPanel.add(loginWelcomePanel);
        mainPanel.add(loginInput);
        mainPanel.add(viewHw);
        mainPanel.add(addGrade);

        //finish frame
        mainFrame.pack();
        mainFrame.setVisible(true);
//////////////////////////////////---Main User Window---//////////////////////////////////

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
