package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
	private static final String PATH = System.getProperty("user.home")
		            + System.getProperty("file.separator")
		            + SimpleGUIWithFileChooser.class.getSimpleName() + ".txt";
	private final JFrame frame = new JFrame("CONTROLLER WINDOWS");
	private File chosenOne = new File(PATH);
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     *  
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
	public SimpleGUIWithFileChooser() {
		final JPanel p = new JPanel(new BorderLayout());
		final JTextArea tArea = new JTextArea();
		final JButton saveButton = new JButton("Save [Push]");
		p.add(tArea);
		p.add(saveButton, BorderLayout.SOUTH);
		final JPanel p2 = new JPanel(new BorderLayout());
		final JTextField textField = new JTextField(chosenOne.getAbsolutePath());
		textField.setEditable(false);
		final JButton browseButton = new JButton("Browse [Push]");
		final JFileChooser fileChooserButton = new JFileChooser();
		p2.add(textField,BorderLayout.CENTER);
		p2.add(browseButton, BorderLayout.LINE_END);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final String ttt = tArea.getText();
				try (PrintStream ps = new PrintStream(chosenOne.getAbsolutePath())) {
					ps.print(ttt);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch (fileChooserButton.showSaveDialog(browseButton)) {
				case JFileChooser.APPROVE_OPTION:
					chosenOne = fileChooserButton.getSelectedFile();
					textField.setText(chosenOne.getAbsolutePath());
					break;
				case JFileChooser.CANCEL_OPTION:
					break;
				default:
					new JOptionPane().showMessageDialog(p2, "Error");
				}
			}
		});
	p.add(p2, BorderLayout.NORTH);
	final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setContentPane(p);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
	}
	
	public static void main(String...strings) {
	new SimpleGUIWithFileChooser().frame.setVisible(true);
    }
}

