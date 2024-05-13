package backEnd;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractSystemMenu {
    
	protected JFrame frame;

    public AbstractSystemMenu(String title) {
        frame = new JFrame(title);
        frame.setLayout(new GridLayout(3, 2));
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void addButton(JButton button) {
        frame.add(button);
    }

    protected void showFrame() {
        frame.setVisible(true);
    }

    protected void disposeFrame() {
        frame.dispose();
    }
}
