package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Collider;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.MIN_HEIGHT;
import static ru.sfedu.ProcessModeling.Constants.MIN_WIDTH;

public class CircleActor extends Actor {
    public CircleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.GREEN);
        g.fillOval((int)x, (int)y, (int)this.width, (int)this.height);
    }

    @Override
    public void update(){
        x+=getSpeedX();
        y+=getSpeedY();
        if(x+this.width >= processing.width || x <= 0){
            setSpeedX(getSpeedX()*-1);
        }
        if(y+this.height >= processing.height || y <= 0){
            setSpeedY(getSpeedY()*-1);
        }
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        return (x - this.x + this.width/2*scaleX)*(x - this.x + this.width/2*scaleX)/(scaleX*scaleX)+
                (y - this.y + this.width/2*scaleY)*(y - this.y + this.width/2*scaleY)/(scaleY*scaleY)
                <= this.width/2 * this.width/2;
    }


    @Override
    public float[][] getPoints(float width, float height) {
        int n = (int) (Math.PI * (width+height)/MIN_WIDTH);
        float angleRotation = 0, dAngleRotation = (float)Math.PI * (width+height) / n;
        float[][] points = new float[2][n];
        for(int i = 0; i<n; i++, angleRotation+=dAngleRotation){
            points[0][i] = (float) (width/2 + width * Math.cos(angleRotation));
            points[1][i] = (float) (height/2 + height * Math.sin(angleRotation));
        }
        return points;
    }
}
