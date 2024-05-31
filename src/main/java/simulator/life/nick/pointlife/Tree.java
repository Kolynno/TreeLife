package simulator.life.nick.pointlife;

import lombok.Getter;
import lombok.Setter;

import static simulator.life.nick.pointlife.GameBoard.DEFAULT_COLOR;

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
        this.genome = treeParent.getGenome().mutation();
    }

    @Override
    public String toString() {
        return "Tree " + id + ", life=" + stepsLeft + ", energy=" + energy +  ", genome=" + genome;
    }

    public static void removeTree(Tree tree, int ROWS, int COLS, Cell[][] cells) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(cells[i][j].getTree() == tree && !cells[i][j].getBackground().equals(DEFAULT_COLOR) ) {
                    cells[i][j].unlifeCell();
                }
            }
        }
    }
}
