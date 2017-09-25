public class MainClass {
    public static void main(String[] args)
    {
        init();

        new Helper().createLayersAndConnectThem(4,
                new LayerProperty(4, GlobalEnum.SIGMOID),
                new LayerProperty(4, GlobalEnum.SIGMOID),
                new LayerProperty(4, GlobalEnum.SIGMOID),
                new LayerProperty(4, GlobalEnum.SIGMOID));
    }

    private static void init()
    {
        GlobalManager.getInstance().setRandomWeightRange(0.1,0.2);
    }
}
