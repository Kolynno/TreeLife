package simulator.life.nick.pointlife;

public class Tree {
    private int stepsLeft = 90;
    private int energy = 300;
    private final int id;
    private final Genome genome;
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
        this.genome = treeParent.getGenome();
    }

    public int getStepsLeft() {
        return stepsLeft;
    }

    public int getEnergy() {
        return energy;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public Genome getGenome() {
        return genome;
    }

    public void setStepsLeft(int stepsLeft) {
        this.stepsLeft = stepsLeft;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public static void setCount(int count) {
        Tree.count = count;
    }

    public Tree getTreeParent() {
        return treeParent;
    }

    public void setTreeParent(Tree treeParent) {
        this.treeParent = treeParent;
    }

    @Override
    public String toString() {
        return "Tree:" + " id=" + id + ", stepsLeft=" + stepsLeft + ", energy=" + energy +  ", genome=" + genome;
    }
}
