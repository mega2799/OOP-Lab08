package it.unibo.oop.lab.mvcio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JTextArea textArea = new JTextArea();
    private final JButton button = new JButton("Save [Push]");

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {
        button.addActionListener(e -> {
            new Controller().writeOnFile(this.textArea.getText() + "\n");
        });
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        frame.add(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String... args) {
        new SimpleGUI();
    }
}