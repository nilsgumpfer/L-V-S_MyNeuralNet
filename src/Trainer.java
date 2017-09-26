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
            logger.info("Training {}/{} started",i,nTimes);
            activate();
            propagate();
            backpropagate();
        }

        error = error / nTimes;

        logger.info("Trainings ended. Error: {}",error);
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

        for (int i = 0; i < outputNodes.size(); i++)
        {
            Node currentNode = outputNodes.get(i);
            double nodeError = currentNode.getActivation() * (1 - currentNode.getActivation()) * (targetAnswer[0] - currentNode.getActivation());

            currentNode.setError(nodeError);
        }

        spreadBackErrorToNodes();
        calculateAndAssignNewWeights();

        // Compare target-answer and given answer. If number of active nodes differs, increase error
        if(helper.countValuesGreaterThreshold(givenAnswer) != helper.countValuesGreaterThreshold(targetAnswer))
            error++;

        logger.info("target: {} given: {} error: {}",targetValue, givenValue, error);
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
