package simulator.life.nick.pointlife;

import java.util.Arrays;
import java.util.Random;

public class Genome {
    private int[] dna;

    public Genome() {
        dna = setGenome(8);
    }

    private int[] setGenome(int size) {
        int[] genome = new int[size];
        for (int i = 0; i < size; i++) {
            genome[i] = new Random().nextInt(size);
        }
        return genome;
    }

    public int[] getDna() {
        return dna;
    }

    @Override
    public String toString() {
        String genome = String.format("Genome: %s", Arrays.toString(dna));
        return genome;
    }
}
