/**
 * Created by kacper on 2017-04-06.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Gui extends JFrame implements
        ActionListener{


    private JFileChooser fileChooser = new JFileChooser();

    private File file;

    private JButton openFileButton = new JButton("Open File");

    private JButton executeButton = new JButton("Execute chosen task");
    /**
     * Panel which stores radio buttons and label above
     */
    private JPanel operationOptionBox = new JPanel();
    /**
     * Panel which stores conversionOptionBox panel
     */
    private JPanel conversionOptionBoxGroup = new JPanel();
    /**
     * Panel which stores numberToConversion, resultNumber and convertButton
     */
    private JPanel mainArea = new JPanel();
    /**
     * Panel which stores JScrollBar with table
     */
    private JPanel resultTablePanel = new JPanel();
    /**
     * Label which refers to JRadioButtons
     */
    private JLabel operationText = new JLabel("Choose operation type");
    /**
     * RadioButton which allows to convert decimal number to binary
     */
    private JRadioButton learnButton = new JRadioButton("Learn neural network");
    /**
     * RadioButton which allows to convert binary number to decimal
     */
    private JRadioButton testButton = new JRadioButton("Test neural network");
    /**
     * menubar
     */
    private JMenuBar menuBar = new JMenuBar();
    /**
     * main menu of the program
     */
    private JMenu programMenu = new JMenu("Program");
    /**
     * submenu Info
     */
    private JMenuItem menuInfo = new JMenuItem("Info");
    /**
     * submenu Refresh
     */
    private JMenuItem menuRefresh = new JMenuItem("Refresh");
    /**
     * submenu Exit
     */
    private JMenuItem menuExit = new JMenuItem("Exit");
    /**
     * Panel to show status connection
     */
    private JPanel serverStatusPanel;

    /**
     * Label to show status connection
     */
    private JLabel serverStatusLabel;

    Gui() throws IOException {
        super("Mushroom neural network v1.0");
        this.setJMenuBar(menuBar);
        this.setSize(600, 400);

        ButtonGroup binDecGroup = new ButtonGroup();
        binDecGroup.add(learnButton);
        binDecGroup.add(testButton);
        learnButton.setSelected(true);

        operationOptionBox.setLayout(new BoxLayout(operationOptionBox, BoxLayout.Y_AXIS));
        operationOptionBox.add(operationText);
        operationOptionBox.add(learnButton);
        operationOptionBox.add(testButton);
        conversionOptionBoxGroup.add(operationOptionBox);
        conversionOptionBoxGroup.setLayout(new GridBagLayout());
        mainArea.add(openFileButton);
        mainArea.add(executeButton);


        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == openFileButton) {
                    int returnVal = fileChooser.showOpenDialog(Gui.this);

                    JTextArea log = new JTextArea();
                    String newline = "";
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        //This is where a real application would open the file.
                        log.append("Opening: " + file.getName() + "." + newline);
                        System.out.println("Selected file: " + file.getAbsolutePath());
                    } else {
                        log.append("Open command cancelled by user." + newline);
                    }
                }
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        menuBar.add(programMenu);
        programMenu.add(menuRefresh);
        programMenu.add(menuInfo);
        programMenu.add(menuExit);

        menuInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"OK"};

                String textInfo = "Program for conversion decimal numbers to binary numbers and vice versa.\n"
                        + "Into Left Textfield insert value which would be converted. \n"
                        + "Choose appropriate type of conversion you want to do and click Convert. \n";

                JOptionPane.showOptionDialog(null,
                        textInfo,
                        "Information",
                        JOptionPane.OK_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
        });

        menuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to close this window?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (ans == JOptionPane.YES_OPTION) {
                    // Closing window
                    dispose();
                }
            }
        });


        //ImageIcon iconExit = new ImageIcon(getClass().getResource("exit.png"));
        //ImageIcon iconRefresh = new ImageIcon(getClass().getResource("reload.png"));
       // ImageIcon iconConvert = new ImageIcon(getClass().getResource("convert.png"));

        //buttonConvert = new JButton(iconConvert);
        //buttonExit = new JButton(iconExit);
        //buttonRefresh = new JButton(iconRefresh);


        resultTablePanel = new JPanel( new GridLayout() );

        serverStatusPanel = new JPanel(new GridLayout());
        serverStatusLabel = new JLabel("Server: connected");
        serverStatusPanel.add(serverStatusLabel);


        JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainArea, resultTablePanel);
        JSplitPane contentPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, conversionOptionBoxGroup, rightPane);
        this.add(contentPane);
        this.add(serverStatusPanel, BorderLayout.PAGE_END);

        this.getContentPane().setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        // End of setting up the components --------
    }

    /**
     * method which is used to exit from the program throuh the "X" button
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        int ans = JOptionPane.showConfirmDialog(this, "Are you sure you want to close this window?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (ans == JOptionPane.YES_OPTION) {
            // Closing window
            dispose();
        }
    }

    public String getFilePath(){
        return file.getPath().toString();
    }

    /**
     * method which returns integer value from TextField numberToConversion
     * @return
     */
//    public int getDecNumber(){
//        return Integer.parseInt(numberToConversion.getText());
//    }
//    /**
//     * method which returns String from TextField numberToConversion
//     * @return
//     */
//    public String getBinNumber(){
//        return numberToConversion.getText();
//    }
//
//    /**
//     * set the value of resultNumber TextField after binary conversion
//     * @param solution
//     */
//    public void setBinCalcSolution(String solution){
//        resultNumber.setText(solution);
//    }
//    /**
//     * set the value of resultNumber TextField after decimal conversion
//     * @param solution
//     */
//    public void setDecCalcSolution(int solution){
//        resultNumber.setText(String.valueOf(solution));
//    }
    /**
     * return value true if binary conversion is selected, otherwise returns false
     * @return
     */
    public boolean getOperationType(){
        if (learnButton.isSelected()){
            return true;
        }
        else
            return false;
    }
    /**
     * sets the numberToConversion TextField when decimal number was passed to the program from command line
     * @param number
     */
//    void setDecNumberToConversion(int number){
//        numberToConversion.setText(String.valueOf(number));
//    }
//    /**
//     * sets the numberToConversion TextField when binary number was passed to the program from command line
//     * @param number
//     */
//    void setBinNumberToConversion(String number){
//        numberToConversion.setText(number);
//    }
    /**
     * sets the learnButton when binary 'b' option was passed to the program from command line
     */
    void setBinaryConversion(){
        learnButton.setSelected(true);
    }
    /**
     * sets the learnButton when decimal 'd' option was passed to the program from command line
     */
    void setDecimalConversion(){
        testButton.setSelected(true);
    }
    /**
     * // If the convertButton or buttonConvert is clicked execute a method
     * in the Controller named actionPerformed
     * @param listenForExecuteButton
     */
    void addExecuteListener(ActionListener listenForExecuteButton){
        executeButton.addActionListener(listenForExecuteButton);
    }
    /**
     * Open a popup that contains the error message passed
     * @param errorMessage
     */
    void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    /**
     * Open a popup that contains the error message passed
     * @param message
     */
    public void printExceptionDetails(String message) {
        System.out.println(message);
    }
    /**
     * executed after clicking buttonConvert or convertButton
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void changeStatusLabel(){
        serverStatusLabel.setText("Server: disconnected");
    }

}