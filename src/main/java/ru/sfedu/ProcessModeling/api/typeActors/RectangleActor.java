package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import static ru.sfedu.ProcessModeling.Constants.*;

import java.awt.*;


public class RectangleActor extends Actor {


    public RectangleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }
    float fx, fy;

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,(int)width,(int)height);
        g.fillOval((int)fx-4,(int)fy-4,4,4);

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
        return  ((x >= this.x &&  x <= this.x+width ) && ( y >= this.y && y <= this.y+height));
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
        fx = x; fy = y;
        float x1 = this.x, y1 = this.y, x2 = this.x + width,  y2 = this.y + height;
        float dx1 = (x - x1), dx2 = (x - x2), dy1 = (y-y1), dy2 = (y-y2);
        dx1 = dx1*speedX<0 ? dx1 : dx2;
        dx2 = dx2*speedX<0 ? dx2 : dx1;
        dy1 = dy1*speedY<0 ? dy1 : dy2;
        dy2 = dy2*speedY<0 ? dy2 : dy1;
        float dx0 = dx2, dy0 = dy2;
        float x0 = x - dx0, y0 = y - dy0;
        normalAngle =  dx0*speedX>=dy0*speedY ? (x > x0 ? (float)Math.PI/2 : (float)-Math.PI/2 ):(y > y0 ? 0 : (float)Math.PI);
        System.out.println("normal angle = " + normalAngle);
    }
}
