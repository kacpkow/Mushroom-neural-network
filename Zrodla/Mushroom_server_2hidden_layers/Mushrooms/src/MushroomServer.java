import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class contains main() method which launches server and makes connected clients able to train or test neural network
 * @author Kacper Kowalik
 * @version 1.0
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

    /**
     * Object of NeuralNetwork class
     */
    private NeuralNetwork neuralNetwork;

    /**
     * Object of mushroom class
     */
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

    /**
     * Method which starts the server
     */
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

    /**
     * The main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        MushroomServer server = new MushroomServer();
        server.start();
    }
}