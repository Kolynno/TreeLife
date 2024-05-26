package simulator.life.nick.pointlife;

import javax.swing.*;
import java.util.Random;

public class Cell extends JPanel {
    private int genome = new Random().nextInt(4);

    public int getGenome() {
        return genome;
    }
}
