package functions.loss;

public interface LossFunction {
    double calcOutput(double givenValue, double targetValue);
}
