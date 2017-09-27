import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainClass {
    public static void main(String[] args)
    {
        MainClass mainClass = new MainClass();

        mainClass.init();
        mainClass.createNet();

        for (int i = 0; i < 100; i++) {
            mainClass.train(10);
        }

        //mainClass.logOutputs();
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<List<Node>> net;

    private void init()
    {
        GlobalManager.getInstance().setRandomWeightRange(-0.1,0.2);
    }

    private void createNet()
    {
        net = new Helper().createLayersAndConnectThem(3,
                new LayerProperty(4, GlobalEnum.SIGMOID),
                new LayerProperty(6, GlobalEnum.SIGMOID),
                new LayerProperty(15, GlobalEnum.SIGMOID));
    }

    private void train(int nTimes) {
        new Trainer(net).trainNet(nTimes);
    }
}
