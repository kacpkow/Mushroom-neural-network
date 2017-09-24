import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kacper on 2017-04-06.
 */
public class MushroomServer {

    /**
     * port number
     */
    private final int PORT = 8888;

    /**
     * field represents the socket waiting for client connections
     */
    private final ServerSocket serverSocket;

    /*object of model class*/
    private NeuralNetwork neuralNetwork;

    private Mushroom mushroom;

    /**
     * Creates the server socket
     * @throws IOException when port is already
     * bind
     */
    MushroomServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        neuralNetwork = new NeuralNetwork();
        mushroom = new Mushroom();
    }

    public void start(){
        System.out.println("Server started");
        try {
            int id = 1;
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    SingleService service = new SingleService(socket, neuralNetwork, mushroom, id);
                    System.out.println("Client "+ id + " connect to server");
                    service.start();
                    id++;
                } catch (IOException e) {
                    socket.close();
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                System.out.println("Closing server");
                serverSocket.close();
            }
            catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MushroomServer server = new MushroomServer();
        server.start();
    }
}