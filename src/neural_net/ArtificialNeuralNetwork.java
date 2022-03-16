package neural_net;

import config.Config;
import math.Function;
import math.Matrix;
import math.Vector;

public class ArtificialNeuralNetwork {
    private double fitness;
    public double getFitness() {
        return this.accessFitness(false, 0);
    }
    public ArtificialNeuralNetwork setFitness(double value) {
        this.accessFitness(true, value);
        return this;
    }
    private synchronized double accessFitness(boolean edit, double value) {
        if(edit) {
            this.fitness = Math.max(0, value);
            return 0;
        } else {
            return this.fitness;
        }
    }

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
            temp = Vector.matrixToVector(Matrix.add(
                    Matrix.transpose(Matrix.matrixMultiply(weights[l], temp)),
                    this.biases[l]
            ).passFunction(Function.sigmoid));
        }
        return temp.toArray();
    }

    public ArtificialNeuralNetwork mutate(double factor) {
        for(Matrix m : this.weights) {
            m.multiply(1 - Function.randomize.pass(factor));
        }
        for(Matrix m : this.biases) {
            m.multiply(1 - Function.randomize.pass(factor));
        }
        return this;
    }
    public ArtificialNeuralNetwork mutate() {
        for(Matrix m : this.weights) {
            m.multiply(1 - Function.randomize.pass(Config.ANN_STANDARD_MUTATION_RATE));
        }
        for(Matrix m : this.biases) {
            m.multiply(1 - Function.randomize.pass(Config.ANN_STANDARD_MUTATION_RATE));
        }
        return this;
    }

}
