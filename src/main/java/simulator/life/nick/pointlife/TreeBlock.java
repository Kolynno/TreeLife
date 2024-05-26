package simulator.life.nick.pointlife;

import javax.swing.*;
import java.awt.*;

class TreeBlock extends JPanel {
    private final int SIZE = 20;

    public TreeBlock() {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setBackground(Color.GREEN.darker().darker());
    }
}
