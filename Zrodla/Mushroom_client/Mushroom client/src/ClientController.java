import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

import static sun.misc.Version.println;

/**
 * Controller for client
 * @author Kacper Kowalik
 * @version 1.0
 */
public class ClientController{
    /**
     * Adress from server
     */
    private final String HOST = "localhost";

    /**
     * Port from server
     */
    private final int PORT = 8888;

    private String[] answer = new String[2];

    /**
     * Reference to the gui object
     */
    private Gui view;

    /**
     * Current ServerConnection class object
     */
    private ServerConnection server;

    /**
     * Boolean variable representing if the client is connected with the server
     */
    private boolean connectionState = true;

    /**
     * Constructor of controller
     * @param theView
     * @throws IOException
     */
    public ClientController(Gui theView) throws IOException {
        this.view = theView;
        try{
            this.server = new ServerConnection(HOST, PORT);
        }catch(IOException e){
            connectionState = false;
            view.changeStatusLabel("disconnected");
            view.displayErrorMessage("Server is not currently available. Any operations on neural network are unavailable");
        }
        this.view.addExecuteListener(new ExecuteListener());
        this.view.addResetParametersListener(new ResetParametersListener());
    }

    /**
     * This method creates dialog with message when exception occurs
     * @param message message to display
     */
    public void printException(String message){
        view.printExceptionDetails(message);
        view.displayErrorMessage(message);
    }

    /**
     * Method which reads single line from file chosen by the user and sends request to the server
     * @throws IOException
     */
    public void testNetwork() throws IOException {
        FileReader reader = new FileReader(view.getFilePath());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String mushroomLine = bufferedReader.readLine();
        answer = server.sendServer("TEST_NETWORK\n"+mushroomLine);
        view.displayResultValue("Given mushroom is "+ answer[1]);
        bufferedReader.close();
        reader.close();
    }

    /**
     * Method which reads all lines from file chosen by the user and sends requests to the server.
     * After executing requests the percentage of correct classifications by neural network is presented to the user
     * @throws IOException
     */
    public void testNetworkMultipleTimes() throws IOException{
        Integer correctRecognitionCounter = 0;
        Integer totalRecognitionsCounter = 0;
        FileReader reader = new FileReader(view.getFilePath());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String mushroomLine;
        while((mushroomLine = bufferedReader.readLine()) != null){
            answer = server.sendServer("TEST_NETWORK_MULTIPLE\n"+mushroomLine);
            if(answer[1].equals("correct_recognition")){
                correctRecognitionCounter++;
            }
            System.out.println(answer[1]);
            totalRecognitionsCounter++;
        }
        System.out.println(correctRecognitionCounter);
        Double correctRecognitionPercentage = 0.0;
        correctRecognitionPercentage = ((double)correctRecognitionCounter/(double)totalRecognitionsCounter)*100.0;
        view.displayResultValue(String.format("%.2f", correctRecognitionPercentage)+" % correct predictions");
        bufferedReader.close();
        reader.close();
    }

    /**
     * Method which reads all lines from file chosen by the user and sends requests to the server.
     * Server is learning and is returning current Mean Squarred Error.
     * Until the MSE is not smaller than assumed value all sequence is repeated in loop
     * @throws IOException
     */
    public void learnNetwork() throws IOException {
        view.displayTrainingResultValue("");
        view.resetResultValue();
        String learnRate;
        String momentum;
        learnRate = view.getLearnRate();
        momentum = view.getMomentum();
        answer = server.sendServer("SET_LEARN_RATE\n" + learnRate);
        answer = server.sendServer("SET_MOMENTUM\n" + momentum);

        String filePath = view.getFilePath();
        Integer numberOfEpoch = 0;
        Double mse = 100.0;
        while(mse > Double.parseDouble(view.getMse())){
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String mushroomLine;
            while((mushroomLine = bufferedReader.readLine()) != null){
                answer = server.sendServer("LEARN_NETWORK\n"+mushroomLine);
            }
            bufferedReader.close();
            reader.close();
            answer = server.sendServer("GET_MSE\n");
            System.out.println("Mse: "+answer[1]);
            mse = Double.parseDouble(answer[1]);
            System.out.println("Epoch: "+numberOfEpoch);
            answer = server.sendServer("RESET_MSE\n");
            numberOfEpoch++;
        }

        view.displayTrainingResultValue("<html><body><div align='center'><br>Epoch: "+ numberOfEpoch + "</br><br>Epoch mean squared error: " + String.format("%.10f", mse) + "</br></div></body></html>");

    }

    /**
     * Execution button listener
     */
    class ExecuteListener implements ActionListener{
        private ServerConnection currentConnection;
        @Override
        public void actionPerformed(ActionEvent e){

            currentConnection = server;
            try {
                if( server.Connected() ){
                    if (view.getOperationType() == 0){
                        learnNetwork();
                    }
                    else if(view.getOperationType() == 1){
                        testNetwork();
                    }
                    else{
                        testNetworkMultipleTimes();
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                view.displayErrorMessage("Server is not currently available. Any operations can't be done");
                view.changeStatusLabel("disconnected");
            }

        }
    }

    /**
     * Reset neural network parameters button listener
     */
    class ResetParametersListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.showWarningResetPopup()){
                try {
                    answer = server.sendServer("RESET_PARAMETERS\n");
                    view.displayResetParametersResult(answer[1]);
                } catch (IOException e1) {
                    System.out.println("Exception occured during reset");
                    e1.printStackTrace();
                }
            }
        }
    }
}