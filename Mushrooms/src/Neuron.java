import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by kacper on 2017-04-10.
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

    public Double calculateNeuronOutput(){
        return calculateSigmoidFunctionValue(this.getSumValue());
    }

    public Double calculateSigmoidFunctionValue(Double value){
        return 1/(1+pow(E, -value));
    }
}
//
//    //min-max normalization
//    public Double normaliseData(Integer value, Integer max, Integer min){
//        return ((value - min)/(max - min))*2.0 - 1.0;
//    }
//
//}
