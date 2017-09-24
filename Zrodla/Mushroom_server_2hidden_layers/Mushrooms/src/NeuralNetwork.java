import java.util.Random;

import static java.lang.Math.*;

/**
 * This is implementation of Neural Network with 2 hidden layers,
 * total amount of layers is 4 (input layer - first hidden layer - second hidden layer - output layer)
 * @author Kacper Kowalik
 * @version 2.0
 */

public class NeuralNetwork {

    /**
     * 0 - sigmoid
     * 1 - hyperbolic tangens
     */
    private int activationFunctionDerivativeOption = 1;
    //sigmoid change: comment line above and uncomment following line
    //private int activationFunctionDerivativeOption = 0;

    /**
     * Mean squared error value
     */
    private Double mse = 0.0;

    /**
     * Mean squared error numerator
     */
    private Double mseNumerator = 0.0;

    /**
     * Trial number used to calculate mean squared error
     */
    private Integer trialNumber = 0;

    /**
     * error of each trial (ideal output - output from neural network)
     */
    private Double error = 1.0;

    /**
     * Number of neural network inputs (without BIAS neuron)
     */
    private static final int inputsNumber = 22;

    /**
     * Number of neural network first layer inputs (without BIAS neuron)
     */
    private static final int hiddenFirstLayerNeurons = 6;

    /**
     * Number of neural network second layer inputs (without BIAS neuron)
     */
    private static final int hiddenSecondLayerNeurons = 4;

    /**
     * Neural network learn rate
     */
    private Double learningRate;

    /**
     * Neural network momentum(alpha)
     */
    private Double alpha;

    /**
     * Output neuron
     */
    private Neuron outputNeuron;
    /**
     * Input neurons
     */
    private Neuron[] inputNeurons;
    /**
     * First layer neurons
     */
    private Neuron[] firstLayerNeurons;
    /**
     * Second layer neurons
     */
    private Neuron[] secondLayerNeurons;

    /**
     * Nodes between input neurons layer and first hidden layer
     */
    private Node[][] hiddenFirstNodesMatrix;

    /**
     * Nodes between first hidden layer and second hidden layer
     */
    private Node[][] hiddenSecondNodesMatrix;

    /**
     * Nodes between second hidden layer and output neuron
     */
    private Node[] hiddenThirdNodesMatrix;

    /**
     * Currently processed mushroom object
     */
    private Mushroom currentMushroom;

    /**
     * Constructor for new Neural Network
     */
    NeuralNetwork() {
        this.learningRate = 0.5;
        this.alpha = 0.5;
        inputNeurons = new Neuron[inputsNumber + 1];
        firstLayerNeurons = new Neuron[hiddenFirstLayerNeurons + 1];
        secondLayerNeurons = new Neuron[hiddenSecondLayerNeurons + 1];

        outputNeuron = new Neuron();

        //initializing all neurons(with BIAS neurons)

        for (int i = 0; i < inputsNumber + 1; i++) {
            inputNeurons[i] = new Neuron();
        }

        for (int i = 0; i < hiddenFirstLayerNeurons + 1; i++) {
            firstLayerNeurons[i] = new Neuron();
        }

        for (int i = 0; i < hiddenSecondLayerNeurons + 1; i++) {
            secondLayerNeurons[i] = new Neuron();
        }

        //creating nodes for all neurons(with BIAS neurons)
        hiddenFirstNodesMatrix = new Node[inputsNumber + 1][hiddenFirstLayerNeurons + 1];
        hiddenSecondNodesMatrix = new Node[hiddenFirstLayerNeurons + 1][hiddenSecondLayerNeurons + 1];
        hiddenThirdNodesMatrix = new Node[hiddenSecondLayerNeurons + 1];

        Random generator = new Random();

        //Initializing nodes and their random weights from 0.0..1.0 range

        for (int i = 0; i < inputsNumber + 1; i++) {
            for (int j = 0; j < hiddenFirstLayerNeurons + 1; j++) {
                hiddenFirstNodesMatrix[i][j] = new Node(generator.nextDouble(), 0.0, 0.0);
            }
        }

        for (int i = 0; i < hiddenFirstLayerNeurons + 1; i++) {
            for (int j = 0; j < hiddenSecondLayerNeurons + 1; j++) {
                hiddenSecondNodesMatrix[i][j] = new Node(generator.nextDouble(), 0.0, 0.0);
            }
        }

        for (int i = 0; i < hiddenSecondLayerNeurons + 1; i++) {
            hiddenThirdNodesMatrix[i] = new Node(generator.nextDouble(), 0.0, 0.0);
        }

        //setting BIAS neurons input to 1
        inputNeurons[inputsNumber].setOutValue(1.0);
        firstLayerNeurons[hiddenFirstLayerNeurons].setOutValue(1.0);
        secondLayerNeurons[hiddenSecondLayerNeurons].setOutValue(1.0);
    }

    /**
     * Sets learning rate value
     *
     * @param learningRate
     */
    public void setLearningRate(Double learningRate) {
        this.learningRate = learningRate;
    }

    /**
     * Sets momentum(alpha) value
     *
     * @param alpha
     */
    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    /**
     * Sets input neurons out value according to values which describe currently processed mushroom
     */
    public void setInputNeurons() {
        Double newMax = 1.0;
        Double newMin = -1.0;
        //sigmoid change: comment line above and uncomment following line
        //Double newMin = -0.0;
        inputNeurons[0].setOutValue(currentMushroom.normaliseData(currentMushroom.getCapSurfaceOrdinalValue(), 0, 3, newMin, newMax));
        inputNeurons[1].setOutValue(currentMushroom.normaliseData(currentMushroom.getCapShapeOrdinalValue(), 0, 5, newMin, newMax));
        inputNeurons[2].setOutValue(currentMushroom.normaliseData(currentMushroom.getCapColorOrdinalValue(), 0, 9, newMin, newMax));
        inputNeurons[3].setOutValue(currentMushroom.normaliseData(currentMushroom.getBruisesOrdinalValue(), 0, 1, newMin, newMax));
        inputNeurons[4].setOutValue(currentMushroom.normaliseData(currentMushroom.getOdorOrdinalValue(), 0, 8, newMin, newMax));
        inputNeurons[5].setOutValue(currentMushroom.normaliseData(currentMushroom.getGillAttachmentOrdinalValue(), 0, 3, newMin, newMax));
        inputNeurons[6].setOutValue(currentMushroom.normaliseData(currentMushroom.getGillSpacingOrdinalValue(), 0, 2, newMin, newMax));
        inputNeurons[7].setOutValue(currentMushroom.normaliseData(currentMushroom.getGillSizeOrdinalValue(), 0, 1, newMin, newMax));
        inputNeurons[8].setOutValue(currentMushroom.normaliseData(currentMushroom.getGillColorOrdinalValue(), 0, 11, newMin, newMax));
        inputNeurons[9].setOutValue(currentMushroom.normaliseData(currentMushroom.getStalkShapeOrdinalValue(), 0, 1, newMin, newMax));
        inputNeurons[10].setOutValue(currentMushroom.normaliseData(currentMushroom.getStalkRootOrdinalValue(), 0, 6, newMin, newMax));
        inputNeurons[11].setOutValue(currentMushroom.normaliseData(currentMushroom.getStalkSurfaceAboveRingOrdinalValue(), 0, 3, newMin, newMax));
        inputNeurons[12].setOutValue(currentMushroom.normaliseData(currentMushroom.getStalkSurfaceBelowOrdinalValue(), 0, 3, newMin, newMax));
        inputNeurons[13].setOutValue(currentMushroom.normaliseData(currentMushroom.getStalkColorAboveRingOrdinalValue(), 0, 8, newMin, newMax));
        inputNeurons[14].setOutValue(currentMushroom.normaliseData(currentMushroom.getStalkColorBelowRingOrdinalValue(), 0, 8, newMin, newMax));
        inputNeurons[15].setOutValue(currentMushroom.normaliseData(currentMushroom.getVeilTypeOrdinalValue(), 0, 1, newMin, newMax));
        inputNeurons[16].setOutValue(currentMushroom.normaliseData(currentMushroom.getVeilColorOrdinalValue(), 0, 3, newMin, newMax));
        inputNeurons[17].setOutValue(currentMushroom.normaliseData(currentMushroom.getRingNumberOrdinalValue(), 0, 2, newMin, newMax));
        inputNeurons[18].setOutValue(currentMushroom.normaliseData(currentMushroom.getRingTypeOrdinalValue(), 0, 7, newMin, newMax));
        inputNeurons[19].setOutValue(currentMushroom.normaliseData(currentMushroom.getSporePrintColorOrdinalValue(), 0, 8, newMin, newMax));
        inputNeurons[20].setOutValue(currentMushroom.normaliseData(currentMushroom.getPopulationOrdinalValue(), 0, 5, newMin, newMax));
        inputNeurons[21].setOutValue(currentMushroom.normaliseData(currentMushroom.getHabitatOrdinalValue(), 0, 6, newMin, newMax));
    }

    /**
     * This method launches output, error and backpropagation calculations
     */
    public void learnNetwork() {
        calculateOutputv2();
        error = calculateError();
        calculateMeanSquaredError();

        System.out.println("output: " + outputNeuron.getOutValue());
        System.out.println("Desired output: " + normaliseOutput(currentMushroom.getClasificationOrdinalValue(), 0, 1, 0.0, 1.0));
        System.out.println("error: " + error);
        System.out.println("mean sqarred error: " + mse);

        calculateDeltasAndGradientsv2();
    }

    /**
     * Sets error to default value
     */
    public void setErrorToDefaultValue() {
        error = 1.0;
    }

    /**
     * This method launch output calculation for mushroom
     */
    public void testNetwork() {
        calculateOutputv2();
    }

    /**
     * This method sets the mushroom to mushroom passed by protocol
     *
     * @param mushroom
     */
    public void setMushroom(Mushroom mushroom) {
        currentMushroom = mushroom;
    }

    /**
     * This method calculates output neuron output value
     */
    public void calculateOutputv2() {
        double partialSum = 0.0;
        for (int i = 0; i < hiddenFirstLayerNeurons + 1; i++) {
            for (int j = 0; j < inputsNumber + 1; j++) {
                partialSum += inputNeurons[j].getOutValue() * hiddenFirstNodesMatrix[j][i].getWeight();
            }
            if (i != hiddenFirstLayerNeurons) {
                firstLayerNeurons[i].setSumValue(partialSum);
                firstLayerNeurons[i].calculateNeuronOutput();
            }
            partialSum = 0.0;
        }

        for (int i = 0; i < hiddenSecondLayerNeurons + 1; i++) {
            for (int j = 0; j < hiddenFirstLayerNeurons + 1; j++) {
                partialSum += firstLayerNeurons[j].getOutValue() * hiddenSecondNodesMatrix[j][i].getWeight();
            }
            if (i != hiddenSecondLayerNeurons) {
                secondLayerNeurons[i].setSumValue(partialSum);
                secondLayerNeurons[i].calculateNeuronOutput();
            }
            partialSum = 0.0;
        }

        partialSum = 0.0;

        for (int i = 0; i < hiddenSecondLayerNeurons + 1; i++) {
            partialSum = partialSum + secondLayerNeurons[i].getOutValue() * hiddenThirdNodesMatrix[i].getWeight();
        }
        outputNeuron.setSumValue(partialSum);
        outputNeuron.calculateNeuronOutput();
    }

    /**
     * Getter for output value
     * @return
     */
    public Double getOutputValue() {
        return outputNeuron.getOutValue();
    }

    /**
     * ideal output - current output calculation
     * @return error
     */
    public Double calculateError() {
        Double error;
        error = normaliseOutput(currentMushroom.getClasificationOrdinalValue(), 0, 1, -1.0, 1.0) - outputNeuron.getOutValue();
        //sigmoid change: comment line above and uncomment following line
        //error = normaliseOutput(currentMushroom.getClasificationOrdinalValue(), 0, 1, 0.0, 1.0) - outputNeuron.getOutValue();
        return error;
    }

    /**
     * mean squarred error calculation
     */
    public void calculateMeanSquaredError() {
        trialNumber++;
        mseNumerator = mseNumerator + pow(error, 2.0);
        mse = mseNumerator / trialNumber;
    }

    /**
     * Resets mean squared error
     */
    public void resetMeanSquaredError() {
        trialNumber = 0;
        mseNumerator = 0.0;
        mse = 0.0;
    }

    /**
     * Getter for mean squared error converted to string for protocol
     * @return
     */
    public String getMeanSquaredError() {
        return String.valueOf(mse);
    }

    /**
     * This method calculates error of output neuron layer and backpropagates the error to the previous layers and makes Nodes weight correction
     */
    public void calculateDeltasAndGradientsv2() {
        outputNeuron.setDeltaValue(calculateError() * activationFunctionDerivative(activationFunctionDerivativeOption, outputNeuron.getOutValue()));

        //3
        for (int i = 0; i <= hiddenSecondLayerNeurons; i++) {
            secondLayerNeurons[i].setDeltaValue(outputNeuron.getDeltaValue() * hiddenThirdNodesMatrix[i].getWeight() * activationFunctionDerivative(activationFunctionDerivativeOption, secondLayerNeurons[i].getOutValue()));
            hiddenThirdNodesMatrix[i].setGradient(outputNeuron.getDeltaValue() * secondLayerNeurons[i].getOutValue());
        }

        Double temp = 0.0;
        for (int i = 0; i <= hiddenFirstLayerNeurons; i++) {
            for (int j = 0; j < hiddenSecondLayerNeurons; j++) {
                temp += hiddenSecondNodesMatrix[i][j].getWeight() * secondLayerNeurons[j].getDeltaValue();
            }
            firstLayerNeurons[i].setDeltaValue(activationFunctionDerivative(activationFunctionDerivativeOption, firstLayerNeurons[i].getOutValue()) * temp);
            temp = 0.0;
        }

        for (int i = 0; i <= hiddenSecondLayerNeurons; i++) {
            for (int j = 0; j <= hiddenFirstLayerNeurons; j++) {
                hiddenSecondNodesMatrix[j][i].setGradient(firstLayerNeurons[j].getOutValue() * secondLayerNeurons[i].getDeltaValue());
            }
        }


        for (int i = 0; i <= hiddenFirstLayerNeurons; i++) {
            for (int j = 0; j <= inputsNumber; j++) {
                hiddenFirstNodesMatrix[j][i].setGradient(inputNeurons[j].getOutValue() * firstLayerNeurons[i].getDeltaValue());
            }
        }

        for (int i = 0; i <= hiddenSecondLayerNeurons; i++) {
            temp = learningRate * hiddenThirdNodesMatrix[i].getGradient() + alpha * hiddenThirdNodesMatrix[i].getPreviousWeightCorrection();
            hiddenThirdNodesMatrix[i].setWeight(hiddenThirdNodesMatrix[i].getWeight() + temp);
            hiddenThirdNodesMatrix[i].setPreviousWeightCorrection(temp);
            temp = 0.0;
        }

        //2 warstwa węzłów
        for (int i = 0; i <= hiddenSecondLayerNeurons; i++) {
            for (int j = 0; j <= hiddenFirstLayerNeurons; j++) {
                temp = learningRate * hiddenSecondNodesMatrix[j][i].getGradient() + alpha * hiddenSecondNodesMatrix[j][i].getPreviousWeightCorrection();
                hiddenSecondNodesMatrix[j][i].setWeight(hiddenSecondNodesMatrix[j][i].getWeight() + temp);
                hiddenSecondNodesMatrix[j][i].setPreviousWeightCorrection(temp);
                temp = 0.0;
            }
        }

        //1 warstwa węzłów
        for (int i = 0; i <= hiddenFirstLayerNeurons; i++) {
            for (int j = 0; j <= inputsNumber; j++) {
                temp = learningRate * hiddenFirstNodesMatrix[j][i].getGradient() + alpha * hiddenFirstNodesMatrix[j][i].getPreviousWeightCorrection();
                hiddenFirstNodesMatrix[j][i].setWeight(hiddenFirstNodesMatrix[j][i].getWeight() + temp);
                hiddenFirstNodesMatrix[j][i].setPreviousWeightCorrection(temp);
                temp = 0.0;
            }
        }
    }

    /**
     * Return derivative of activation function value for chosen x
     * @param option 0 - sigmoid derivative, 1 - hyperbolic tangent derivative
     * @param x
     * @return
     */
    public Double activationFunctionDerivative(int option, Double x){
        if(option == 0){
            return (pow(Math.E, x)/pow(1+pow(Math.E, x),2));
        }
        else
            return 1/(pow(cosh(x), 2));
    }

    /**
     * This function returns normalized output
     * @param value
     * @param min
     * @param max
     * @param new_min
     * @param new_max
     * @return
     */
    public Double normaliseOutput(Integer value, Integer min, Integer max, Double new_min, Double new_max) {
        return ((value / 1.0 - min / 1.0) / (max / 1.0 - min / 1.0)) * (new_max - new_min) + new_min;
    }

}