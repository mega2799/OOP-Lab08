package it.unibo.oop.lab.iogui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// import jdk.internal.jline.internal.InputStreamReader;

/**
 * This class is a simple application that writes a random number on a file.
 * 
 * This application does not exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, not as a template for your
 * applications.
 */
public class BadIOGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + BadIOGUI.class.getSimpleName() + ".txt";
    private static final int PROPORTION = 5;
    private final Random rng = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /**
     * 
     */
    public BadIOGUI() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JPanel windows = new JPanel();
        windows.setLayout(new BoxLayout(windows, BoxLayout.LINE_AXIS));
        final JButton write = new JButton("Write on file");
        windows.add(write);
        final JButton read = new JButton("Read the file");
        final JButton space = new JButton("          "); // wanted a space beetween the buttons to look better
        windows.add(space);
        windows.add(read);
        panel.add(windows, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * 
         * Handlers
         */
        write.addActionListener(e -> {
            try (PrintStream ps = new PrintStream(PATH)) {
                ps.print(rng.nextInt());
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });

        read.addActionListener(e -> {
            try (InputStream in = new FileInputStream(PATH)) {
                DataInputStream data = new DataInputStream(in);
                System.out.println(data.readLine());
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });

        // Took from solution, readLine is deprecated and i didn't find a good alternative
        /*
         * read.addActionListener(e -> { try { List<String> lines =
         * Files.readAllLines(new File(PATH).toPath()); for(final String l: lines) {
         * System.out.println(l); } }catch (IOException e1) {
         * JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
         * e1.printStackTrace(); } });
         */
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the primary
         * is selected. In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is MUCH
         * better than manually specify the size of a window in pixel: it takes into
         * account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this flag
         * makes the OS window manager take care of the default positioning on screen.
         * Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * @param args
     *                 ignored
     */
    public static void main(final String... args) {
        new BadIOGUI().display();
    }
}