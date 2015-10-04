import java.awt.*;

/**
 * Created by James on 10/4/2015.
 */
public class ParticleTrail {

    double xLength,yLength;
    int x,y;
    int size;
    Color color;

    public ParticleTrail(int x,int y,int size,Color color)
    {
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=color;
    }

    public void updateTrail(int x,int y,double xLength,double yLength)
    {
        this.x=x;
        this.y=y;
        this.xLength=xLength;
        this.yLength=yLength;
    }

    public void drawParticleTrail(Graphics g)
    {
        if(xLength!=0) {
            Graphics2D g2d = (Graphics2D) g.create();
            //Rectangle rect2 = new Rectangle(x, y, (int) Math.sqrt(xLength * xLength + yLength * yLength)*38, size);
            double angle = Math.atan(yLength / xLength);

            if(xLength>0)
                angle+=Math.PI;
            g2d.setColor(new Color(color.getRed() - 100, color.getGreen() - 100, color.getBlue() - 100));
            //g2d.setColor(Color.black);
            g2d.rotate(angle, x + size / 2, y + size / 2);
            //g2d.draw(rect2);
            //g2d.fill(rect2);
            g2d.fillOval(x, y, (int) (Math.sqrt(xLength * xLength + yLength * yLength)*1.2), size);
            g2d.dispose();
        }
    }

}
