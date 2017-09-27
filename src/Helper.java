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
                //logger.trace("Connected node {} to node {}",nodeOne.getID(), nodeTwo.getID());
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

    public double convertBinToDec(Double[] binaryValues)
    {
        double result = 0.0;

        for (int i = 0; i < binaryValues.length; i++) {
            result += binaryValues[i] * Math.pow(2,i);
        }

        return result;
    }

    public double getValueOfOutputLayer(List<Node> outputNodes)
    {
        double result = 0.0;

        for (int i = 0; i < outputNodes.size(); i++) {
            if(outputNodes.get(i).getActivation() > GlobalManager.getInstance().getActiveThreshold())
                result++;
        }

        return result;
    }

    public Double[] getTargetAnswerForDecInput(double targetValue) {
        switch ((int)targetValue)
        {
            case 1:
                return new Double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 2:
                return new Double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 3:
                return new Double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 4:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 5:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 6:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 7:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 8:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 9:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 10:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            case 11:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0};
            case 12:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0};
            case 13:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0};
            case 14:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0};
            case 15:
                return new Double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
            default:
                return new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        }
    }

    public int countValuesGreaterThreshold(List<Node> outputNodes) {
        int counter = 0;

        for(Node node : outputNodes)
        {
            if(node.getActivation() >= GlobalManager.getInstance().getActiveThreshold())
                counter++;
        }

        return counter;
    }

    public int countValuesGreaterThreshold(Double[] targetValues) {
        int counter = 0;

        for(Double value : targetValues)
        {
            if(value >= GlobalManager.getInstance().getActiveThreshold())
                counter++;
        }

        return counter;
    }

    public List<Link> getAllLinks(List<List<Node>> net)
    {
        List<Link> links = new ArrayList<>();

        for(List<Node> layer : net)
        {
            for(Node node : layer)
            {
                links.addAll(node.getOutgoingLinks());
            }
        }

        return links;
    }

    public void logOutputs(List<List<Node>> net){
        List<Node> outputNodes = new Helper().getOutputNodes(net);

        for (Node node : outputNodes)
        {
            logger.info("Node {} : {}",node.getID(), node.getActivation());
        }
    }
}
