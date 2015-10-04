import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by James on 10/3/2015.
 */
public class Launcher {

    final public static int WIDTH = 1920;
    final public static int HEIGHT = 1040;

    public static void main(String[] cows)
    {
        JFrame frame = new JFrame("particle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game();

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);

        frame.getContentPane().add(game);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
    }
}
