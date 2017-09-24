/**
 * Created by kacper on 2017-04-11.
 */
public class Node {
    private Double weight;
    private Double previousWeightCorrection;
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
