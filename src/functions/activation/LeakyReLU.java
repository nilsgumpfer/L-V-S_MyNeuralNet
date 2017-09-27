package functions.activation;

public class LeakyReLU implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        if(input < 0)
            return 0.01 * input;
        else
            return input;
    }

    @Override
    public double calcOutputForDerivative(double input) {return 0;}
}
