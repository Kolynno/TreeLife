package simulator.life.nick.pointlife;


import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class Cell extends JPanel {

    private int currentGenome;
    private Tree tree;
    private boolean isActive = true;

    public Cell(Tree tree, int currentGenome) {
        this.tree = tree;
        this.currentGenome = currentGenome;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
