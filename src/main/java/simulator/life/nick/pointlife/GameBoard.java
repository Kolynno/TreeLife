package simulator.life.nick.pointlife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JFrame {

    // The world settings
    private final int ROWS = 64;
    private final int COLS = 128;
    private final int CELL_SIZE = 14;

    // The world design
    private final Color BORDER_COLOR = new Color(215, 215, 215);
    private final Color DEFAULT_COLOR = new Color(241, 241, 241);
    private final Color LIFE_COLOR = new Color(149, 252, 84);

    private JButton nextStepButton;
    private JButton removeAllButton;
    private JPanel gamePanel;
    private Cell[][] cells;

    public GameBoard() {
        setTitle("Game Board");
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
       // gamePanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        cells = new Cell[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Cell cell = new Cell();
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
                        calculateLife(i, j);
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

    private void calculateLife(int i, int j) {
        int genome = cells[i][j].getGenome();
        if (genome == 0 && i - 2 >= 0) {
            cells[i - 2][j].setBackground(LIFE_COLOR);
            cells[i - 1][j].setBackground(LIFE_COLOR);
        } else if (genome == 1 && i - 1 >= 0) {
            cells[i - 1][j].setBackground(LIFE_COLOR);
        } else if (genome == 2 && j - 1 >= 0) {
            cells[i][j - 1].setBackground(LIFE_COLOR);
        } else if (genome == 3 && j + 1 < COLS) {
            cells[i][j + 1].setBackground(LIFE_COLOR);
        }

        //JLabel text = new JLabel(String.valueOf(genome));
        //text.setForeground(Color.BLACK); // Установка цвета текста
        //cells[i][j].removeAll(); // Очистка ячейки перед добавлением нового компонента
        //cells[i][j].setLayout(new BorderLayout()); // Установка макета для корректного позиционирования текста
        //cells[i][j].add(text, BorderLayout.CENTER);
        cells[i][j].revalidate(); // Обновление макета ячейки
        cells[i][j].repaint(); // Перерисовка ячейки
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameBoard::new);
    }
}
