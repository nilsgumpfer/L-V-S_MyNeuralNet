import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sigmoid implements ActivationFunction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public double calcOutput(double input) {
        double out = (1/( 1 + Math.pow(Math.E,(-1*input))));
        //logger.trace("{}->{}",input,out);
        return out;
    }

    @Override
    public double calcOutputForDerivative(double input) {
        return 0;
    }
}
