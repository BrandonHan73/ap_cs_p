package neural_net;

import config.Config;
import main.Main;

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

        /**/
        for(int i = 0; i < populationSize; i++) {
            Main.dataLabel[i].setText(String.format("%.2f", this.networks[i].getFitness()));
        }
        /**/

        ArtificialNeuralNetwork[] temp = new ArtificialNeuralNetwork[this.populationSize];
        temp[0] = this.getBestNetwork();
        temp[1] = temp[0].duplicate().mutate(Config.ANN_LOW_MUTATION_RATE);
        for(int i = 1; i < this.populationSize / 8; i++) {
            temp[i] = temp[0].duplicate().mutate(Config.ANN_LOW_MUTATION_RATE);
        }
        for(int i = this.populationSize / 8; i < 2 * this.populationSize / 3; i++) {
            temp[i] = this.getWeightedRandom().duplicate().mutate();
        }
        for(int i = 2 * this.populationSize / 3; i < this.populationSize; i++) {
            temp[i] = new ArtificialNeuralNetwork(this.nodeCounts);
        }
        this.networks = temp;
        this.sortNetworks();

        /**/
        for(int i = 0; i < populationSize; i++) {
            Main.dataLabel2[i].setText(String.format("%.2f", this.networks[i].getFitness()));
        }
        /**/

        return this;
    }

    public TrainNEAT cleanseFitness() {
        for(ArtificialNeuralNetwork network : this.networks) {
            network.setFitness(0);
        }
        return this;
    }

    public ArtificialNeuralNetwork getWeightedRandom() {
        double temp = 0;
        ArtificialNeuralNetwork best = this.getBestNetwork();
        for(ArtificialNeuralNetwork ann : this.networks) {
            temp += ann.getFitness();
        }
        temp /= this.populationSize;
        ArrayList<ArtificialNeuralNetwork> options = new ArrayList<>();
        for(ArtificialNeuralNetwork ann : this.networks) {
            if(ann.getFitness() >= temp && ann.getFitness() > best.getFitness() / 2) {
                options.add(ann);
            }
        }
        temp = 0;
        for(ArtificialNeuralNetwork ann : options) {
            temp += Math.pow(ann.getFitness(), Config.ANN_STANDARD_FITNESS_BLOAT_RATE);
        }
        temp *= Math.random();
        for(ArtificialNeuralNetwork ann : options) {
            temp -= Math.pow(ann.getFitness(), Config.ANN_STANDARD_FITNESS_BLOAT_RATE);
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
