public class Gaussian implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        return Math.pow(Math.E, Math.pow(-1 * input, 2));
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
