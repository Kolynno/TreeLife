package simulator.life.nick.pointlife.life;

import simulator.life.nick.pointlife.Cell;
import simulator.life.nick.pointlife.Genome;
import simulator.life.nick.pointlife.Tree;

import javax.swing.*;
import java.awt.*;

import static simulator.life.nick.pointlife.GameBoard.*;
import static simulator.life.nick.pointlife.Tree.removeTree;

public class Calculate {

    public void calculateSeed(int i, int j, Cell[][] cells, int ROWS, int COLS) {
        if (cells[i][j].getTree().getEnergy() > 0) {
            if (isLifeless(i+1, j, cells)) {
                cells[i+1][j].setCurrentGenome(cells[i][j].getCurrentGenome());
                cells[i+1][j].setBackground(SEED_COLOR);
                cells[i+1][j].setTree(cells[i][j].getTree());
                cells[i][j].setActive(true);
                cells[i][j].setBackground(DEFAULT_COLOR);
                cells[i][j].removeAll();
            } else if (isBottom(i, ROWS)) {
                cells[i][j].setBackground(LIFE_COLOR);
                cells[i][j].setActive(true);
                //trees.add(tree);
            }
        } else {
            removeTree(cells[i][j].getTree(), ROWS, COLS, cells);
        }
    }

    public void calculateLife(int i, int j, Cell[][] cells, int ROWS, int COLS) {
        if (cells[i][j].getTree().getEnergy() < 0) {
            removeTree(cells[i][j].getTree(), ROWS, COLS, cells);
        } else {
            cells[i][j].setActive(false);
            int currentGenome = cells[i][j].getCurrentGenome();
            Tree tree = cells[i][j].getTree();
            int genome = tree.getGenome().getDna()[currentGenome];
            Genome.doGenome(i, j, currentGenome, genome, tree, this, cells);
            JLabel text = new JLabel(String.valueOf(currentGenome));
            text.setForeground(Color.BLACK); // Установка цвета текста
            cells[i][j].removeAll(); // Очистка ячейки перед добавлением нового компонента
            cells[i][j].setLayout(new BorderLayout()); // Установка макета для корректного позиционирования текста
            cells[i][j].add(text, BorderLayout.CENTER);
            cells[i][j].revalidate(); // Обновление макета ячейки
            cells[i][j].repaint(); // Перерисовка ячейки
        }
    }


    public boolean isLifeless(int i, int j, Cell[][] cells) {
        try {
            return cells[i][j].getBackground().equals(DEFAULT_COLOR);
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean isBottom(int i, int ROWS) {
        return i == ROWS - 1;
    }

    public void setSeed(int i, int j, int currentGenome, Tree parentTree, Cell[][] cells) {
        cells[i][j].setBackground(SEED_COLOR);
        cells[i][j].setCurrentGenome(currentGenome);
        cells[i][j].setTree(new Tree(parentTree));
    }

    public void setLife(int i, int j, int genome, Tree tree, Cell[][] cells) {
        cells[i][j].setBackground(LIFE_COLOR);
        cells[i][j].setCurrentGenome(genome);
        cells[i][j].setTree(tree);
    }

}
