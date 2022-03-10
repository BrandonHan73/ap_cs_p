package main;

public class ArtificialNeuralNetwork {

    public final int inputCount, layers;
    private int[] nodeCounts;
    private Matrix[] weights, biases;

    public ArtificialNeuralNetwork(int[] nodeCounts) {
        this.inputCount = nodeCounts[0];
        this.layers = nodeCounts.length - 1;
        this.nodeCounts = new int[nodeCounts.length];
        System.arraycopy(nodeCounts, 0, this.nodeCounts, 0, this.nodeCounts.length);
        this.weights = new Matrix[this.layers];
        this.biases = new Matrix[this.layers];
        for(int i = 0; i < this.layers; i++) {
            this.weights[i] = new Matrix(this.nodeCounts[i + 1], this.nodeCounts[i]);
            this.weights[i].randomize(Config.ANN_INITIAL_RANDOM_MIN, Config.ANN_INITIAL_RANDOM_MAX);
            this.biases[i] = new Matrix(this.nodeCounts[i + 1], 1);
            this.biases[i].randomize(Config.ANN_INITIAL_RANDOM_MIN, Config.ANN_INITIAL_RANDOM_MAX);
        }
    }

    public double[] pass(double[] inputs) {
        assert inputs.length == this.inputCount;
        Vector temp = new Vector(this.inputCount).set(inputs);
        for(int l = 0; l < this.layers; l++) {
            temp = Vector.matrixToVector(
                    Matrix.add(
                            Matrix.transpose(Matrix.matrixMultiply(weights[l], temp)),
                            this.biases[l]
                    )
            );
        }
        return temp.toArray();
    }

}
