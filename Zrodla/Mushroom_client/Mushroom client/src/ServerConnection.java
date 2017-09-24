import java.io.*;
import java.net.*;

/**
 * Class used to make a connection to server
 *@author Kacper Kowalik
 * @version 1.0
 **/
public class ServerConnection {

    /**
     * Connection to server
     */
    private final Socket server;

    /**
     * Client stream connect to server
     */
    private final BufferedReader in;

    /**
     * Output stream for server
     */
    private final PrintWriter out;

    /**
     * Initial server connect
     * @param adres adress from server
     * @param port server port to connect
     * @throws IOException if there were errors with I/O stream
     */
    public ServerConnection(String adres, int port) throws IOException{
        this.server = new Socket(adres, port);
        this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())), true);
    }

    /**
     *
     * @return true if connected and false if not
     * @throws IOException
     */
    public boolean Connected() throws IOException{
        out.println("connection");

        String[] answer = new String[1];
        answer[0] = in.readLine(); // replay delivery
        if(answer[0].equals(null))
            return false;
        else
            return true;

    }

    /**
     * Method which sends requests to the server
     * @param send
     * @return
     * @throws IOException
     */
    public String[] sendServer(String send) throws IOException{
        out.println(send);

        String[] answer = new String[2];
        answer[0] = in.readLine(); // replay delivery
        answer[1] = in.readLine(); // get answer

        return answer;
    }

}