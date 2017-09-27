import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Helper helper = new Helper();
    private List<List<Node>> neuralNet;
    private List<Node> inputNodes;
    private List<Node> outputNodes;
    private Double[] inputValues;
    private double error;


    public Trainer(List<List<Node>> neuralNet)
    {
        this.neuralNet = neuralNet;
        inputNodes = helper.getInputNodes(neuralNet);
        outputNodes = helper.getOutputNodes(neuralNet);
    }

    private void init(){
        error = 0.0;
        inputValues = new Double[]{};
    }

    public void trainNet(int nTimes){
        init();

        for (int i = 0; i < nTimes; i++) {
            logger.trace("Training {}/{} started",i,nTimes);
            activate();
            propagate();
            backpropagate();
            //helper.logOutputs(neuralNet);
        }

        error = error / nTimes;

        logger.info("Error: {}%",(int)(error*100));
    }
    public void propagate(){
        new Helper().processActivation(neuralNet);
    }
    public void backpropagate() throws IllegalArgumentException
    {
        double targetValue = helper.convertBinToDec(inputValues);
        double givenValue = helper.getValueOfOutputLayer(outputNodes);

        Double[] targetAnswer = helper.getTargetAnswerForDecInput(targetValue);
        Double[] givenAnswer = helper.getTargetAnswerForDecInput(givenValue);

        if(targetAnswer.length != outputNodes.size())
            throw new IllegalArgumentException("backpropagate(): A corresponding number of output-nodes and target-answers is not given. Please have a look at your developments.");

        calculateNodeErrors(targetAnswer);
        spreadBackErrorsThroughNet();
        calculateAndAssignNewWeights();
        recalculateGlobalError(targetAnswer, givenAnswer);

        logger.trace("target: {} given: {} error: {}",targetValue, givenValue, error);
    }

    private void recalculateGlobalError(Double[] targetAnswer, Double[] givenAnswer)
    {
        // Compare target-answer and given answer. If number of active nodes differs, increase error
        if(helper.countValuesGreaterThreshold(givenAnswer) != helper.countValuesGreaterThreshold(targetAnswer))
            error++;
    }

    private void calculateNodeErrors(Double[] targetAnswer)
    {
        for (int i = 0; i < outputNodes.size(); i++)
        {
            Node currentNode = outputNodes.get(i);
            double nodeError = currentNode.getActivation() * (1 - currentNode.getActivation()) * (targetAnswer[0] - currentNode.getActivation());

            currentNode.setError(nodeError);
        }
    }

    private void calculateAndAssignNewWeights() {
        List<Link> allLinks = helper.getAllLinks(neuralNet);

        for(Link link : allLinks)
        {
            double newWeight = link.getWeight() + GlobalManager.getInstance().getLearningRate() * link.getEndNode().getError() * link.getStartNode().getActivation();
            link.setWeight(newWeight);
        }

    }

    private void spreadBackErrorsThroughNet()
    {
        // Run backwards through all hidden layers, skip input and output layers
        for (int i = neuralNet.size() - 2; i > 1 ; i--)
        {
            List<Node> layer = neuralNet.get(i);

            for(Node node : layer)
            {
                double collectedErrors = 0.0;

                // Sum up weighted errors of nodes one layer behind
                for(Link link : node.getOutgoingLinks())
                {
                    collectedErrors += link.getWeight() * link.getEndNode().getError();
                }

                double tmpError = node.getActivation() * (1 - node.getActivation()) * collectedErrors;

                node.setError(tmpError);
            }
        }
    }

    public void activate(){
        List<Double> values = new ArrayList<>();

        for (Node node : inputNodes) {
            double value = GlobalManager.getInstance().getRandomOneOrZero();
            node.activate(value);
            values.add(value);
        }

        inputValues = values.toArray(new Double[]{});
    }
}
