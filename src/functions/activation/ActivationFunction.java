package functions.activation;

public interface ActivationFunction {
    double calcOutput(double input);
    double calcOutputForDerivative(double input);
}
