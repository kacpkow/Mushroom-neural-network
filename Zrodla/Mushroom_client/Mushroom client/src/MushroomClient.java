import javax.swing.*;

/**
 * Main method, launches GUI and Controller
 * @author Kacper Kowalik
 * @version 1.0
 */
public class MushroomClient {
    public static void main(String[] args) throws Exception {

        Gui view = new Gui();
        ClientController controller = new ClientController(view);
        view.setVisible(true);
    }
}
