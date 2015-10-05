import com.sun.javafx.geom.Vec2f;

import java.awt.*;
import java.util.ArrayList;

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

    public void move(int mouseX,int mouseY,ArrayList<ParticleModifier> modifiers)
    {

        moveFromMouse(mouseX, mouseY);
        moveFromModifiers(modifiers);

        cross();

        mathX+=xSpeed;
        mathY+=ySpeed;

        x=(int)mathX;
        y=(int)mathY;

        trail.updateTrail(x,y,xSpeed,ySpeed);
    }

    public double getAngle(int inputX,int inputY)
    {
        if(inputX-x==0)
            inputX++;
        if(inputY-y==0)
            inputY++;
        return Math.atan((double) (inputY - y) / (double) (inputX - x));
    }

    public void moveFromMouse(int mouseX,int mouseY)
    {
        double yChangeSpeed;
        double xChangeSpeed;
        double angle = getAngle(mouseX,mouseY);

        xChangeSpeed = getXSpeed(angle, Math.abs(mouseX - x));
        yChangeSpeed = getYSpeed(angle, Math.abs(mouseY - y));

        quadFinalize(mouseX,mouseY,xChangeSpeed,yChangeSpeed);
    }

    public void moveFromModifiers(ArrayList<ParticleModifier> modifiers)
    {
        for(int i = 0; i<modifiers.size();i++) {
            try {
                double[] modifierData = modifiers.get(i).effect(getAngle(modifiers.get(i).getX(), modifiers.get(i).getY()), x, y);
                quadFinalize(modifiers.get(i).getX(), modifiers.get(i).getY(), modifierData[0], modifierData[1]);
            }
            catch(NullPointerException e)
            {
                System.err.print("null pointer moveFromModifiers");
            }
        }
    }

    public void quadFinalize(int inputX,int inputY,double xChangeSpeed,double yChangeSpeed)
    {
        if(x>inputX)
            xSpeed = xSpeed - xChangeSpeed;
        else
            xSpeed = xSpeed + xChangeSpeed;

        if(x>inputX)
            ySpeed=ySpeed-yChangeSpeed;
        else
            ySpeed=ySpeed+yChangeSpeed;

        //updown(xChangeSpeed,yChangeSpeed);
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
