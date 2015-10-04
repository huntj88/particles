import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by James on 10/3/2015.
 */
public class Game extends JPanel implements Runnable,MouseMotionListener{

    ComputeParticles particles = new ComputeParticles();
    int mouseX,mouseY;

    public Game()
    {
        particles.spawn(2350);
        addMouseMotionListener(this);
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        drawParticles(g);
        g.fillRect(mouseX-6,mouseY-2,12,4);
        g.fillRect(mouseX-2,mouseY-6,4,12);
        //particles.addParticle(10,100,10);
    }

    public void drawParticles(Graphics g)
    {
        for(Particle p:particles.getParticles())
            p.drawParticle(g);
    }

    @Override
    public void run() {
        while(true)
        {
            //System.out.println("game");
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        particles.updateMouseCoords(e.getX(),e.getY());
        mouseX=e.getX();
        mouseY=e.getY();
    }
}
