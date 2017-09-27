package functions.activation;

public class TanH implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        return Math.tanh(input);
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
