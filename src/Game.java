import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by James on 10/3/2015.
 */
public class Game extends JPanel implements Runnable,MouseMotionListener,MouseListener{

    ComputeParticles particles = new ComputeParticles();
    int mouseX,mouseY;
    int oldMouseX,oldMouseY;
    boolean pressed=false;

    public Game()
    {
        particles.spawn(2350);
        addMouseMotionListener(this);
        addMouseListener(this);
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.fillRect(0, 0, getWidth(), getHeight());
        drawModifiers(g);
        drawParticles(g);
        g.fillRect(mouseX - 6, mouseY - 2, 12, 4);
        g.fillRect(mouseX - 2, mouseY - 6, 4, 12);
        if(pressed) {
            int magnitude = (int)Math.sqrt((mouseX-oldMouseX)*(mouseX-oldMouseX)+(mouseY-oldMouseY)+(mouseY-oldMouseY));
            g.drawOval(oldMouseX-magnitude/2, oldMouseY-magnitude/2, magnitude,magnitude);
        }
        //particles.addParticle(10,100,10);
    }

    public void drawParticles(Graphics g)
    {
        for(Particle p:particles.getParticles())
            p.drawParticle(g);
    }

    public void drawModifiers(Graphics g)
    {
        for(ParticleModifier m:particles.getModifiers())
            m.drawModifier(g);
    }

    @Override
    public void run() {
        while(true)
        {
            System.out.println("game");
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
        mouseX=e.getX();
        mouseY=e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        particles.updateMouseCoords(e.getX(),e.getY());
        mouseX=e.getX();
        mouseY=e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        particles.updateMouseCoords(e.getX(), e.getY());
        //particles.clicked();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        particles.pressed();
        pressed=true;
        oldMouseX=e.getX();
        oldMouseY=e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        particles.released(Math.sqrt((mouseX-oldMouseX)*(mouseX-oldMouseX)+(mouseY-oldMouseY)+(mouseY-oldMouseY)));
        pressed=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
