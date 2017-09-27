public class Sigmoid implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        return  (1/( 1 + Math.pow(Math.E,(-1*input))));
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
