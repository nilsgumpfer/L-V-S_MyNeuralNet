import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LayerVisitor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void visit(List<Node> layer) {
        for(Node node : layer)
        {
            double tmpNodeActivation = 0.0;

            for(Link incomingLink : node.getIncomingLinks())
            {
                double act = incomingLink.getStartNode().getActivation();
                double weight = incomingLink.getWeight();

                tmpNodeActivation += act * weight;

                //logger.trace("a {} w {} -> r {}",act, weight, tmpNodeActivation);
            }

            node.activate(tmpNodeActivation);
        }
    }

}
