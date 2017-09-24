import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Controller for requests from clients
 * @author Kacper Kowalik
 * @version 1.0
 */
public class Protocol {

    /**
     * This value stores currently processed number of mushroom
     */
    private static int numberOfMushroom = 0;

    /**
     * This method resets current Mean Squarred Error(MSE) for neural network
     * @param neuralNetwork
     * @param id
     * @return
     */
    public String resetMse(NeuralNetwork neuralNetwork, int id){
        neuralNetwork.resetMeanSquarredError();
        return "Error has been reset";
    }

    /**
     * Method which returns MSE
     * @param neuralNetwork
     * @param id
     * @return
     */
    public String getMse(NeuralNetwork neuralNetwork, int id){
        return neuralNetwork.getMeanSquarredError();
    }

    /**
     * Method which launches setting up new mushroom's parameters, and learning network method of NeuralNetwork
     * @param parameter
     * @param neuralNetwork
     * @param mushroom
     * @param id
     * @return
     * @throws IOException
     */
    public String learnNetwork(String parameter, NeuralNetwork neuralNetwork, Mushroom mushroom, int id) throws IOException {
        String info;
        System.out.println("Client: " + id + "   " );
        System.out.println("Mushroom nr "+numberOfMushroom++);
        Reader reader = new StringReader(parameter);

        readMushroomValues(parameter, mushroom, false);

        neuralNetwork.setMushroom(mushroom);
        neuralNetwork.setInputNeurons();
        neuralNetwork.setErrorToDefaultValue();
        neuralNetwork.learnNetwork();

        info = "";

        return info;
    }

    /**
     * Method which launches setting up new mushroom's parameters and testing neural network. Returns appropriate string which depends from classification of mushroom
     * @param parameter
     * @param neuralNetwork
     * @param mushroom
     * @param id
     * @return
     * @throws IOException
     */
    public String testNetworkMultiple(String parameter, NeuralNetwork neuralNetwork, Mushroom mushroom, int id) throws IOException {
        String info;
        System.out.print("Client: " + id + "   " );

        readMushroomValues(parameter, mushroom ,false);

        neuralNetwork.setMushroom(mushroom);
        neuralNetwork.setInputNeurons();
        neuralNetwork.testNetwork();
        System.out.println(mushroom);
        System.out.println("output: "+neuralNetwork.getOutputValue());

        info = "";

        if(neuralNetwork.getOutputValue() >= 0.0 && mushroom.getClasificationOrdinalValue() == 1){
            //sigmoid change: comment line above and uncomment following line
            //if(neuralNetwork.getOutputValue() >= 0.5 && mushroom.getClasificationOrdinalValue() == 1){
            info = "correct_recognition";
        }
        else if(neuralNetwork.getOutputValue() < 0.0 && mushroom.getClasificationOrdinalValue() == 0){
            //sigmoid change: comment line above and uncomment following line
            //else if(neuralNetwork.getOutputValue() < 0.5 && mushroom.getClasificationOrdinalValue() == 0){
            info = "correct_recognition";
        }
        else{
            info = "bad_recognition";
        }


        System.out.println(info);

        return info;
    }

    /**
     * Method which launches setting up new mushroom's parameters and testing neural network. Returns appropriate string which depends from classification of mushroom
     * @param parameter
     * @param neuralNetwork
     * @param mushroom
     * @param id
     * @return
     * @throws IOException
     */
    public String testNetwork(String parameter, NeuralNetwork neuralNetwork, Mushroom mushroom, int id) throws IOException {
        String info;
        System.out.print("Client: " + id + "   " );

        readMushroomValues(parameter, mushroom ,true);

        neuralNetwork.setMushroom(mushroom);
        neuralNetwork.setInputNeurons();
        neuralNetwork.testNetwork();
        System.out.println(mushroom);
        System.out.println("output: "+neuralNetwork.getOutputValue());

        info = "";

        if(neuralNetwork.getOutputValue() >= 0.5 ){
            info = "poisonous";
        }
        else{
            info = "edible";
        }

        return info;
    }

    /**
     * This method sets the parameters of mushroom to parameters given by client in "parameter" line
     * @param parameter
     * @param mushroom
     * @param opType
     * @throws IOException
     */
    public void readMushroomValues(String parameter, Mushroom mushroom, boolean opType) throws IOException {
        Reader reader = new StringReader(parameter);
        char c;
        String castReader;

        if(!opType){
            c = (char) reader.read();
            reader.read();
            castReader = Character.toString(c);
            mushroom.setClasification(mushroom.getClasification().valueOf(castReader));
        }

        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setCapShape(mushroom.getCapShape().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setCapSurface(mushroom.getCapSurface().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setCapColor(mushroom.getCapColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setBruises(mushroom.getBruises().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setOdor(mushroom.getOdor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillAttachment(mushroom.getGillAttachment().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillSpacing(mushroom.getGillSpacing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillSize(mushroom.getGillSize().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setGillColor(mushroom.getGillColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkShape(mushroom.getStalkShape().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkRoot(mushroom.getStalkRoot().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkSurfaceAboveRing(mushroom.getStalkSurfaceAboveRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkSurfaceBelowRing(mushroom.getStalkSurfaceBelowRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkColorAboveRing(mushroom.getStalkColorAboveRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setStalkColorBelowRing(mushroom.getStalkColorBelowRing().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setVeilType(mushroom.getVeilType().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setVeilColor(mushroom.getVeilColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setRingNumber(mushroom.getRingNumber().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setRingType(mushroom.getRingType().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setSporePrintColor(mushroom.getSporePrintColor().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setPopulation(mushroom.getPopulation().valueOf(castReader));
        c = (char) reader.read();
        reader.read();
        castReader = Character.toString(c);
        mushroom.setHabitat(mushroom.getHabitat().valueOf(castReader));
        reader.close();
    }

    /**
     * This method sets the learn rate to value given by client
     * @param parameter
     * @param network
     * @param id
     * @return
     * @throws IOException
     */
    public String setLearnRate(String parameter, NeuralNetwork network, int id) throws IOException {
        Double learnRate;
        Scanner scanner = new Scanner(parameter);
        //System.out.println(parameter);
        scanner.useLocale(Locale.US);

        learnRate = scanner.nextDouble();
        network.setLearningRate(learnRate);
        String info = "Learn Rate set to: " + learnRate;
        return info;
    }

    /**
     * This method sets the momentum to value given by client
     * @param parameter
     * @param network
     * @param id
     * @return
     * @throws IOException
     */
    public String setMomentum(String parameter, NeuralNetwork network, int id) throws IOException {
        Double momentum;
        Scanner scanner = new Scanner(parameter);
        scanner.useLocale(Locale.US);

        momentum = scanner.nextDouble();
        network.setAlpha(momentum);
        String info = "Momentum set to: " + momentum;
        return info;
    }


    /**
     * Show message about closeing connection
     * @return news to display
     */
    public String showExit(){
        String exit = "Closing connection with server";
        return exit;
    }
}

