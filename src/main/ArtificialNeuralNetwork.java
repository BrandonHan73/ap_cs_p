package main;

public class ArtificialNeuralNetwork {

    public final int inputs;
    private int[] nodeCounts;
    private Matrix[] weights, biases;

    public ArtificialNeuralNetwork(int[] nodeCounts) {
        this.inputs = nodeCounts[0];
        this.nodeCounts = new int[nodeCounts.length];
        System.arraycopy(nodeCounts, 0, this.nodeCounts, 0, this.nodeCounts.length);
        this.weights = new Matrix[this.nodeCounts.length - 1];
        this.biases = new Matrix[this.nodeCounts.length - 1];
        for(int i = 0; i < this.nodeCounts.length - 1; i++) {
            this.weights[i] = new Matrix(this.nodeCounts[i + 1], this.nodeCounts[i]);
            this.weights[i].randomize(Config.ANN_INITIAL_RANDOM_MIN, Config.ANN_INITIAL_RANDOM_MAX);
            this.biases[i] = new Matrix(this.nodeCounts[i + 1], this.nodeCounts[i]);
            this.biases[i].randomize(Config.ANN_INITIAL_RANDOM_MIN, Config.ANN_INITIAL_RANDOM_MAX);
        }
    }

}
