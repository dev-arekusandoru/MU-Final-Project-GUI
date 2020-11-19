import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.awt.event.*;
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
        mainFrame.setPreferredSize(new Dimension(400, 450));

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
        //Course selection panel
        JPanel coursePanel = new JPanel();
        JLabel choose = new JLabel("Choose a course to view: ");
        JComboBox courseSelect = new JComboBox<String>(courses);

        coursePanel.add(choose);
        coursePanel.add(courseSelect);

        //
        //Assignment type selection
        JPanel assignmentType = new JPanel();

        JRadioButton hw = new JRadioButton("Homework");
        JRadioButton quiz = new JRadioButton("Quiz");
        JRadioButton test = new JRadioButton("Test/Presentation");

        ButtonGroup assignmentTypeButtons = new ButtonGroup();

        assignmentTypeButtons.add(hw);
        assignmentTypeButtons.add(quiz);
        assignmentTypeButtons.add(test);

        hw.setSelected(true);

        assignmentType.add(hw);
        assignmentType.add(quiz);
        assignmentType.add(test);

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
        mainPanel.add(coursePanel);
        mainPanel.add(assignmentType);
        mainPanel.add(viewHw);
        mainPanel.add(addGrade);

        //finish frame
        mainFrame.pack();
        mainFrame.setVisible(true);
//////////////////////////////////---Main User Window---//////////////////////////////////

    }


    public static void main(String[] args) {
        new test();

    }
}
