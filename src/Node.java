import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ActivationFunction activationFunction;
    private double activation = 0.0;
    private double error = 0.0;
    private List<Link> incomingLinks = new ArrayList<>();
    private List<Link> outgoingLinks = new ArrayList<>();
    private String nodeID;


    public Node(int layer, int nodeNumber, ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.nodeID = layer + "-" + nodeNumber;
        //logger.trace("Created node {}", nodeID);
    }

    public void connectLink(Link incoming)
    {
        incoming.setEndNode(this);
        incomingLinks.add(incoming);
    }

    public Link pullLink()
    {
        Link link = new Link(this);
        outgoingLinks.add(link);

        return link;
    }

    public String getID() {
        return nodeID;
    }

    public void activate(double input) {
        activation = activationFunction.calcOutput(input);
        //logger.trace("Node {} activated: input {} activation {}",nodeID, input, activation);
    }

    public double getActivation(){
        return activation;
    }

    public List<Link> getIncomingLinks() {
        return incomingLinks;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public List<Link> getOutgoingLinks() {
        return outgoingLinks;
    }
}
