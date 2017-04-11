/**
 * Created by kacper on 2017-04-11.
 */
public class Node {
    private Double weight;
    private Double previousWeight;
    private Double gradient;
    private Neuron iNeuron;
    private Neuron kNeuron;

    Node(Double weight, Double previousWeight, Double gradient, Neuron iNeuron, Neuron kNeuron){
        this.weight = weight;
        this.previousWeight = previousWeight;
        this.gradient = gradient;
        this.iNeuron = iNeuron;
        this.kNeuron = kNeuron;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPreviousWeight() {
        return previousWeight;
    }

    public void setPreviousWeight(Double previousWeight) {
        this.previousWeight = previousWeight;
    }

    public Double getGradient() {
        return gradient;
    }

    public void setGradient(Double gradient) {
        this.gradient = gradient;
    }

    public Neuron getiNeuron() {
        return iNeuron;
    }

    public void setiNeuron(Neuron iNeuron) {
        this.iNeuron = iNeuron;
    }

    public Neuron getkNeuron() {
        return kNeuron;
    }

    public void setkNeuron(Neuron kNeuron) {
        this.kNeuron = kNeuron;
    }
}
