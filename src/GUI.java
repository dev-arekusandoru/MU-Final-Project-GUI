import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUI implements ActionListener {

    public static void main(String[] args) {
        new GUI();
    }

    String[] courses = {"Calculus", "Debate", "Gov & Econ", "Spanish III"};
    static DefaultListModel<String> Calc_hw_grades = new DefaultListModel<>();


    ///////////////////////////////////////////----MAIN FRAME VARS-----///////////////////////////////////////////
    //course dropdown
    JComboBox<String> courseSelect;

    //assignment type
    JRadioButton hw;
    JRadioButton quiz;
    JRadioButton test;

    //assignment type button group
    ButtonGroup assignmentTypeButtons;

    //Homework viewer
    static JList<String> gradeViewer;

    //main menu functions
    JButton add;
    JButton delete;
    JButton edit;

    ///////////////////////////////////////////----ADD FRAME VARS-----///////////////////////////////////////////
    //frame to add a grade
    JFrame addFrame;

    //add course dropdown
    JComboBox<String> addCourseSelect;

    //add assignment name
    JTextField assignmentName;

    //add assignment type
    JRadioButton addhw;
    JRadioButton addquiz;
    JRadioButton addtest;

    //add assignment score
    JSpinner rec;
    JSpinner total;
    int pRec;
    int pTotal;

    //finish adding grade or add another
    JButton finish;
    JButton addAnother;
    //self explanatory
    ButtonGroup addAssignmentTypeButtons;

    ///////////////////////////////////////////----EDIT FRAME VARS-----///////////////////////////////////////////
    //frame to add a grade
    JFrame editFrame;

    //add course dropdown
    JComboBox<String> editCourseSelect;

    //add assignment name
    JTextField editAssignmentName;

    //add assignment type
    JRadioButton edithw;
    JRadioButton editquiz;
    JRadioButton edittest;

    //add assignment score
    JSpinner editrec;
    JSpinner edittotal;
    int editpRec;
    int editpTotal;

    int changeeditpRec;
    int changeeditpTotal;

    //finish adding grade or add another
    JButton editfinish;
    JButton cancel;
    //self explanatory
    ButtonGroup editAssignmentTypeButtons;

    //store vars
    static String editAssignName;
    static String editAssignType;
    static String editAssignClass;
    static Double editAssignPercent;

    //counter
    static int count = -1;

    public GUI() {

/////////////////////////////////////----VIEW GRADE PANEL----////////////////////////////////////////
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
        courseSelect = new JComboBox<String>(courses);
        courseSelect.addActionListener(this);

        coursePanel.add(choose);
        coursePanel.add(courseSelect);

        //
        //Assignment type selection
        JPanel assignmentType = new JPanel();

        hw = new JRadioButton("Homework");
        hw.addActionListener(this);
        quiz = new JRadioButton("Quiz");
        quiz.addActionListener(this);
        test = new JRadioButton("Test/Presentation");
        test.addActionListener(this);

        assignmentTypeButtons = new ButtonGroup();

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

        gradeViewer = new JList<String>(Calc_hw_grades);
        gradeViewer.setPreferredSize(new Dimension(300, 200));
        gradeViewer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane gradeScroll = new JScrollPane(gradeViewer,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        viewHw.add(gradeScroll);

        //
        //Add grade panel
        JPanel addGrade = new JPanel();
        add = new JButton("Add Grade");
        add.addActionListener(this);
        edit = new JButton("Edit Grade");
        edit.addActionListener(this);
        delete = new JButton("Delete Grade");
        delete.addActionListener(this);

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

/////////////////////////////////////----ADD GRADE PANEL----////////////////////////////////////////
        addFrame = new JFrame();
        addFrame.setTitle("Add Assignment");

        //sets the location of the initial window
        addFrame.setLocation(300, 200);

        //exits the program when the window is closed
        addFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //gets the main content panel
        JPanel addPanel = (JPanel)addFrame.getContentPane();

        //sets the layout of the mainPanel
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        Border addEmptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        addPanel.setBorder(addEmptyBorder);

        //
        //Course selection panel
        JPanel addCoursePanel = new JPanel();
        JLabel addChoose = new JLabel("Choose course: ");
        addCourseSelect = new JComboBox<String>(courses);

        addCoursePanel.add(addChoose);
        addCoursePanel.add(addCourseSelect);

        //
        //Assignment name
        JPanel addAssignmentName = new JPanel();
        JLabel addName = new JLabel("Assignment Name: ");
        assignmentName = new JTextField(20);

        addAssignmentName.add(addName);
        addAssignmentName.add(assignmentName);

        //
        //Assignment type selection
        JPanel addAssignmentType = new JPanel();

        addhw = new JRadioButton("Homework");
        addquiz = new JRadioButton("Quiz");
        addtest = new JRadioButton("Test/Presentation");

        addAssignmentTypeButtons = new ButtonGroup();

        addAssignmentTypeButtons.add(addhw);
        addAssignmentTypeButtons.add(addquiz);
        addAssignmentTypeButtons.add(addtest);

        addhw.setSelected(true);

        addAssignmentType.add(addhw);
        addAssignmentType.add(addquiz);
        addAssignmentType.add(addtest);

        //
        //Grade points received
        JPanel gPoints = new JPanel();
        JLabel gradePoints = new JLabel("Points recieved: ");
        JLabel gradePoints2 = new JLabel(" of ");
        rec = new JSpinner(new SpinnerNumberModel(pRec, 0, 200, 1));
        total = new JSpinner(new SpinnerNumberModel(pRec, 0, 200, 1));

        gPoints.add(gradePoints);
        gPoints.add(rec);
        gPoints.add(gradePoints2);
        gPoints.add(total);

        //
        //Add grade
        JPanel finishGrade = new JPanel();
        finish = new JButton("Finish");
        finish.addActionListener(this);
        addAnother = new JButton("Add Another");
        addAnother.addActionListener(this);

        finishGrade.add(addAnother);
        finishGrade.add(finish);


        //add all the panels in order
        addPanel.add(addCoursePanel);
        addPanel.add(addAssignmentName);
        addPanel.add(addAssignmentType);
        addPanel.add(gPoints);
        addPanel.add(finishGrade);

        //finish frame
        addFrame.pack();
        addFrame.setVisible(false);


/////////////////////////////////////----EDIT GRADE PANEL----////////////////////////////////////////
        editFrame = new JFrame();
        editFrame.setTitle("Edit Assignment");

        //sets the location of the initial window
        editFrame.setLocation(300, 200);

        //exits the program when the window is closed
        editFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //gets the main content panel
        JPanel editPanel = (JPanel)editFrame.getContentPane();

        //sets the layout of the mainPanel
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));

        //creates and sets an empty border
        Border editEmptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        editPanel.setBorder(editEmptyBorder);

        //
        //Course selection panel
        JPanel editCoursePanel = new JPanel();
        JLabel editChoose = new JLabel("Choose course: ");
        editCourseSelect = new JComboBox<String>(courses);

        editCoursePanel.add(editChoose);
        editCoursePanel.add(editCourseSelect);

        //
        //Assignment name
        JPanel editAssignmentNamePanel = new JPanel();
        JLabel editName = new JLabel("Assignment Name: ");
        editAssignmentName = new JTextField(20);

        editAssignmentNamePanel.add(editName);
        editAssignmentNamePanel.add(editAssignmentName);

        //
        //Assignment type selection
        JPanel editAssignmentType = new JPanel();

        edithw = new JRadioButton("Homework");
        editquiz = new JRadioButton("Quiz");
        edittest = new JRadioButton("Test/Presentation");

        editAssignmentTypeButtons = new ButtonGroup();

        editAssignmentTypeButtons.add(edithw);
        editAssignmentTypeButtons.add(editquiz);
        editAssignmentTypeButtons.add(edittest);

        edithw.setSelected(true);

        editAssignmentType.add(edithw);
        editAssignmentType.add(editquiz);
        editAssignmentType.add(edittest);

        //
        //Grade points received
        JPanel editgPoints = new JPanel();
        JLabel editgradePoints = new JLabel("Points recieved: ");
        JLabel editgradePoints2 = new JLabel(" of ");
        editrec = new JSpinner(new SpinnerNumberModel(editpRec, 0, 200, 1));
        edittotal = new JSpinner(new SpinnerNumberModel(editpRec, 0, 200, 1));

        editgPoints.add(editgradePoints);
        editgPoints.add(editrec);
        editgPoints.add(editgradePoints2);
        editgPoints.add(edittotal);

        //
        //Add grade
        JPanel editFinishGrade = new JPanel();
        editfinish = new JButton("Finish");
        editfinish.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);

        editFinishGrade.add(editfinish);
        editFinishGrade.add(cancel);


        //add all the panels in order
        editPanel.add(editCoursePanel);
        editPanel.add(editAssignmentNamePanel);
        editPanel.add(editAssignmentType);
        editPanel.add(editgPoints);
        editPanel.add(editFinishGrade);

        //finish frame
        editFrame.pack();
        editFrame.setVisible(false);



    }




    public void actionPerformed(ActionEvent e) {

    }
}
