import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ActivationFunction activationFunction;
    private double error = 0.0;
    private List<Link> incomingLinks = new ArrayList<>();
    private List<Link> outgoingLinks = new ArrayList<>();
    private String nodeID;


    public Node(int layer, int nodeNumber, ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        this.nodeID = layer + "." + nodeNumber;
        logger.info("Created node {}", nodeID);
    }

    public void connectLink(Link incoming)
    {
        incoming.setEndNode(this);
        incomingLinks.add(incoming);
    }

    public Link pullLink()
    {
        return new Link(this);
    }

    public String getID() {
        return nodeID;
    }
}
