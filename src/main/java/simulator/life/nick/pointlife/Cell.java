package simulator.life.nick.pointlife;

import javax.swing.*;

public class Cell extends JPanel {
    private int currentGenome;
    private Tree tree;
    private boolean isActive = true;

    public Cell(Tree tree, int currentGenome) {
        this.tree = tree;
        this.currentGenome = currentGenome;
    }

    public int getCurrentGenome() {
        return currentGenome;
    }

    public void setCurrentGenome(int currentGenome) {
        this.currentGenome = currentGenome;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
