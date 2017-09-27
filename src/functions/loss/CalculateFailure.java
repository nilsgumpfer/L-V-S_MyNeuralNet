package functions.loss;

public class CalculateFailure implements LossFunction {
    @Override
    public double calcOutput(double givenValue, double targetValue) {
        double diff = targetValue - givenValue;
        return Math.abs(diff);
    }
}
