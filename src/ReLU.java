import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReLU implements ActivationFunction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public double calcOutput(double input) {
        double out = Math.max(0.0,input);
        //logger.trace("{}->{}",input,out);
        return out;
    }

    @Override
    public double calcOutputForDerivative(double input) {
        if (input > 0.0)
            return 1.0;
        else
            return 0.0;
    }
}
