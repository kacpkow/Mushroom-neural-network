/**
 * Created by kacper on 2017-04-06.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;
import java.io.IOException;

import javax.swing.*;

/**
 * Gui for client application
 * @author Kacper Kowalik
 * @version 1.0
 */
public class Gui extends JFrame implements ActionListener{

    /**
    *File chooser for file with mushroom database or with mushrooms to testing out
     */
    private JFileChooser fileChooser = new JFileChooser();

    /**
     * Reference to file selected by the user
     */
    private File file;

    /**
     * Button which is using to open file with database
     */
    private JButton openFileButton = new JButton("Open File");

    /**
     * Button which is using to execute one of the operation described by radio buttons in option panel
     */
    private JButton executeButton = new JButton("Execute chosen task");

    /**
     * Learn rate label
     */
    private JLabel learnRateLabel = new JLabel("learn rate(epsilon): ");

    /**
     * Learn rate edit text which is using for changing learn rate value in server
     */
    private JTextField learnRate = new JTextField("0.2", 4);

    /**
     * Momentum label
     */
    private JLabel momentumLabel = new JLabel("momentum(alpha): ");

    /**
     * Mean squared error edit text which is using for changing mse final value in application
     */
    private JTextField mse = new JTextField("0.0001", 9);

    /**
     * Mean squared error label
     */
    private JLabel mseLabel = new JLabel("Mean squared error(MSE): ");

    /**
     * Momentum edit text which is using for changing momentum value in server
     */
    private JTextField momentum = new JTextField("0.7", 4);

    /**
     * JPanel which stores learn rate and momentum text fields and labels
     */
    private JPanel epsilonAlphaContainer= new JPanel();

    /**
     * JPanel which stores learn rate, momentum, mean squared error text fields and labels all together
     */
    private JPanel epsilonAlphaMseContainer= new JPanel();

    /**
     * JPanel which stores mean squared error text field and label
     */
    private JPanel meanSquaredErrorContainer= new JPanel();

    /**
     * This JLabel is the place where result of the neural network training is presented to the user
     */
    private JLabel trainingLabel = new JLabel("");

    /**
     * This JLabel is the place where result of the operation is presented to the user
     */
    private JLabel stateLabel = new JLabel("");

    /**
     * This panel stores panel with training result and classification result
     */
    private JPanel resultGroupBox = new JPanel();

    /**
     * Panel which stores radio buttons and label above
     */
    private JPanel operationOptionBox = new JPanel();
    /**
     * Panel which stores operationOptionBox panel
     */
    private JPanel operationOptionBoxGroup = new JPanel();
    /**
     * Panel which stores openFileButton and executeChosenTaskButton
     */
    private JPanel mainArea = new JPanel();
    /**
     * Panel which stores stateLabel and resultLabel
     */
    private JPanel resultTablePanel = new JPanel();
    /**
     * Label which refers to JRadioButtons
     */
    private JLabel operationText = new JLabel("Choose operation type");
    /**
     * RadioButton which allows to learn Neural Network with given learn rate and momentum values
     */
    private JRadioButton learnButton = new JRadioButton("Teach neural network");
    /**
     * RadioButton which allows to test Neural Network with single trial
     */
    private JRadioButton testSingleButton = new JRadioButton("Test neural network once");

    /**
     * RadioButton which allows to test Neural Network with multiple trials saved in .csv file
     */
    private JRadioButton testMultipleButton = new JRadioButton("Test neural network many times");
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
     * submenu Reset
     */
    private JMenuItem menuReset = new JMenuItem("Reset");
    /**
     * submenu Exit
     */
    private JMenuItem menuExit = new JMenuItem("Exit");
    /**
     * Panel to show server connection status
     */
    private JPanel serverStatusPanel;

    /**
     * Label to show server connection status
     */
    private JLabel serverStatusLabel;

    Gui() throws IOException {
        super("Mushroom neural network client v1.0");
        this.setJMenuBar(menuBar);
        this.setSize(600, 400);
        this.setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(learnButton);
        buttonGroup.add(testSingleButton);
        buttonGroup.add(testMultipleButton);
        learnButton.setSelected(true);

        operationOptionBox.setLayout(new BoxLayout(operationOptionBox, BoxLayout.Y_AXIS));
        operationOptionBox.add(operationText);
        operationOptionBox.add(learnButton);
        operationOptionBox.add(testSingleButton);
        operationOptionBox.add(testMultipleButton);
        operationOptionBoxGroup.add(operationOptionBox);
        operationOptionBoxGroup.setLayout(new GridBagLayout());
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
        programMenu.add(menuReset);
        programMenu.add(menuInfo);
        programMenu.add(menuExit);

        menuInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"OK"};

                String textInfo = "<html><body><div align='center'><br>This program is client of Mushroom server program.</br>"
                        + "<br>To learn or to test neural network check the appropriate radio button</br>"
                        + "<br>and click \"Execute chosen task\" button</br></div></body></html>";

                JLabel messageLabel = new JLabel(textInfo);

                JOptionPane.showOptionDialog(null,
                        messageLabel,
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

        resultTablePanel = new JPanel( new GridBagLayout());
        resultTablePanel.setLayout(new BoxLayout(resultTablePanel, BoxLayout.Y_AXIS));
        epsilonAlphaContainer = new JPanel(new GridBagLayout());
        meanSquaredErrorContainer = new JPanel(new GridBagLayout());
        epsilonAlphaMseContainer = new JPanel(new GridBagLayout());
        epsilonAlphaMseContainer.setLayout(new BoxLayout(epsilonAlphaMseContainer, BoxLayout.Y_AXIS));
        epsilonAlphaMseContainer.add(epsilonAlphaContainer);
        epsilonAlphaMseContainer.add(meanSquaredErrorContainer);

        epsilonAlphaContainer.add(learnRateLabel);
        epsilonAlphaContainer.add(learnRate);
        epsilonAlphaContainer.add(momentumLabel);
        epsilonAlphaContainer.add(momentum);

        meanSquaredErrorContainer.add(mseLabel);
        meanSquaredErrorContainer.add(mse);

        resultTablePanel.add(trainingLabel);
        resultTablePanel.add(stateLabel);

        serverStatusPanel = new JPanel(new GridLayout());
        serverStatusLabel = new JLabel("Server: connected");
        serverStatusPanel.add(serverStatusLabel);

        resultGroupBox.add(resultTablePanel);

        JSplitPane rightBottomPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, epsilonAlphaMseContainer, resultGroupBox);
        JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainArea, rightBottomPane);

        JSplitPane contentPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, operationOptionBoxGroup, rightPane);
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

    /**
     * Method which is used by FileReader in ClientController
     * @return path to the selected file
     */
    public String getFilePath(){
        return file.getPath().toString();
    }


    /**
     * Method to get selected operation type
     * @return 0 if learn mode selected, 1 if single test selected, 2 if multiple tests selected
     */
    public Integer getOperationType(){
        if (learnButton.isSelected()){
            return 0;
        }
        else if(testSingleButton.isSelected()){
            return 1;
        }
        return 2;
    }

    /**
     * Method which returns current learnRate value typed by the user
     * @return learnRate
     */
    public String getLearnRate(){
        return learnRate.getText().toString();
    }

    /**
     * Method which returns current momentum value typed by the user
     * @return momentum
     */
    public String getMomentum(){
        return momentum.getText().toString();
    }

    /**
     * Method which returns current mean squared error value typed by the user
     * @return momentum
     */
    public String getMse(){return mse.getText().toString();}

    /**
     * If the executeChosenTask is clicked execute a method
     * in the Controller named actionPerformed
     * @param listenForExecuteButton
     */
    void addExecuteListener(ActionListener listenForExecuteButton){
        executeButton.addActionListener(listenForExecuteButton);
    }

    /**
     * Adds the listener for Reset button
     * @param listenForResetButton
     */
    void addResetParametersListener(ActionListener listenForResetButton){
        menuReset.addActionListener(listenForResetButton);
    }

    /**
     * @return true if user wants to reset neural network parameters, otherwise returns false
     */
    public boolean showWarningResetPopup(){
        int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset neural network parameters?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (ans == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
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
     * executed after clicking execute chosen task button
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Changes server connection status label
     * @param msg
     */
    public void changeStatusLabel(String msg){
        serverStatusLabel.setText("Server: " + msg);
    }

    /**
     * Displays the result of operation that server returned to the client
     * @param message
     */
    public void displayResultValue(String message){
        stateLabel.setText("<html><body><div align='center'><br>"+message+"</br></div></body></html>");
        JOptionPane.showMessageDialog(this, message);
    }

    public void displayTrainingResultValue(String message){
        trainingLabel.setText(message);
    }

    public void resetResultValue(){
        stateLabel.setText("");
    }

    /**
     * Displays the result of resetting neural network parameters that server returned to the client
     * @param message
     */
    public void displayResetParametersResult(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}