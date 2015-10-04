import java.awt.*;

/**
 * Created by James on 10/3/2015.
 */
public class Particle {

    private int x,y;
    private double mathX,mathY;
    private int size;
    private double xSpeed,ySpeed;
    private double gravity=10;
    private Color color;
    private ParticleTrail trail;
    private int maxSpeed=3;

    public Particle(int x, int y,int size)
    {
        //color = Color.green;
        color = new Color((int)(Math.random()*155)+100,(int)(Math.random()*105)+150,(int)(Math.random()*155)+100);
        mathX=x;
        mathY=y;
        this.size=size;
        trail = new ParticleTrail(x,y,size,color);
    }



    public void drawParticle(Graphics g)
    {
        if(x>0&&x<Launcher.WIDTH&&y>0&&y<Launcher.HEIGHT) {
            trail.drawParticleTrail(g);
            g.setColor(color);
            g.fillOval(x, y, size, size);
        }
    }

    public void move(int mouseX,int mouseY)
    {

        double yChangeSpeed;
        double xChangeSpeed;

        if(mouseX-x==0)
            mouseX++;
        if(mouseY-y==0)
            mouseY++;
        double xAngle = Math.atan((double) (mouseY - y) / (double) (mouseX - x));

        xChangeSpeed = getXSpeed(xAngle, Math.abs(mouseX - x));
        yChangeSpeed = getYSpeed(xAngle,Math.abs(mouseY - y));

        if(x>mouseX)
            xSpeed = xSpeed - xChangeSpeed;
        else
            xSpeed = xSpeed + xChangeSpeed;

        if(x>mouseX)
            ySpeed=ySpeed-yChangeSpeed;
        else
            ySpeed=ySpeed+yChangeSpeed;

        //updown(xChangeSpeed,yChangeSpeed);
        cross();


        mathX+=xSpeed;
        mathY+=ySpeed;

        x=(int)mathX;
        y=(int)mathY;
        /*x=400;
        y=400;*/

        trail.updateTrail(x,y,xSpeed,ySpeed);
    }

    private double getXSpeed(double angle,int distance)
    {
        return (Math.cos(angle) * (gravity /((double)distance/50.0+1)));
    }

    private double getYSpeed(double angle,int distance)
    {
        return (Math.sin(angle) * (gravity/((double)distance/50.0+1)));
    }

    public void cross()
    {
        if(xSpeed>maxSpeed)
            xSpeed--;
        else if(xSpeed<-maxSpeed)
            xSpeed++;

        if(ySpeed>maxSpeed)
            ySpeed--;
        else if(ySpeed<-maxSpeed)
            ySpeed++;
    }

    public void updown(double xChangeSpeed,double yChangeSpeed)
    {
        if(xSpeed>maxSpeed)
            xSpeed-=xChangeSpeed;
        else if(xSpeed<-maxSpeed)
            xSpeed+=xChangeSpeed;

        if(ySpeed>maxSpeed)
            ySpeed-=yChangeSpeed;
        else if(ySpeed<-maxSpeed)
            ySpeed+=yChangeSpeed;
    }

    /*public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }*/
}
