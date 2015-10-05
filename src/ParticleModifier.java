import com.sun.javafx.geom.Vec2f;

import java.awt.*;

/**
 * Created by James on 10/4/2015.
 */
public abstract class ParticleModifier {

    protected int x,y;

    public ParticleModifier(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    public abstract double[] effect(double angle,int particleX,int particleY);
    public abstract void drawModifier(Graphics g);

    public int getX(){return x;}
    public int getY(){return y;}

}
