package simulator.life.nick.pointlife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameBoard extends JFrame {

    // The world settings
    // Standard:
    private final int ROWS = 32;
    private final int COLS = 32;
    private final int CELL_SIZE = 28;

    // The world design
    private final Color BORDER_COLOR = new Color(215, 215, 215);
    private final Color DEFAULT_COLOR = new Color(241, 241, 241);
    private final Color LIFE_COLOR = new Color(149, 252, 84);

    //UI
    private JButton nextStepButton;
    private JButton removeAllButton;
    private JPanel gamePanel;

    //Data
    private ArrayList<Tree> trees;
    private Cell[][] cells;

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
                        cells[i][j].getTree().setEnergy(cells[i][j].getTree().getEnergy() - 10);
                        System.out.println(cells[i][j].getTree().toString());
                        if (cells[i][j].isActive()) {
                            calculateLife(i, j);
                        }
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
                    if(cells[i][j].getBackground() == LIFE_COLOR) {
                        cells[i][j].setBackground(DEFAULT_COLOR);
                        cells[i][j].removeAll();
                        cells[i][j].revalidate();
                        cells[i][j].repaint();
                    }
                }
            }
        }
    }


    private void removeTree(Tree tree) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(cells[i][j].getTree() == tree ) {
                    cells[i][j].setBackground(DEFAULT_COLOR);
                    cells[i][j].removeAll();
                    cells[i][j].revalidate();
                    cells[i][j].repaint();
                }
            }
        }
    }


    private void calculateLife(int i, int j) {
        if (cells[i][j].getTree().getEnergy() < 0) {
            removeTree(cells[i][j].getTree());
        } else {
            cells[i][j].setActive(false);
            int currentGenome = cells[i][j].getCurrentGenome();
            Tree tree = cells[i][j].getTree();
            int genome = tree.getGenome().getDna()[currentGenome];
            doGenome(i, j, currentGenome, genome, tree);
            JLabel text = new JLabel(String.valueOf(currentGenome));
            text.setForeground(Color.BLACK); // Установка цвета текста
            cells[i][j].removeAll(); // Очистка ячейки перед добавлением нового компонента
            cells[i][j].setLayout(new BorderLayout()); // Установка макета для корректного позиционирования текста
            cells[i][j].add(text, BorderLayout.CENTER);
            cells[i][j].revalidate(); // Обновление макета ячейки
            cells[i][j].repaint(); // Перерисовка ячейки
        }
    }


    //check game border
    private void doGenome(int i, int j, int currentGenome, int genome, Tree tree) {
        switch (currentGenome) {
            case 0,1,2,3,4:
                if (isLifeless(i - 1, j)) {
                    setLife(i - 1, j, genome, tree);
                }
                break;
            case 5: if (isLifeless(i, j + 1)) {
                setLife(i, j + 1, genome, tree);
            }
            case 6: if (isLifeless(i, j - 1)) {
                setLife(i, j - 1, genome, tree);
            }
            break;
            case 7: if (isLifeless(i - 1, j) && isLifeless(i - 2, j)) {
                    setLife(i - 1, j, genome, tree);
                    setLife(i - 2, j, genome, tree);
                    break;
                }
        }
    }

    private void setLife(int i, int j, int genome, Tree tree) {
        cells[i][j].setBackground(LIFE_COLOR);
        cells[i][j].setCurrentGenome(genome);
        cells[i][j].setTree(tree);
    }

    private boolean isLifeless(int i, int j) {
        try {
            return !cells[i][j].getBackground().equals(LIFE_COLOR);
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameBoard::new);
    }
}
