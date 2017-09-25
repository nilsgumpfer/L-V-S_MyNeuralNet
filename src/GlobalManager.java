import java.util.concurrent.ThreadLocalRandom;

public class GlobalManager {
    private static GlobalManager myInstance = new GlobalManager();
    private double randRangeLow;
    private double randRangeHigh;

    private GlobalManager(){}

    public static GlobalManager getInstance()
    {
        return myInstance;
    }

    public void setRandomWeightRange(double low, double high)
    {
        randRangeHigh = high;
        randRangeLow = low;
    }
    public double getRandomWeight(){
        return ThreadLocalRandom.current().nextDouble(randRangeLow, randRangeHigh);
    }
}
