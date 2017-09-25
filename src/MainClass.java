import java.util.List;

public class MainClass {
    public static void main(String[] args)
    {
        MainClass mainClass = new MainClass();

        mainClass.init();
        mainClass.createNet();
        mainClass.train();
    }

    private void train() {
        new Trainer(net).trainNet(1);
    }

    private List<List<Node>> net;

    private void init()
    {
        GlobalManager.getInstance().setRandomWeightRange(0.1,0.2);
    }

    private void createNet()
    {
        net = new Helper().createLayersAndConnectThem(3,
                new LayerProperty(4, GlobalEnum.SIGMOID),
                new LayerProperty(6, GlobalEnum.SIGMOID),
                new LayerProperty(15, GlobalEnum.SIGMOID));
    }
}
