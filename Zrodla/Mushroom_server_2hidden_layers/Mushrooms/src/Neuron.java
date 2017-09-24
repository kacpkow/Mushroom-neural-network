import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Neuron class
 * @author Kacper Kowalik
 * @version 1.0
 */
public class Neuron {
    private Double sumValue;
    private Double outValue;
    private Double deltaValue;

    Neuron() {
        this.sumValue = 0.0;
        this.outValue = 0.0;
        this.deltaValue = 0.0;
    }

    public Double getSumValue() {
        return sumValue;
    }

    public void setSumValue(Double sumValue) {
        this.sumValue = sumValue;
    }

    public Double getOutValue() {
        return outValue;
    }

    public void setOutValue(Double outValue) {
        this.outValue = outValue;
    }

    public Double getDeltaValue() {
        return deltaValue;
    }

    public void setDeltaValue(Double deltaValue) {
        this.deltaValue = deltaValue;
    }

    public void calculateNeuronOutput(){

        this.setOutValue(calculateHyperbolicTangensValue(this.getSumValue()));
        //sigmoid change: comment line above and uncomment following line
        //this.setOutValue(calculateSigmoidFunctionValue(this.getSumValue()));
    }

    public Double calculateSigmoidFunctionValue(Double value){
        return 1/(1+pow(E, -1.0 * value));
    }

    public Double calculateHyperbolicTangensValue(Double value){
        return Math.tanh(value);
    }

}
