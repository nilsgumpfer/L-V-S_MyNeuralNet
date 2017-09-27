public class Softsign implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        return input / (1 + Math.abs(input));
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
