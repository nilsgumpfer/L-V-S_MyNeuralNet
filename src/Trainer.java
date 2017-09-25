import java.util.List;

public class Trainer {
    private Helper helper = new Helper();
    private List<List<Node>> neuralNet;
    private List<Node> inputNodes;
    private List<Node> outputNodes;
    private double error = 0.0;

    public Trainer(List<List<Node>> neuralNet)
    {
        this.neuralNet = neuralNet;
        inputNodes = helper.getInputNodes(neuralNet);
        outputNodes = helper.getOutputNodes(neuralNet);
    }

    public void trainNet(int nTimes){
        error = 0.0;

        for (int i = 0; i < nTimes; i++) {
            propagate();
            backpropagate();
        }

        //TODO: Set error somewhere that this works.. maybe use global variable!
        //error = error / nTimes;
    }
    public void propagate(){
        for (Node node : inputNodes) {
            node.activate(GlobalManager.getInstance().getRandomOneOrZero());
        }

        new Helper().processActivation(neuralNet);
    }
    public void backpropagate(){}
}
