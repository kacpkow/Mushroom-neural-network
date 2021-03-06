/**
 * Created by kacper on 2017-04-06.
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class which handles single connection
 * @version 1.0
 * @author Kacper Kowalik
 */

public class SingleService extends Thread{

    /**
     * socket representing connection to the client
     */
    private final Socket socket;
    /**
     * ID of connected client
     */
    private final int id;
    /**
     * buffered input character stream
     */
    private BufferedReader in;
    /**
     * Formatted output character stream
     */
    private PrintWriter out;
    /**
     * Protocol of server for manage and realize connection
     */
    private final Protocol protocol;

    /**
     * Object of NeuralNetwork class that represents current trained or not trained neural network
     */
    private NeuralNetwork neuralNetwork;

    /**
     * Object of Mushroom class that represents currently processed mushroom
     */
    private final Mushroom mushroom;

    /**
     * The constructor of instance of the SingleService class. Use the socket as
     * a parameter.
     */
    public SingleService(Socket socket, NeuralNetwork neuralNetwork, Mushroom mushroom, int id) throws IOException{
        this.socket = socket;
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        this.protocol = new Protocol();
        this.neuralNetwork = neuralNetwork;
        this.mushroom = mushroom;
        this.id = id;
    }

    /**
     * Realizes the service
     */
    @Override
    public void run() {
        try {
            while (true) {
                String option = "";
                String parameter = "";
                option = in.readLine();
                out.println("Server answers: " + option);
                switch(option.toUpperCase()){
                    case "EXIT":
                        out.println(this.protocol.showExit());
                        break;
                    case "QUIT":
                        out.println(this.protocol.showExit());
                        break;
                    case "LEARN_NETWORK":
                        parameter = in.readLine();
                    {
                        try {
                            out.println(this.protocol.learnNetwork(parameter, neuralNetwork, mushroom, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "TEST_NETWORK":
                        parameter = in.readLine();
                    {
                        try {
                            out.println(this.protocol.testNetwork(parameter, neuralNetwork, mushroom, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "TEST_NETWORK_MULTIPLE":
                        parameter = in.readLine();
                    {
                        try {
                            out.println(this.protocol.testNetworkMultiple(parameter, neuralNetwork, mushroom, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "SET_LEARN_RATE":
                        parameter = in.readLine();
                    {
                        try {
                            out.println(this.protocol.setLearnRate(parameter, neuralNetwork, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "SET_MOMENTUM":
                        parameter = in.readLine();
                    {
                        try {
                            out.println(this.protocol.setMomentum(parameter, neuralNetwork, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "RESET_MSE":
                    {
                        parameter = in.readLine();
                        try {
                            out.println(this.protocol.resetMse(neuralNetwork, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "GET_MSE":
                    {
                        parameter = in.readLine();
                        try {
                            System.out.println("MSE from protocol "+this.protocol.getMse(neuralNetwork, id));
                            out.println(this.protocol.getMse(neuralNetwork, id));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                    case "RESET_PARAMETERS":
                    {
                        parameter = in.readLine();
                        try {
                            neuralNetwork = new NeuralNetwork();
                            out.println("Parameters reset successfully");
                        } catch (Exception ex) {
                            out.println("Parameters not reset successfully. Try again");
                            ex.printStackTrace();
                        }
                    }
                    break;
                }
                if(option.toUpperCase().equals("EXIT") || option.toUpperCase().equals("QUIT")){
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch(NullPointerException n){
            System.out.println("Lose connection with client: " + id);
        } finally {
            try {
                socket.close();
                System.out.println("Client: " + id + " has disconnected.");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
