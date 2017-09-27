package functions.activation;

public class ReLU implements ActivationFunction {
    @Override
    public double calcOutput(double input) {
        if(input < 0)
            return 0;
        else
            return input;
    }

    @Override
    public double calcOutputForDerivative(double input) {
        if (input > 0.0)
            return 1.0;
        else
            return 0.0;
    }
}
