package ru.sfedu.ProcessModeling.api.typeActors;

import ru.sfedu.ProcessModeling.api.Simulation;
import ru.sfedu.ProcessModeling.api.Actor;

import java.awt.*;

import static ru.sfedu.ProcessModeling.Constants.MIN_HEIGHT;
import static ru.sfedu.ProcessModeling.Constants.MIN_WIDTH;


public class RectangleActor extends Actor {

    public float masCornerX[], masCornerY[];
    float[] normalAngles;

    public RectangleActor(Simulation processing,float x, float y, int width, int height) {
        super(processing, x, y, width, height);
    }

    @Override
    public void draw(Graphics g){
        super.draw(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.translate(centerX + x, centerY + y);
        graphics2D.rotate(rotation);
        graphics2D.setColor(this.color);
        int[]   bufX = {(int)(masCornerX[0]), (int)(masCornerX[1]),(int)(masCornerX[2]),(int)(masCornerX[3])},
                bufY = {(int)(masCornerY[0]), (int)(masCornerY[1]),(int)(masCornerY[2]),(int)(masCornerY[3])};
        graphics2D.fillPolygon(bufX, bufY, 4);
        graphics2D.setColor(Color.GREEN);
        graphics2D.rotate(-rotation);
        graphics2D.setColor(Color.magenta);
        for (int i = 0; i<points[0].length; i++){
            graphics2D.fillOval((int)points[0][i], (int)points[1][i], 4,4);
        }
        graphics2D.translate(-(centerX+x), -(centerY + y));
    }

    @Override
    public boolean pointBelongToArea(float x, float y) {
        float cosRotation = (float) Math.cos(rotation);
        float sinRotation = (float) Math.sin(rotation);
        float rx = -(x - centerX - this.x);
        float ry = -(y - centerY - this.y);
        float dx = rx * cosRotation - ry * sinRotation;
        float dy = ry * cosRotation + rx * sinRotation;
        return  ((dx >= -width/2 &&  dx <= width/2 ) && ( dy >= -height/2 && dy <= height/2));
    }

    @Override
    public float[][] getPoints(float width, float height) {
        int countPointsWidth = (int)(width/MIN_WIDTH), countPointsHeight = (int)(height/MIN_HEIGHT);
        float[][] points = new float[2][countPointsWidth*2 + countPointsHeight*2 + 4];
        int i = 1;

        masCornerX = new float[4];
        masCornerY = new float[4];
        normalAngles = new float[4];
        centerX = width/2;
        centerY = height/2;

        this.centerX = width/2;
        this.centerY = height/2;

        points[0][0] = 0;
        points[1][0] = 0;

        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] + width/countPointsWidth;
            points[1][i] = points[1][i-1];
        }
        masCornerX[1] = points[0][i-1];
        masCornerY[1] = points[1][i-1];
        for(int k=1; k<countPointsHeight+1; k++, i++)
        {
            points[0][i] = points[0][i-1];
            points[1][i] = points[1][i-1] + height/countPointsHeight;
        }
        masCornerX[2] = points[0][i-1];
        masCornerY[2] = points[1][i-1];
        for(int k=1; k<countPointsWidth+1; k++, i++)
        {
            points[0][i] = points[0][i-1] - width/countPointsWidth;
            points[1][i] = points[1][i-1];
        }
        masCornerX[3] = points[0][i-1];
        masCornerY[3] = points[1][i-1];
        for(int k=1; k<countPointsHeight+1; k++, i++)
        {
            points[0][i] = points[0][i-1];
            points[1][i] = points[1][i-1] - height/countPointsHeight;
        }
        masCornerX[0] = points[0][i-1];
        masCornerY[0] = points[1][i-1];
        for (int k = 0; k<points[0].length; k++){
            points[0][k] -= centerX;
            points[1][k] -= centerY;
        }
        for (int k=0; k<4; k++){
            masCornerX[k] -= centerX;
            masCornerY[k] -= centerY;
        }
        return points;
    }

}
