import java.util.List;

public class LayerVisitor {
    public void visit(List<Node> layer) {
        for(Node node : layer)
        {
            double tmpNodeActivation = 0.0;

            for(Link incomingLink : node.getIncomingLinks())
            {
                tmpNodeActivation += incomingLink.getStartNode().getActivation() * incomingLink.getWeight();
            }

            node.activate(tmpNodeActivation);
        }
    }

}
