import java.awt.*;
import java.util.ArrayList;

/**
 * Created by James on 10/3/2015.
 */
public class ComputeParticles implements Runnable{

    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<ParticleModifier> modifiers = new ArrayList<>();

    int mouseX,mouseY;

    public ComputeParticles()
    {
        new Thread(this).start();
    }

    public void updateMouseCoords(int mouseX, int mouseY)
    {
        this.mouseX=mouseX;
        this.mouseY=mouseY;
        //System.out.println(mouseX+" "+mouseY);
    }

    public void pressed()
    {
        //modifiers.add(new GravityWell(mouseX,mouseY));
        //System.out.print("pressed");
    }

    public void released(double magnitude)
    {
        //System.out.print("released");
        modifiers.add(new GravityWell(mouseX, mouseY, magnitude));
    }

    public void spawn(int amount)
    {
        for(int i = 0;i<amount;i++)
            particles.add(new Particle((int)(Math.random()*Launcher.WIDTH),(int)(Math.random()*Launcher.HEIGHT),(int)(Math.random()*Math.random()*Math.random()*5)+3));
    }

    public void addParticle(int x, int y, int size)
    {
        particles.add(new Particle(x,y,size));
    }

    public ArrayList<Particle> getParticles()
    {
        return particles;
    }
    public ArrayList<ParticleModifier> getModifiers()
    {
        return modifiers;
    }

    public void calculateParticleMoves()
    {
        /*for (Particle p : particles)
            p.move(mouseX,mouseY);*/

        for(int i = 0;i<particles.size();i++)
        {
            particles.get(i).move(mouseX,mouseY,modifiers);
        }
    }

    @Override
    public void run() {
        while(true) {

            calculateParticleMoves();
            System.out.println("game2");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
