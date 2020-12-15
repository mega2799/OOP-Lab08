package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private final JFrame frame = new JFrame("CONTROLLER WINDOWS");
    Controller c = new Controller();
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JPanel textPanel = new JPanel(new BorderLayout());
    private final JTextField text = new JTextField(c.getFile().getAbsolutePath());
    private final JButton saveButton = new JButton("Save [push]");
    private final JButton browseButton = new JButton("Browse [push]");

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface. Suggestion: use a second JPanel with a second
     * BorderLayout, put the panel in the North of the main panel, put the text
     * field in the center of the new panel and put the button in the line_end of
     * the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the current
     * selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should use
     * the method showSaveDialog() to display the file chooser, and if the result is
     * equal to JFileChooser.APPROVE_OPTION the program should set as new file in
     * the Controller the file chosen. If CANCEL_OPTION is returned, then the
     * program should do nothing. Otherwise, a message dialog should be shown
     * telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to update
     * the UI: in this example the UI knows when should be updated, so try to keep
     * things separated.
     */
    public SimpleGUIWithFileChooser() {
        text.setEditable(false);
        textPanel.add(text, BorderLayout.CENTER);
        textPanel.add(browseButton, BorderLayout.LINE_END);
        panel.add(textPanel, BorderLayout.NORTH);
        panel.add(saveButton, BorderLayout.SOUTH);
        browseButton.addActionListener(e -> {
            JFileChooser j = new JFileChooser();
            final int res = j.showSaveDialog(panel);
            if (res == j.APPROVE_OPTION) {
                c.setFile(j.getSelectedFile());
                text.setText(c.getFile().getAbsolutePath());
            } else if (res != j.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(panel, "Error");
            }
        });
        saveButton.addActionListener(e -> {
            c.writeOnFile(text.getText());
        });
        frame.add(panel);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this flag
         * makes the OS window manager take care of the default positioning on screen.
         * Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... strings) {
        new SimpleGUIWithFileChooser();
    }
}
