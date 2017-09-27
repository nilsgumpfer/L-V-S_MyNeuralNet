import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainClass {
    public static void main(String[] args)
    {
        MainClass mainClass = new MainClass();

        mainClass.init();
        mainClass.createNet();
        mainClass.train();

        //mainClass.logOutputs();
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<List<Node>> net;

    private void init()
    {
        GlobalManager.getInstance().setRandomWeightRange(-0.1,0.2);
        GlobalManager.getInstance().setActiveThreshold(0.5);
    }

    private void createNet()
    {
        GlobalEnum activationFunction = GlobalEnum.SIGMOID;
        net = new Helper().createLayersAndConnectThem(3,
                new LayerProperty(4, activationFunction),
                new LayerProperty(6, activationFunction),
                new LayerProperty(15, activationFunction));
    }

    private void train() {
        new Trainer(net, new CountMisses()).trainTillEnd(50);
    }
}
