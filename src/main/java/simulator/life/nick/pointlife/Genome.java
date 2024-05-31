package simulator.life.nick.pointlife;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

@Getter
public class Genome {

    //Settings
    private static final int GENOME_LENGTH = 8;

    private int[] dna;

    public Genome() {
        dna = setGenome(GENOME_LENGTH);
    }

    private int[] setGenome(int size) {
        int[] genome = new int[size];
        for (int i = 0; i < size; i++) {
            genome[i] = new Random().nextInt(size);
        }
        return genome;
    }

    @Override
    public String toString() {
        String genome = String.format("Genome: %s", Arrays.toString(dna));
        return genome;
    }

    //TODO
    public Genome mutation() {
        Genome newGenome = new Genome();
        for (int i = 0; i < this.dna.length; i++) {
            newGenome.dna[i] = new Random().nextInt(this.dna.length);
        }
        return newGenome;
    }
}
