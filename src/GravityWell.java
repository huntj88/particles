import com.sun.javafx.geom.Vec2f;

import java.awt.*;

/**
 * Created by James on 10/4/2015.
 */
public class GravityWell extends ParticleModifier {

    double gravity;

    public GravityWell(int x, int y,double gravity)
    {
        super(x,y);
        this.gravity=gravity;
    }

    @Override
    public double[] effect(double angle,int particleX,int particleY) {
        return new double[]{getXSpeed(angle, Math.abs(x - particleX)),getYSpeed(angle,Math.abs(y - particleY))};
    }

    @Override
    public void drawModifier(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x - (int) gravity / 2, y - (int) gravity / 2, (int) gravity,(int)gravity);
    }


    private double getXSpeed(double angle,int distance)
    {
        return (Math.cos(angle) * (gravity/10.0 /((double)distance/50.0+1)));
    }

    private double getYSpeed(double angle,int distance)
    {
        return (Math.sin(angle) * (gravity/10.0/((double)distance/50.0+1)));
    }

}
