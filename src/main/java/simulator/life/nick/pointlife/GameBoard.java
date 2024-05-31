package simulator.life.nick.pointlife;

import simulator.life.nick.pointlife.life.Calculate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static simulator.life.nick.pointlife.Tree.removeTree;

public class GameBoard extends JFrame {

    // The world settings
    // Standard:
    private final int ROWS = 32;
    private final int COLS = 32;
    private final int CELL_SIZE = 28;

    //Vars
    private static final int ENERGY_PER_CELL = 10;

    // The world design
    public static final Color BORDER_COLOR = new Color(215, 215, 215);
    public static final Color DEFAULT_COLOR = new Color(241, 241, 241);
    public static final Color LIFE_COLOR = new Color(149, 252, 84);
    public static final Color SEED_COLOR = new Color(255, 255, 255);

    //UI
    private JButton nextStepButton;
    private JButton removeAllButton;
    private JPanel gamePanel;

    //Data
    private ArrayList<Tree> trees;
    private Cell[][] cells;
    private final Calculate calculate = new Calculate();

    public GameBoard() {
        setTitle("Tree Life v.0.0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createGamePanel();
        createButtons();

        add(gamePanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextStepButton);
        buttonPanel.add(removeAllButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createGamePanel() {
        gamePanel = new JPanel(new GridLayout(ROWS, COLS));
        cells = new Cell[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Cell cell = new Cell(null, -1);
                cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                cell.setBackground(DEFAULT_COLOR);
                cell.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
                cell.addMouseListener(new CellClickListener());
                cells[i][j] = cell;
                gamePanel.add(cell);
            }
        }
    }

    private void createButtons() {
        nextStepButton = new JButton("Step");
        removeAllButton = new JButton("Remove all");
        nextStepButton.addActionListener(new NextStepButton());
        removeAllButton.addActionListener(new RemoveAllButton());
    }

    private class CellClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Cell cell = (Cell) e.getSource();
            if (cell.getBackground().equals(DEFAULT_COLOR)) {
                cell.setBackground(LIFE_COLOR);
                Tree tree = new Tree();
                cell.setTree(tree);
                cell.setCurrentGenome(0);
                //trees.add(tree);
                System.out.println(tree);
                //TreeBlock treeBlock = new TreeBlock();
                //cell.add(treeBlock);
            }
        }
    }

    private class NextStepButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (cells[i][j].getBackground() == LIFE_COLOR) {
                        cells[i][j].getTree().setEnergy(cells[i][j].getTree().getEnergy() - ENERGY_PER_CELL);
                        if (cells[i][j].isActive()) {
                            if (cells[i][j].getTree().getEnergy() > 0) {
                                calculate.calculateLife(i, j, cells, ROWS, COLS);
                            } else {
                                removeTree(cells[i][j].getTree(), ROWS, COLS, cells);
                            }
                        } else {
                            if (cells[i][j].getTree().getEnergy() < 0) {
                                removeTree(cells[i][j].getTree(), ROWS, COLS, cells);
                            }
                        }
                    } else if (cells[i][j].getBackground() == SEED_COLOR) {
                        calculate.calculateSeed(i,j, cells, ROWS, COLS);
                    }

                }
            }
        }
    }

    private class RemoveAllButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if(cells[i][j].getBackground() != DEFAULT_COLOR) {
                        cells[i][j].unlifeCell();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameBoard::new);
    }
}
