import functions.loss.CountMisses;
import globals.GlobalEnum;
import globals.GlobalManager;
import globals.Helper;
import globals.Trainer;
import net.LayerProperty;
import net.Node;

import java.util.List;

public class MainClass {

    private List<List<Node>> net;
    private Trainer trainer;


    public static void main(String[] args)
    {
        MainClass mainClass = new MainClass();

        mainClass.init();
        mainClass.train();
        mainClass.test();
    }

    private void test() {
        trainer.test(net, 0.0,1.0,0.0,1.0);
    }

    private void init()
    {
        createNet_var1(); // fastest variant till now
        //createNet_var2(); // quite fast in the beginning, but stuck

        trainer = new Trainer(net, new CountMisses());
    }

    private void createNet_var1()
    {
        GlobalManager.getInstance().setRandomWeightRange(-0.2,0.2);
        GlobalManager.getInstance().setActiveThreshold(0.5);

        GlobalEnum activationFunction = GlobalEnum.SIGMOID;
        net = new Helper().createLayersAndConnectThem(
                new LayerProperty(4, activationFunction),
                new LayerProperty(8, activationFunction),
                new LayerProperty(16, activationFunction),
                new LayerProperty(15, activationFunction)
        );
    }

    private void createNet_var2()
    {
        GlobalManager.getInstance().setRandomWeightRange(-0.212,0);
        GlobalManager.getInstance().setActiveThreshold(0.6);

        GlobalEnum activationFunction = GlobalEnum.ARCTAN;
        net = new Helper().createLayersAndConnectThem(
                new LayerProperty(4, activationFunction),
                new LayerProperty(8, activationFunction),
                //new LayerProperty(16, activationFunction),
                new LayerProperty(15, activationFunction)
        );
    }

    private void train() {
        trainer.trainTillEnd(50);
    }
}
