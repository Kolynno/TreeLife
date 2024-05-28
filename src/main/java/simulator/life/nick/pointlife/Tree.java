package simulator.life.nick.pointlife;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tree {
    //Tree init settings
    private int stepsLeft = 90;
    private int energy = 300;

    //Base
    private final int id;
    private final Genome genome;

    //Extra
    private Tree treeParent;

    private static int count = 0;

    public Tree() {
        count++;
        this.id = count;
        this.genome = new Genome();
    }

    public Tree(Tree treeParent) {
        count++;
        this.id = count;
        Genome newGenome = Genome.modify(treeParent.getGenome());
        this.genome = newGenome;
    }

    @Override
    public String toString() {
        return "Tree " + id + ", life=" + stepsLeft + ", energy=" + energy +  ", genome=" + genome;
    }
}
