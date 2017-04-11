import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

/**
 * Controller for client
 * @author Kacper Kowalik
 * @version 2.2
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

    private Gui view;
    private ServerConnection server;
    private boolean connectionState = true;

    public ClientController(Gui theView) throws IOException {
        this.view = theView;
        try{
            this.server = new ServerConnection(HOST, PORT);
        }catch(IOException e){
            connectionState = false;
            view.changeStatusLabel();
            view.displayErrorMessage("Server is not currently available. Any operations on neural are unavailable");
        }
        this.view.addExecuteListener(new ExecuteListener());
    }

    public void setNumberDecValue(int value) throws StringIndexOutOfBoundsException, IOException{
        answer = server.sendServer("SETDECREQUEST\n"+String.valueOf(value));
    }


    public void convertToDec(String...args) throws Exception{
        if(connectionState){
            if(args.length != 0){
                //setNumberBinValue(args[0]);
                //view.setBinNumberToConversion(args[0]);
            }
            view.setDecimalConversion();
        }
    }

    /**
     * This method creates dialog with message when exception occurs
     * @param message message to display
     */
    public void printException(String message){
        view.printExceptionDetails(message);
        view.displayErrorMessage(message);
    }

    public void setMushroomFields(String value) throws IOException{
        answer = server.sendServer("SETMUSHROOMFIELDS\n"+value);
    }

    public void parseMushroomsData() throws IOException {
        FileReader reader = new FileReader(view.getFilePath());
        BufferedReader bufferedReader = new BufferedReader(reader);
        String mushroomLine = bufferedReader.readLine();
        mushroomLine = bufferedReader.readLine();
        answer = server.sendServer("SETMUSHROOMFIELDS\n"+mushroomLine);
        bufferedReader.close();
        reader.close();
    }

    class ExecuteListener implements ActionListener{
        private ServerConnection currentConnection;
        @Override
        public void actionPerformed(ActionEvent e){

            //int decNumber = 0;
            //String binNumber = "";
            currentConnection = server;
            // Surround interactions with the view with
            // a try block in case numbers weren't
            // properly entered
            try {
                if( server.Connected() ){
                    if (view.getOperationType()){
                        parseMushroomsData();
                    }
                }
            } catch (IOException ex) {
                view.displayErrorMessage("Server is not currently available. Conversion can't be done");
            }

        }
    }
}