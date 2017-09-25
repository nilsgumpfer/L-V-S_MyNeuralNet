import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Helper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void connectLayers(List<Node> layerOne, List<Node> layerTwo)
    {
        for(Node nodeOne : layerOne)
        {
            for(Node nodeTwo : layerTwo)
            {
                nodeTwo.connectLink(nodeOne.pullLink());
                logger.info("Connected node {} to node {}",nodeOne.getID(), nodeTwo.getID());
            }
        }
    }

    public void connectAllLayers(List<List<Node>> layersFromInToOut)
    {
        for(int i = 1; i < layersFromInToOut.size(); i++)
        {
            List<Node> layerOne = layersFromInToOut.get(i-1);
            List<Node> layerTwo = layersFromInToOut.get(i);

            connectLayers(layerOne, layerTwo);
        }
    }

    public List<Node> createLayer(int layerID, int numberOfNodes, GlobalEnum activationFunction)
    {
        List<Node> nodeList = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            nodeList.add(new Node(layerID, i, getActivationFunction(activationFunction)));
        }

        return nodeList;
    }

    public List<List<Node>> createLayersAndConnectThem(int numberOfLayers, LayerProperty... layerProperties) throws IllegalArgumentException {
        List<List<Node>> layerCollection = new ArrayList<>();

        if(numberOfLayers != layerProperties.length) {
            throw new IllegalArgumentException("createLayersAndConnectThem(): You have to pass over a corresponding number of layers and properties");
        }

        for (int i = 0; i < numberOfLayers; i++)
        {
            layerCollection.add(createLayer(i, layerProperties[i].getNumberOfNodes(), layerProperties[i].getActivationFunction()));
        }

        connectAllLayers(layerCollection);

        return layerCollection;
    }

    public ActivationFunction getActivationFunction(GlobalEnum functionCode)
    {
        switch (functionCode) {
            case RELU:
                return new ReLU();
            case SIGMOID:
                return new Sigmoid();
            default:
                return new Sigmoid();
        }
    }

    public List<Node> getInputNodes(List<List<Node>> net)
    {
        return net.get(0);
    }

    public List<Node> getOutputNodes(List<List<Node>> net)
    {
        return net.get(net.size()-1);
    }

    public void processActivation(List<List<Node>> net)
    {
        LayerVisitor layerVisitor = new LayerVisitor();

        //skip first layer
        for (int i = 1; i < net.size(); i++) {
            //activate nodes for each layer / collect values for each node
            layerVisitor.visit(net.get(i));
        }
    }
}
