package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Collider;
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
        //g.fillRect((int)x,(int)y,(int)width,(int)height);
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
        return (x >= this.x &&  x <= this.x+width ) && ( y >= this.y && y <= this.y+height);
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
}
