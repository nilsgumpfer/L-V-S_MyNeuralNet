public class ArcTan implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        return Math.atan(input);
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
