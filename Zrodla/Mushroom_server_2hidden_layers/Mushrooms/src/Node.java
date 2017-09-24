/**
 * Class for objects which represents single connection between neuron one layer and between neuron from other layer
 * @author Kacper Kowalik
 * @version 1.0
 */
public class Node {
    /**
     * This field stores weight value of particular node
     */
    private Double weight;

    /**
     * This field stores previous weight correction value of particular node.
     * Previous weight correction is used in backpropagation with momentum
     */
    private Double previousWeightCorrection;

    /**
     * This field stores gradient value of particular node
     */
    private Double gradient;

    Node(Double weight, Double previousWeightCorrection, Double gradient){
        this.weight = weight;
        this.previousWeightCorrection = previousWeightCorrection;
        this.gradient = gradient;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPreviousWeightCorrection() {
        return previousWeightCorrection;
    }

    public void setPreviousWeightCorrection(Double previousWeightCorrection) {
        this.previousWeightCorrection = previousWeightCorrection;
    }

    public Double getGradient() {
        return gradient;
    }

    public void setGradient(Double gradient) {
        this.gradient = gradient;
    }

}
