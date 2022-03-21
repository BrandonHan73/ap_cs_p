package neural_net;

import java.util.ArrayList;

public class TrainNEAT {
    public final int populationSize;
    private final int[] nodeCounts;
    private ArtificialNeuralNetwork[] networks;

    public TrainNEAT(int popSize, int[] nodeCounts) {
        this.populationSize = popSize;
        this.networks = new ArtificialNeuralNetwork[this.populationSize];
        this.nodeCounts = new int[nodeCounts.length];
        System.arraycopy(nodeCounts, 0, this.nodeCounts, 0, this.nodeCounts.length);
        for(int i = 0; i < this.populationSize; i++) {
            this.networks[i] = new ArtificialNeuralNetwork(this.nodeCounts);
        }
    }

    public TrainNEAT sortNetworks() {
        ArrayList<ArtificialNeuralNetwork> temp = new ArrayList<>();
        temp.add(this.networks[0]);
        for(int i = 1; i < this.populationSize; i++) {
            for(int j = 0; j < temp.size(); j++) {
                if(this.networks[i].getFitness() >= temp.get(j).getFitness()) {
                    temp.add(j, this.networks[i]);
                    break;
                }
            }
            if(temp.size() == i) {
                temp.add(this.networks[i]);
            }
        }
        this.networks = temp.toArray(new ArtificialNeuralNetwork[0]);
        return this;
    }

    public TrainNEAT repopulate() {
        this.sortNetworks();
        ArtificialNeuralNetwork[] temp = new ArtificialNeuralNetwork[this.populationSize];
        System.arraycopy(this.networks, 0, temp, 0, this.populationSize / 3);
        for(int i = this.populationSize / 3; i < 2 * this.populationSize / 3; i++) {
            temp[i] = this.getWeightedRandom().duplicate().mutate();
        }
        for(int i = 2 * this.populationSize / 3; i < this.populationSize; i++) {
            temp[i] = new ArtificialNeuralNetwork(this.nodeCounts);
        }
        this.networks = temp;
        this.sortNetworks();
        return this;
    }

    public ArtificialNeuralNetwork getWeightedRandom() {
        this.sortNetworks();
        double temp = 0;
        for(ArtificialNeuralNetwork ann : this.networks) {
            temp += ann.getFitness();
        }
        temp *= Math.random();
        for(ArtificialNeuralNetwork ann : this.networks) {
            temp -= ann.getFitness();
            if(temp <= 0) {
                return ann;
            }
        }
        return null;
    }

    public ArtificialNeuralNetwork[] getNetworks() {
        ArtificialNeuralNetwork[] temp = new ArtificialNeuralNetwork[this.networks.length];
        System.arraycopy(this.networks, 0, temp, 0, this.networks.length);
        return temp;
    }

    public ArtificialNeuralNetwork getBestNetwork() {
        double highest = 0, temp;
        ArtificialNeuralNetwork best = this.networks[0];
        for(ArtificialNeuralNetwork net : this.networks) {
            temp = net.getFitness();
            if(temp > highest) {
                highest = temp;
                best = net;
            }
        }
        return best;
    }

}
