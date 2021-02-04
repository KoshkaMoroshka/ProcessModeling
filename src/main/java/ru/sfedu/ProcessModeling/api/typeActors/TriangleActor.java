package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;
import ru.sfedu.ProcessModeling.api.Collider;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.MIN_HEIGHT;
import static ru.sfedu.ProcessModeling.Constants.MIN_WIDTH;

public class TriangleActor extends Actor {

    public TriangleActor(Simulation processing, int width, int height) {
        super(processing, width, height);
    }



    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setColor(Color.RED);
        /*g.drawLine((int)x-25, (int)y, (int)x+25,(int) y);
        g.drawLine((int)x-25, (int)y,(int) x, (int)y-43);
        g.drawLine((int)x+25, (int)y, (int)x, (int)y-43);*/
        int xm[] = {(int)x, (int)(x+width), (int) (x+width/2)};
        int ym[] = {(int)(y+height), (int)(y+height), (int) y};
        g.fillPolygon(xm,ym,3);
    }

    @Override
    public void update(){
        x+=getSpeedX();
        y+=getSpeedY();
        if(x + width> processing.width || x < 0){
            setSpeedX(getSpeedX()*-1);
        }
        if(y + height> processing.height || y < 0){
            setSpeedY(getSpeedY()*-1);
        }
    }

    private boolean sameSide(float x, float y, float x1, float y1, float x2, float y2, float x3, float y3){
        return ((x-x1)*(y2-y1)-(y-y1)*(x2-x1))*((x3-x1)*(y2-y1)-(y3-y1)*(x2-x1))>=0;
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        return sameSide(x, y, this.x, this.y+height, this.x+width, this.y +height, this.x+width/2,this.y) &&
               sameSide(x, y, this.x+width, this.y+height, this.x+width/2, this.y, this.x,this.y+height) &&
               sameSide(x, y, this.x+width/2, this.y, this.x, this.y +height, this.x+width,this.y+height);
    }

    @Override
    public float[][] getPoints(float width, float height) {
        int countPointsEdge = (int)((Math.sqrt((width/2)*(width/2) + height*height))/MIN_WIDTH), countPointsGrounds = (int)(height/MIN_WIDTH);
        float[][] points = new float[2][countPointsEdge*2 + countPointsGrounds + 3];
        int i = 1;
        points[0][0] = x;
        points[1][0] = y + height;
        for (int k =1; k < countPointsGrounds+1; k++, i++){
            points[0][i] = points[0][i-1] + width/countPointsGrounds - x;
            points[1][i] = points[1][i-1] - y;
        }
        for (int k = 1; k < countPointsEdge+1; k++, i++){
            points[0][i] = points[0][i-1] - (width/2) / countPointsEdge - x;
            points[1][i] = points[0][i-1] - height / countPointsEdge - y;
        }
        for (int k = 1; k< countPointsEdge+1; k++, i++){
            points[0][i] = points[0][i-1] - (width/2) / countPointsEdge - x;
            points[1][i] = points[0][i-1] + height / countPointsEdge - y;
        }
        return points;
    }
}
