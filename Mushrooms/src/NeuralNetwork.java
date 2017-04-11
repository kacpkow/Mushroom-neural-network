import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by kacper on 2017-04-10.
 */

public class NeuralNetwork {
    //Number of neural network inputs
    private Integer inputsNumber;
    private static final int hiddenNeurons = 10;
    //Number of hidden layers of neural network
    //private Integer hiddenLayers;

    //learning rate
    private Double learningRate;

    //alpha
    private Double alpha;

    private List<Neuron> inputNeuronsList;
    private List<Neuron> hiddenNeuronsList;
    private List<Node> firstLayerNodes;
    private List<Node> secondLayerNodes;
    private Neuron bInputNeuron;
    private Neuron bHiddenNeuron;
    private Neuron outputNeuron;

    //constructor for new neural network which didn't exist
    NeuralNetwork(Integer inputsNumber){
        this.inputsNumber = inputsNumber;
        this.learningRate = 0.7;
        outputNeuron = new Neuron();
        //initialization of input layer
        inputNeuronsList = new ArrayList<>();
        hiddenNeuronsList = new ArrayList<>();
        firstLayerNodes = new ArrayList<>();
        secondLayerNodes = new ArrayList<>();

        //initialization of hidden layer
        for(int i=1; i<=10; i++){
            Neuron tmpNeuron = new Neuron();
            secondLayerNodes.add(new Node(0.0, 0.0, 0.0, tmpNeuron, outputNeuron));
            hiddenNeuronsList.add(tmpNeuron);
        }
        bHiddenNeuron = new Neuron();
        secondLayerNodes.add(new Node(0.0, 0.0, 0.0, bInputNeuron, outputNeuron));

        for(int i=1; i<=22; i++){
            Neuron tmpNeuron = new Neuron();
            for(Neuron hiddenNeuron:hiddenNeuronsList){
                firstLayerNodes.add(new Node(0.0, 0.0, 0.0, tmpNeuron, hiddenNeuron));
            }
            inputNeuronsList.add(tmpNeuron);
        }

        bInputNeuron = new Neuron();
        for(Neuron hiddenNeuron:hiddenNeuronsList){
            firstLayerNodes.add(new Node(0.0, 0.0, 0.0, bInputNeuron, hiddenNeuron));
        }

    }

    public Double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(Double learningRate) {
        this.learningRate = learningRate;
    }

    public void calculateOutput(){
        for(Node nodes:secondLayerNodes){
            nodes.getkNeuron().setSumValue(nodes.getkNeuron().getSumValue() + nodes.getiNeuron().getOutValue() * nodes.getWeight());
        }
        outputNeuron.calculateNeuronOutput();
    }

    public void calculateHiddenNeuronsOutput(){
        Double partialSum = 0.0;

        for(Node nodes:firstLayerNodes){
            nodes.getkNeuron().setSumValue(nodes.getkNeuron().getSumValue() + nodes.getiNeuron().getOutValue() * nodes.getWeight());
        }

        for(Neuron neurons:hiddenNeuronsList){
            neurons.calculateNeuronOutput();
        }
    }

    public Double calculateError(){
        return 0.0;
    }

    public void readDataFromFile(){

    }
}
