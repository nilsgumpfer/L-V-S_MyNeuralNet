package net;

import globals.GlobalEnum;

public class LayerProperty {
    private int numberOfNodes;
    private GlobalEnum activationFunction;

    public LayerProperty(int numberOfNodes, GlobalEnum activationFunction) {
        this.numberOfNodes = numberOfNodes;
        this.activationFunction = activationFunction;
    }

    public GlobalEnum getActivationFunction() {
        return activationFunction;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }
}
