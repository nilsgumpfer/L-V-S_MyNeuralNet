package net;

import globals.GlobalManager;

public class Link {
    private double weight;
    private Node startNode;
    private Node endNode;

    public Link(double weight, Node startNode, Node endNode) {
        this.weight = weight;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public Link(Node startNode) {
        this.weight = GlobalManager.getInstance().getRandomWeight();
        this.startNode = startNode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if(weight.isNaN())
            this.weight = GlobalManager.getInstance().getRandomWeight(); //TODO: sth is wrong here with NaN stuff
        else
            this.weight = weight;
    }

    public Node getEndNode() {
        return endNode;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setEndNode(Node endNode)
    {
        this.endNode = endNode;
    }
}
