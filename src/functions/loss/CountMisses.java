package functions.loss;

public class CountMisses implements LossFunction {
    @Override
    public double calcOutput(double givenValue, double targetValue) {
        if(givenValue != targetValue)
            return 1;
        else
            return 0;
    }
}
