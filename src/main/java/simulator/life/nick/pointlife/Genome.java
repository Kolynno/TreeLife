package simulator.life.nick.pointlife;

import lombok.Getter;
import simulator.life.nick.pointlife.life.Calculate;

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

    public static void doGenome(int i, int j, int currentGenome, int genome, Tree tree, Calculate calculate, Cell[][] cells) {
        switch (currentGenome) {
            case 0,1:
                if (calculate.isLifeless(i - 1, j, cells)) {
                    calculate.setLife(i - 1, j, genome, tree, cells);
                }
                break;
            case 2, 3, 7: calculate.setSeed(i,j, 0, cells[i][j].getTree(), cells);
                break;
            case 4,5:
                if (calculate.isLifeless(i, j + 1, cells)) {
                    calculate.setLife(i, j + 1, genome, tree, cells);;
                }
                break;
            case 6:
                if (calculate.isLifeless(i, j - 1, cells)) {
                    calculate.setLife(i, j - 1, genome, tree, cells);
                }
                break;
        }
    }
}
