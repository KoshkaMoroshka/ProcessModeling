package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import static ru.sfedu.ProcessModeling.Constants.*;

import java.awt.*;


public class RectangleActor extends Actor {


    public RectangleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,(int)width,(int)height);
            g.fillOval((int)x-4,(int)y-4,4,4);
    }

    @Override
    public void update(){
        x+=getSpeedX();
        y+=getSpeedY();
        if(x + width>= processing.width || x <= 0){
            setSpeedX(getSpeedX()*-1);
        }
        if(y + height>= processing.height || y <= 0){
            setSpeedY(getSpeedY()*-1);
        }

    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        if ((x >= this.x &&  x <= this.x+width ) && ( y >= this.y && y <= this.y+height)){
            getNormalAngle(x,y,getSpeedX(),getSpeedY());
            return true;
        }
        return false;
    }

    @Override
    public float[][] getPoints(float width, float height) {
        int countPointsWidth = (int)(width/MIN_WIDTH), countPointsHeight = (int)(height/MIN_HEIGHT);
        float[][] points = new float[2][countPointsWidth*2 + countPointsHeight*2 + 4];
        int i = 1;
        points[0][0] = x;
        points[1][0] = y;

        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] + width/countPointsWidth - x;
            points[1][i] = points[1][i-1] - y;
        }
        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] - x;
            points[1][i] = points[1][i-1] + height/countPointsHeight - y;
        }
        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] - width/countPointsWidth - x;
            points[1][i] = points[1][i-1] - y;
        }
        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] - x;
            points[1][i] = points[1][i-1] - height/countPointsHeight - y;
        }
        return points;
    }

    @Override
    public void getNormalAngle(float x, float y, float speedX, float speedY) {
        if((x >= this.x && x <= this.x + width)||(y >= this.y && y <= this.y + height)){
            x -= speedX;
            y -= speedY;
        }
        float stepXLeft = Math.abs((x-this.x)/speedX);
        float stepXRight = Math.abs((x-this.x-width)/speedX);
        float stepYUp = Math.abs((y - this.y)/speedY);
        float stepYBottom = Math.abs((y - (this.y + height))/speedY);
        float distanceToAngleEdge1 = (float) Math.sqrt((x - this.x)*(x - this.x) + (y - this.y)*(y  - this.y)),
              distanceToAngleEdge2 = (float) Math.sqrt((x - this.x - width)*(x - this.x - width) + (y - this.y)*(y - this.y)),
              distanceToAngleEdge3 = (float) Math.sqrt((x - this.x - width)*(x - this.x - width) + (y - this.y - height)*(y - this.y - height)),
              distanceToAngleEdge4 = (float) Math.sqrt((x - this.x)*(x - this.x) + (y - this.y - height)*(y - this.y - height));

    }
}
