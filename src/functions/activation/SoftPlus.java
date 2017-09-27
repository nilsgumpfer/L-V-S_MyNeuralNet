public class SoftPlus implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        return  Math.log(1 + Math.pow(Math.E, input));
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
