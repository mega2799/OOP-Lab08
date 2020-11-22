package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Just Another SimpleGUI");
    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {
	final Controller ctl = new UseController();
	    final JPanel mainPanel = new JPanel(new BorderLayout());
	    // create and add a tField
	    final JTextField textField = new JTextField("Insert the Text below....");
	    textField.setEditable(false);
	    mainPanel.add(textField, BorderLayout.NORTH);
	    // create and add tArea 
	    final JTextArea textArea = new JTextArea();
	    mainPanel.add(textArea, BorderLayout.CENTER);
	    // create 2 button to insert in panel 
	    final JButton historyButton = new JButton("Show history [Push]");
	    final JButton printButton = new JButton("Print [Push]");
	    final JPanel buttonPanel = new JPanel();
	    buttonPanel.add(printButton);
	    buttonPanel.add(historyButton);
	    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	    // print Button handler 
	    printButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				ctl.setNextString(textArea.getText());
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
			ctl.printCurrentOnStdOut();
			textArea.setText("");
		}
	    });
	   // show history button Handler 
	   historyButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ctl.getHistory().forEach(a -> System.out.println(a));
		}
	   });
	 /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }
    public static void main(final String... string) {
	    new SimpleGUI().frame.setVisible(true);
    }
}
