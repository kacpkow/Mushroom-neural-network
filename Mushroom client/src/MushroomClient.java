/**
 * Created by kacper on 2017-04-06.
 */
public class MushroomClient {
    public static void main(String[] args) throws Exception {

        Gui view = new Gui();
        ClientController controller = new ClientController(view);
        view.setVisible(true);
    }
}
