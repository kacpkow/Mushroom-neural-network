/**
 * Created by kacper on 2017-04-06.
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * class which handles single connection
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
     * Object form model class, which converts number
     */
    private final Mushroom mushroom;

    /**
     * The constructor of instance of the SingleService class. Use the socket as
     * a parameter.
     */
    public SingleService(Socket socket, Mushroom mushroom, int id) throws IOException{
        this.socket = socket;
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        this.protocol = new Protocol();
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
                    case "HELP":
                        out.println(this.protocol.showHelp());
                        break;
                    case "EXIT":
                        out.println(this.protocol.showExit());
                        break;
                    case "QUIT":
                        out.println(this.protocol.showExit());
                        break;
                    case "SETMUSHROOMFIELDS":
                        parameter = in.readLine();
                    {
                        try {
                            out.println(this.protocol.setMushroomFields(parameter, mushroom, id));
                        } catch (Exception ex) {
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
