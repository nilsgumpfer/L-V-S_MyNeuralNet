package globals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GlobalManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static GlobalManager myInstance = new GlobalManager();
    private double randRangeLow;
    private double randRangeHigh;
    private double globalError = 0.0;
    private double activeThreshold = 0.5;
    private List<Double> errorHistory = new ArrayList<>();
    private double learningRate = 1.0;

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
        double rand;

        // Ensure only values slightly larger or less than 0 are taken (improves learning)
        do{
            rand = ThreadLocalRandom.current().nextDouble(randRangeLow, randRangeHigh);
        }while(rand < 0.005 && rand > -0.005 );

        //logger.info("{}",rand);

        return rand;
    }

    public double getRandomOneOrZero()
    {
        int number = (int) (Math.random() * 10000000);

        if(number % 2 == 0)
            return 1;
        else
            return 0;
    }

    public double getActiveThreshold() {
        return activeThreshold;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setActiveThreshold(double activeThreshold) {
        this.activeThreshold = activeThreshold;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
