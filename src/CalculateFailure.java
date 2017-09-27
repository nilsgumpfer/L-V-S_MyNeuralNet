public class CalculateFailure implements LossFunction {
    @Override
    public double calcOutput(double givenValue, double targetValue) {
        double diff = targetValue - givenValue;

        // Make every value positive so it can increase the error in any case
        if (diff < 0)
            diff = diff * -1;

        return diff;
    }
}
